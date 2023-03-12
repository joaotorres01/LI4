package Model;

public class MediaReviews {
    private double preservacao;
    private double experiencia;
    private double facilidadeAcesso;
    private double estetica;
    private double media;

    private double round (double value) {
        int scale = (int) Math.pow(10, 1);
        return (double) Math.round(value * scale) / scale;
    }


    public MediaReviews(double preservacao, double experiencia, double facilidadeAcesso, double estetica) {
        this.preservacao = round(preservacao);
        this.experiencia = round(experiencia);
        this.facilidadeAcesso = round(facilidadeAcesso);
        this.estetica = round(estetica);
        this.media = (preservacao + estetica +experiencia + facilidadeAcesso)/4;
        this.media= round(this.media);
    }
}
