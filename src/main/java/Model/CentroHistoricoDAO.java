package Model;

import java.sql.*;
import java.util.*;

public class CentroHistoricoDAO {
    private Connection c;
    private static String dataBase = "centro_historico";

    public CentroHistoricoDAO(Connection connection) {
        this.c = connection;
    }

    private Localizacao getLocalizao(String id) throws SQLException {
        String query = "SELECT Longitude,Latitude FROM localizacao WHERE centro_historico = ?";
        PreparedStatement st = c.prepareStatement(query);
        st.setString(1,id);
        ResultSet rs = st.executeQuery();
        rs.next();
        float latitude = rs.getFloat("Latitude");
        float longitude = rs.getFloat("Longitude");
        return new Localizacao(latitude,longitude);
    }

    private List<Horario> getHorario(String id) throws SQLException{
        List<Horario> list = new ArrayList<>();
        String query = "SELECT Dia,Abertura,Fecho FROM horario WHERE centro_historico = ?";
        PreparedStatement st = c.prepareStatement(query);
        st.setString(1,id);
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            int dia = rs.getInt("Dia");
            Time abertura = rs.getTime("Abertura");
            Time fecho = rs.getTime("Fecho");
            Horario horario = new Horario(dia,abertura,fecho);
            list.add(horario);
        }
        return list;
    }



    public CentroHistorico get(String id) throws SQLException {
        String query = "SELECT * FROM centro_historico WHERE NomeCentroHistorico = ?";
        PreparedStatement st = c.prepareStatement(query);
        st.setString(1,id);
        ResultSet rs = st.executeQuery();
        rs.next();
        String nome = rs.getString("Descrição");
        String rua = rs.getString("Rua");
        Localizacao localizacao = getLocalizao(id);
        List<Horario> horarios = getHorario(id);
        ReviewsDAO reviewsDAO = new ReviewsDAO(c);
        return new CentroHistorico(id,nome,rua,localizacao,horarios,reviewsDAO);
    }

    public List<String> getAllKeys() throws SQLException{
        String querry = "SELECT NomeCentroHistorico FROM " + dataBase;
        PreparedStatement st = c.prepareStatement(querry);
        ResultSet rs = st.executeQuery();
        List<String> result = new ArrayList<>();
        while(rs.next()) result.add(rs.getString("NomeCentroHistorico"));
        return result;
    }


    public Map<String,Localizacao> getLocalizacoes() throws SQLException {
        Map<String,Localizacao> locais = new HashMap<>();
        String query = "SELECT * FROM localizacao";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            String centro = rs.getString("centro_historico");
            float latitude = rs.getFloat("Latitude");
            float longitude = rs.getFloat("Longitude");
            Localizacao localizacao = new Localizacao(latitude,longitude);
            locais.put(centro,localizacao);

        }
        return locais;

    }

    public Map<String, String> getAllNames() throws SQLException {
        Map<String,String> nomes = new TreeMap<>();
        String query ="SELECT * FROM centro_historico";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            String id = rs.getString("NomeCentroHistorico");
            String nome = rs.getString("Descrição");
            nomes.put(id,nome);
        }
        return nomes;
    }
}