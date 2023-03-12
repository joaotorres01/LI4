package Model;

import java.sql.*;

public class UtilizadorDAO {

    private Connection c;

    public UtilizadorDAO(Connection connection){
        this.c = connection;
    }

    public Utilizador create(String nome, String password, String email) throws SQLException{
        String query = "INSERT INTO utilizador (Email,Password,Nome,LoggedIn,Configuracoes_notificacao)VALUES (?, ?, ?, ?,?)";
        PreparedStatement pstmt = c.prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        pstmt.setString(3, nome);
        pstmt.setInt(4, 1);
        pstmt.setInt(5,1);
        pstmt.execute();
        return new Utilizador(nome, password, email);
    }

    public Utilizador create(String nome, String password, String email, String num_telefone) throws SQLException{
        String query = "INSERT INTO utilizador (Email,Password,Nome,Telemovel,LoggedIn,Configuracoes_notificacao )VALUES (?, ?,?, ?, ?,?)";
        PreparedStatement pstmt = c.prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        pstmt.setString(3, nome);
        pstmt.setString(4,num_telefone);
        pstmt.setInt(5, 1);
        pstmt.setInt(6,1);
        pstmt.execute();
        return new Utilizador(nome, password, email,num_telefone);
    }

    public Utilizador logIn (String email, String password) throws SQLException {
        String query = "UPDATE utilizador SET LoggedIn = ? WHERE email = ? AND password = ?";
        PreparedStatement ps;
        ps = c.prepareStatement(query);
        ps.setInt(1,1);
        ps.setString(2,email);
        ps.setString(3,password);
        int i = ps.executeUpdate();
        if (i  == 0){
            throw new SQLException("Utilizador não existe");
        }
        else {
            System.out.println("Login feito com sucesso");
            return getUtilizador(email);
        }
    }

    public void logOut(String email) throws SQLException {
        String query = "UPDATE utilizador SET LoggedIn = ? WHERE email = ? AND LoggedIn = ?";
        PreparedStatement ps;
        ps = c.prepareStatement(query);
        ps.setInt(1,0);
        ps.setString(2,email);
        ps.setInt(3,1);
        int i = ps.executeUpdate();
        if (i == 0){
            System.out.println("Logout falhado");//TODO:THROW EXCEPTION
        }
        else System.out.println("Logout efetuado");
    }


    public Utilizador getUtilizador (String email) throws SQLException {
        String query = "SELECT * FROM utilizador WHERE email = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String password = rs.getString("Password");
            String nome = rs.getString("Nome");
            String telemovel = rs.getString("Telemovel");
            boolean loggedIn = rs.getBoolean("LoggedIn");
            return new Utilizador(nome, email, password, telemovel, loggedIn, c);
        }
        throw new SQLException("Can't get user");
    }

    public void replaceNome(String email, String nome) throws SQLException {
        String query = "UPDATE utilizador SET Nome = ? WHERE Email = ?";
        PreparedStatement ps;
        ps = c.prepareStatement(query);
        ps.setString(1, nome);
        ps.setString(2, email);
        ps.execute();
    }

    public void replacePassword(String email, String password) throws SQLException{
        String query = "UPDATE utilizador SET Password = ? WHERE Email = ?";
        PreparedStatement ps;
        ps = c.prepareStatement(query);
        ps.setString(1,password);
        ps.setString(2,email);
        ps.execute();
    }

    public void replaceTelefone(String email, String numTel) throws  SQLException{
        String query = "UPDATE utilizador SET Telemovel = ? WHERE Email = ?";
        PreparedStatement ps;
        ps = c.prepareStatement(query);
        ps.setString(1,numTel);
        ps.setString(2,email);
        ps.execute();
    }
}