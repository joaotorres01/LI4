package Servlet;

import Model.GuideMeTo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    //private GuideMeTo gtm = new GuideMeTo();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        try {
            GuideMeTo guideMeTo = new GuideMeTo();

            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (email.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                request.setAttribute("error", "Uma ou mais caixas estão vazias");
                doGet(request, response);
            } else {
                if (!password.equals(confirmPassword)) {
                    request.setAttribute("error", "As palavras-passe são diferentes");

                    doGet(request, response);
                } else {
                    try {
                        if (phone.isEmpty())guideMeTo.registarUtilizador(name,password,email);
                        else guideMeTo.registarUtilizador(name,password,email,phone);
                        response.sendRedirect("/map");
                    }
                    catch (SQLException e){
                        request.setAttribute("error","Email/Telemóvel em uso");
                        doGet(request,response);
                    }
                }
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        getServletContext().getRequestDispatcher("/register.jsp").forward(request,response);
    }

}
