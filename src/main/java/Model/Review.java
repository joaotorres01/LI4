package Model;

import java.sql.Date;

public class Review {
    private String user;
    private String centro;
    private double preservacao;
    private double experiencia;
    private double facilidadeAcesso;
    private double estetica;
    private Date dia;


    public Review(String user, String centro, double preservacao, double experiencia, double facilidadeAcesso, double estetica, Date dia) {
        this.user = user;
        this.centro = centro;
        this.preservacao = preservacao;
        this.experiencia = experiencia;
        this.facilidadeAcesso = facilidadeAcesso;
        this.estetica = estetica;
        this.dia = dia;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public double getPreservacao() {
        return preservacao;
    }

    public void setPreservacao(double preservacao) {
        this.preservacao = preservacao;
    }

    public double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(double experiencia) {
        this.experiencia = experiencia;
    }

    public double getFacilidadeAcesso() {
        return facilidadeAcesso;
    }

    public void setFacilidadeAcesso(double facilidadeAcesso) {
        this.facilidadeAcesso = facilidadeAcesso;
    }

    public double getEstetica() {
        return estetica;
    }

    public void setEstetica(double estetica) {
        this.estetica = estetica;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }
}