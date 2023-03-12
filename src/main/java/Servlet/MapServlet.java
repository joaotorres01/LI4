package Servlet;

import Model.CentroHistorico;
import Model.GuideMeTo;
import Model.Localizacao;
import Model.MediaReviews;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "MapServlet", value = "/map")
public class MapServlet extends HttpServlet {
    private String email;
    private GuideMeTo gtm;


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String form = request.getParameter("form");
        String centro = request.getParameter("centroForm");
        if (form.equals("review")) {
            int facilidade = Integer.parseInt(request.getParameter("facilidadeAcesso"));
            int preservacao = Integer.parseInt(request.getParameter("preservacao"));
            int estetica = Integer.parseInt(request.getParameter("estetica"));
            int experiencia = Integer.parseInt(request.getParameter("experiencia"));
            try {
                gtm.adicionarReview(email, centro, facilidade, preservacao, estetica, experiencia);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (form.equals("visitados")){
            try {
                gtm.adicionarVisitado(email,centro);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        response.sendRedirect("/map?key="+ centro);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {

            String email = null;
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                String value = cookies[i].getValue();
                if (name.equals("email")){
                    email = value;
                }
            }
            this.gtm = new GuideMeTo();
            String nome;
            if (email != null){
                this.email = email;
                nome = gtm.getNome(email);
            }
            else{
                nome="Guest";
                this.email="";
            }
            String visitados = request.getParameter("visitados");
            request.setAttribute("Nome",nome);
            request.setAttribute("Email",this.email);
            String centro = request.getParameter("key");
            request.setAttribute("Key",centro);
            request.setAttribute("Centro","");
            Gson gson = new Gson();
            if (visitados != null){
                Map<String,String> mapVisitados = gtm.getCentrosVisitados(email);
                if (mapVisitados.size() != 0){
                    String visitadosJSON = gson.toJson(mapVisitados);
                    request.setAttribute("Visitados",visitadosJSON);
                }
            }
            String listar = request.getParameter("listar");
            if (listar != null){
                Map<String,String> mapAllCentros = gtm.getAllCentros();
                if (mapAllCentros.size() != 0){
                    String allCentros = gson.toJson(mapAllCentros);
                    request.setAttribute("ListaCentros",gson.toJson(allCentros));
                }
            }
            if ( centro != null){
                CentroHistorico centroHistorico =  gtm.getCentro(centro);
                MediaReviews mediaReviews = centroHistorico.getMediaReviews();
                String centroJSON = gson.toJson(centroHistorico);
                String mediaJSON = gson.toJson(mediaReviews);
                request.setAttribute("Media",mediaJSON);
                request.setAttribute("Centro",centroJSON);
            }
            Map<String, Localizacao> mapLocais = gtm.getLocalizacoes();
            String local = gson.toJson(mapLocais);
            request.setAttribute("Locais",local);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        getServletContext().getRequestDispatcher("/map.jsp").forward(request,response);
    }

    public void destroy() {
    }
}