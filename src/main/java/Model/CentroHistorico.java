package Model;

import java.sql.SQLException;
import java.util.*;

public class CentroHistorico {
    private String key, nome,rua;
    private Localizacao localizacao;
    private List<Horario> horario;
    private transient ReviewsDAO reviews ;

    public CentroHistorico(String key, String nome, String rua,  Localizacao localizacao, List<Horario> horario, ReviewsDAO reviews) {
        this.key = key;
        this.nome = nome;
        this.rua = rua;
        this.localizacao = localizacao;
        this.horario = horario;
        this.reviews = reviews;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public String getRua(){
        return rua;
    }

    public List<Horario> getHorarios() {
        List<Horario> lista = new ArrayList<>();
        for (Horario h : this.horario){
            lista.add(h);
        }
        return lista;
    }

    public MediaReviews getMediaReviews() throws SQLException {
        List<Review> reviewList = reviews.getReviewsCentro(key);
        double m_pres = 0, m_fa = 0,m_exp = 0,m_est = 0;
        for (Review review : reviewList){
            m_pres += review.getPreservacao();
            m_fa += review.getFacilidadeAcesso();
            m_exp += review.getExperiencia();
            m_est += review.getEstetica();
        }
        int len = reviewList.size();
        if (len > 0){
            m_pres=m_pres/len;
            m_fa= m_fa/len;
            m_est=m_est/len;
            m_exp=m_exp/len;
        }
        return new MediaReviews(m_pres,m_exp,m_fa,m_est);
    }


}
