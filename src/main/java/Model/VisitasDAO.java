package Model;

import java.sql.*;
import java.util.*;

public class VisitasDAO {
    private Connection c;

    public VisitasDAO (Connection connection){
        this.c = connection;
    }

    public List<String> getCentros(String email) throws SQLException {
        List<String> resultado = new ArrayList<>();
        String query = "SELECT centro_historico FROM visitados WHERE email = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String centro = rs.getString("centro_historico");
            resultado.add(centro);
        }
        return resultado;
    }


    public void addVisita (String email,String centro) throws SQLException {
        String query ="INSERT IGNORE INTO visitados(centro_historico,email) VALUES (?,?)";
        PreparedStatement ps = c.prepareStatement(query);
        System.out.println("centro:" +centro);
        System.out.println("email:" + email);
        ps.setString(1,centro);
        ps.setString(2,email);
        boolean staus = ps.execute();
        System.out.println("Visita adicionada -> "+ staus);
    }

}
