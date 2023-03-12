package Model;

import java.sql.Time;

public class Horario {
    private int dia;
    private Time abertura, fecho;


    public Horario(int dia, Time abertura, Time fechada) {
        this.dia = dia;
        this.abertura = abertura;
        this.fecho = fechada;
    }
}
