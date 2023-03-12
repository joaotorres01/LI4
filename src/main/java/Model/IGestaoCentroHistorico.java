package Model;

import java.util.*;
import java.sql.*;

public interface IGestaoCentroHistorico {
 
    List<String> getKeys();
    List<Horario> getHorario(String nome);
    String getDescricao(String nome);
    String getRua(String nome);
    Map<String,Localizacao> getLocalizacoes() throws SQLException;
    CentroHistorico getCentro(String centro) throws SQLException;
    MediaReviews getMediaReviews (String centro) throws SQLException;

    Map<String, String> getAllCentrosNames() throws SQLException;
}
