package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDAO {
    private Connection c;

    public ReviewsDAO(Connection connection){
        this.c = connection;
    }


    public void doReview (String email,String centroHistorico,double preservacao,double facilidadeAcesso,double experiencia,double estetica) throws SQLException {
        String query = "INSERT INTO avaliacao (centro_historico,utilizador_email,Preservacao,FacilidadeAcesso,Experiencia,Estetica,Dia) VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = c.prepareStatement(query);
        pstmt.setString(1,centroHistorico);
        pstmt.setString(2,email);
        pstmt.setDouble(3,preservacao);
        pstmt.setDouble(4,facilidadeAcesso);
        pstmt.setDouble(5,experiencia);
        pstmt.setDouble(6,estetica);
        pstmt.setDate(7, Date.valueOf(LocalDate.now()));
        pstmt.execute();
    }



    public List<Review> getReviewsUser(String email) throws SQLException {
        List<Review> listReviews = new ArrayList<>();
        String query = "SELECT * FROM avaliacao WHERE utilizador_email = ?";
        PreparedStatement pstm = c.prepareStatement(query);
        pstm.setString(1, email);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            String centroHistorico = rs.getString("centro_historico");
            double preservacao = rs.getDouble("Preservacao");
            double facilidadeAcesso = rs.getDouble("FacilidadeAcesso");
            double experiencia = rs.getDouble("Experiencia");
            double estetica = rs.getDouble("Estetica");
            Date date = rs.getDate("Dia");
            Review review = new Review(email, centroHistorico, preservacao, experiencia, facilidadeAcesso, estetica, date);
            listReviews.add(review);
        }
        return listReviews;
    }

    public List<Review> getReviewsCentro(String centro) throws SQLException {
        List<Review> listReviews = new ArrayList<>();
        String query = "SELECT * FROM avaliacao WHERE centro_historico = ?";
        PreparedStatement pstm = c.prepareStatement(query);
        pstm.setString(1, centro);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            String email = rs.getString("utilizador_email");
            double preservacao = rs.getDouble("Preservacao");
            double facilidadeAcesso = rs.getDouble("FacilidadeAcesso");
            double experiencia = rs.getDouble("Experiencia");
            double estetica = rs.getDouble("Estetica");
            Date date = rs.getDate("Dia");
            Review review = new Review(email, centro, preservacao, experiencia, facilidadeAcesso, estetica, date);
            listReviews.add(review);
        }
        return listReviews;
    }

}
