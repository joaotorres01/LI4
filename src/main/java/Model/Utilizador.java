package Model;

import java.sql.*;
import java.util.*;

public class Utilizador {
 
    private String nome = "";
    private String email = "";
    private String password = "";
    private String numTelefone = "";
    private boolean loggedIn = false;

    private VisitasDAO historico;

    public Utilizador(String nome, String email, String password, String numTelefone, boolean loggedIn, Connection c) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.numTelefone = numTelefone;
        this.loggedIn = loggedIn;
        this.historico= new VisitasDAO(c);
    }

    public Utilizador(String nome, String password, String email){
        this.nome = nome;
        this.email = email;
        this.password = password;        
    }

    public Utilizador(String nome, String password, String email, String num_telefone){
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.numTelefone = num_telefone;        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


    public void addVisita(String centro) throws  SQLException{
        this.historico.addVisita(email,centro);
    }

    public void mudarPass(String pass){
        setPassword(password);
    }


    public boolean validaRegisto(String email, String nome, String password){ 
        // ver na base de dados
        return (!this.email.equals(email) || !this.nome.equals(nome) || !this.password.equals(password));
    }

    public boolean validaRegisto(String email, String n_telemovel, String nome, String password){
        // ver na base de dados
        return (!this.email.equals(email) || !this.nome.equals(nome) || !this.password.equals(password) || !this.numTelefone.equals(n_telemovel));
    }

    public List<String> getVisitados() throws SQLException {
        return historico.getCentros(this.email);
    }
}
