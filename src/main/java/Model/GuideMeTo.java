package Model;

import java.sql.*;
import java.util.*;

public class GuideMeTo implements IGuideMeTo {
    
    private IGestaoUtilizador utilizadores;
    private IGestaoCentroHistorico centros;
    private GestaoReviews reviews;

    public GuideMeTo() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LI4","root","root");
        this.utilizadores = new GestaoUtilizador(conn);
        this.centros = new GestaoCentroHistorico(conn);
        this.reviews = new GestaoReviews(conn);

    }

    public void registarUtilizador(String nome, String password, String email) throws SQLException {
        utilizadores.newUser(nome, password, email);
    }

    public void registarUtilizador(String nome, String password, String email, String n_telemovel) throws SQLException {
        utilizadores.newUser(nome, password, email, n_telemovel);
    }

    public void logIn(String email, String password) throws SQLException {
        utilizadores.logIn(email,password);

    }

    public void logOut(String email) throws SQLException {
        utilizadores.logOut(email);
    }


    public Map<String,Localizacao> getLocalizacoes () throws SQLException {
        return centros.getLocalizacoes();
    }

    public Utilizador getUserByEmail(String email){
        try {
            return utilizadores.getByEmail(email);
        }catch (SQLException e){
            return null;
        }
    }

    public void changeUser(String email, Utilizador user) throws SQLException {
        this.utilizadores.replace(email,user);

    }

    public String getNome (String email){
        try {
            String nome = utilizadores.getByEmail(email).getNome();
            return nome;
        }catch (SQLException e){
            return "";
        }
    }

    public CentroHistorico getCentro (String centro) throws SQLException {
        return centros.getCentro(centro);
    }

    public MediaReviews getMediaReviews(String centro) throws SQLException {
        return centros.getMediaReviews(centro);
    }

    public void adicionarReview(String email,String centro, int facilidade,int preservacao,int estetica,int experiencia) throws SQLException {
        reviews.adicionarReview(email, centro,  facilidade, preservacao, estetica, experiencia);
    }

    public void adicionarVisitado(String email,String centro) throws SQLException {
        utilizadores.adicionaVisita(email,centro);
    }

    public Map<String,String> getCentrosVisitados (String user) throws SQLException {
        Map<String,String> map = new HashMap<>();
        List<String> keysVisitados = utilizadores.getCentrosVisitados(user);
        for (String key : keysVisitados){
            String nome = centros.getDescricao(key);
            map.put(key,nome);
        }
        return map;
    }


    public Map<String, String> getAllCentros() throws SQLException {
        return centros.getAllCentrosNames();
    }
}
