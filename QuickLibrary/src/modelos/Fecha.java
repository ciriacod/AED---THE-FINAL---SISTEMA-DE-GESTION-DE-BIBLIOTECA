package modelos;

import java.util.Calendar;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;
    private int hora;
    private int minuto;

    public Fecha(){
        Calendar sistema = Calendar.getInstance();
        this.dia = sistema.get(Calendar.DAY_OF_MONTH);
        this.mes = sistema.get(Calendar.MONTH) + 1;
        this.anio = sistema.get(Calendar.YEAR);
        this.hora = sistema.get(Calendar.HOUR_OF_DAY);
        this.minuto = sistema.get(Calendar.MINUTE);
    }

    public Fecha(int dia, int mes, int anio, int hora, int minuto) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.hora = 0;
        this.minuto = 0;
    }

    @Override
    public String toString(){
        return String.format("%02d/%02d/%d %02d:%02d",
        dia, mes, anio, hora, minuto);
    }
    
    
}
