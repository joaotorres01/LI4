package Model;

import java.sql.*;
import java.util.*;


public class GestaoCentroHistorico implements IGestaoCentroHistorico{
    private CentroHistoricoDAO centros;

    public GestaoCentroHistorico(Connection connection){
        this.centros = new CentroHistoricoDAO(connection);
    }

    public List<String> getKeys(){
        try{ return this.centros.getAllKeys(); }
        catch(SQLException e){ return new ArrayList<>();}
    }

    public List<Horario> getHorario(String nome){
        try{
            return centros.get(nome).getHorarios();
        } catch(Exception e) { return null; }
    }

    public String getDescricao(String nome){
        try{
            return centros.get(nome).getNome();
        } catch(Exception e) { return null; }
    }



    public String getRua(String nome){
        try{
            return centros.get(nome).getRua();
        } catch(Exception e) { return null; }
    }

    public Map<String,Localizacao> getLocalizacoes() throws SQLException {
        return centros.getLocalizacoes();
    }

    public CentroHistorico getCentro(String centro) throws SQLException {
        return centros.get(centro);
    }

    public MediaReviews getMediaReviews (String centro) throws SQLException {
        return centros.get(centro).getMediaReviews();
    }

    public Map<String, String> getAllCentrosNames() throws SQLException {
        return centros.getAllNames();
    }
}
