package Servlet;

import Model.*;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangeServlet", value = "/change")
public class ChangeServlet extends HttpServlet {
    IGuideMeTo gtm;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            gtm = new GuideMeTo();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String email = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            String value = cookies[i].getValue();
            if (name.equals("email")){
                email = value;
            }
        }
        Utilizador user = gtm.getUserByEmail(email);
        request.setAttribute("name",user.getNome());
        request.setAttribute("phone",user.getNumTelefone());
        request.setAttribute("email",user.getEmail());
        getServletContext().getRequestDispatcher("/change.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Utilizador user = gtm.getUserByEmail(email);
        String nameNew = request.getParameter("nameNew");
        String phoneNew = request.getParameter("phoneNew");
        String oldPassword = request.getParameter("oldPassword");
        String passwordNew = request.getParameter("passwordNew");
        String confirmPassword = request.getParameter("confirmPassword");

        System.out.println("name is" +nameNew);




        if(!oldPassword.isEmpty()) {
            if(oldPassword.equals((user.getPassword()))) {
                if (!passwordNew.equals(confirmPassword)) {
                    request.setAttribute("error", "As palavras-passe não são iguais");
                    doGet(request, response);
                }
                else{
                    System.out.println();
                    try {
                        gtm.changeUser(user.getEmail(), new Utilizador(nameNew, passwordNew, user.getEmail(), phoneNew));
                    }
                    catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("The account was been changed");
                    response.sendRedirect("/map");
                }
        }
            else{
                request.setAttribute("error", "A palavra-passe introduzida não é a atual");
                doGet(request, response);
            }
        }
        else{
            request.setAttribute("error", "O campo palavra-passe é obrigatório");
            doGet(request, response);
        }
    }
}
