package Servlet;

import Model.GuideMeTo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            GuideMeTo gtm = new GuideMeTo();
            
            String email = request.getParameter("email");
            String password = request.getParameter("password");


            if (email.isEmpty() || password.isEmpty()) {
                request.setAttribute("error", "Uma das caixas está vazia");
                doGet(request, response);
            }
            try {
                gtm.logIn(email, password);
                System.out.println("Válido");
                response.sendRedirect("/map");
            }
            catch (SQLException e){
                System.out.println("Inválido");
                request.setAttribute("error", "Login inválido");
                doGet(request,response);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
    }

}
