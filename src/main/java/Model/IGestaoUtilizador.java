package Model;

import java.util.*;
import java.sql.*;

public interface IGestaoUtilizador {
    void newUser (String nome, String password, String email) throws SQLException;
    void newUser (String nome, String password, String email, String num_telefone) throws SQLException;
	void logIn(String email, String password) throws SQLException;
    void logOut(String email) throws SQLException;
    void adicionaVisita(String email,String centro) throws SQLException;
    Utilizador getByEmail(String email) throws SQLException;
    List<String> getCentrosVisitados(String email) throws SQLException;
    void replace(String email, Utilizador user) throws SQLException;
}
