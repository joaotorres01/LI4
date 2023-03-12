package Model;

import java.sql.Connection;
import java.sql.SQLException;

public class GestaoReviews {
    private ReviewsDAO reviews;

    public GestaoReviews(Connection c){
        this.reviews = new ReviewsDAO(c);
    }

    public void adicionarReview(String email, String centro, int facilidade, int preservacao, int estetica, int experiencia) throws SQLException {
        reviews.doReview(email,centro,preservacao,facilidade,experiencia,estetica);
    }
}
