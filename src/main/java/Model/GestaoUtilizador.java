package Model;

import java.sql.*;
import java.util.*;

public class GestaoUtilizador implements IGestaoUtilizador{

    private UtilizadorDAO utilizadores;

    public GestaoUtilizador(Connection connection){
        this.utilizadores = new UtilizadorDAO(connection);
    }

    public void newUser (String nome, String password, String email) throws SQLException {
        utilizadores.create(nome, password, email);
    }

    public void newUser (String nome, String password, String email, String num_telefone) throws SQLException {
        utilizadores.create(nome, password, email, num_telefone);
    }

	public void logIn(String email, String password) throws SQLException {
        utilizadores.logIn(email,password);
	}

    public void logOut(String email) throws SQLException {
        utilizadores.logOut(email);
    }

    public void replace(String email, Utilizador user) throws SQLException{
        Utilizador oldUser = utilizadores.getUtilizador(email);
        utilizadores.replaceNome(email, selectString(oldUser.getNome(),user.getNome()) );
        utilizadores.replacePassword(email, selectString(oldUser.getPassword(),user.getPassword()) );
        utilizadores.replaceTelefone(email, selectString(oldUser.getNumTelefone(),user.getNumTelefone()));
    }
    


    private String selectString (String one,String two){
        if (two != null && !two.isEmpty()) return two;
        return one;
    }

    public void adicionaVisita(String email, String centro) throws SQLException {
        Utilizador utilizador = utilizadores.getUtilizador(email);
        utilizador.addVisita(centro);

    }

    public Utilizador getByEmail(String email) throws SQLException {
        return utilizadores.getUtilizador(email);
    }

    public List<String> getCentrosVisitados(String email) throws SQLException {
        Utilizador utilizador = utilizadores.getUtilizador(email);
        return utilizador.getVisitados();
    }
}
