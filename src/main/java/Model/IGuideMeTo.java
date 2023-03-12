package Model;

import java.util.*;
import java.sql.*;

public interface IGuideMeTo {
    void registarUtilizador(String nome, String password, String email) throws SQLException;
    void registarUtilizador(String nome, String password, String email, String n_telemovel) throws SQLException;
    void logIn(String email, String password) throws SQLException;
    void logOut(String email) throws SQLException;
    Map<String,Localizacao> getLocalizacoes () throws SQLException;
    String getNome (String email);
    CentroHistorico getCentro (String centro) throws SQLException;
    MediaReviews getMediaReviews(String centro) throws SQLException; 
    void adicionarReview(String email,String centro, int facilidade,int preservacao,int estetica,int experiencia) throws SQLException;
    void adicionarVisitado(String email,String centro) throws SQLException;
    Map<String,String> getCentrosVisitados (String user) throws SQLException;
    Utilizador getUserByEmail(String email);
    void changeUser(String email,Utilizador user) throws SQLException;
}