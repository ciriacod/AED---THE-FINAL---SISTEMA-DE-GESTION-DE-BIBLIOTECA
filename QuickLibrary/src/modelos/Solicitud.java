package modelos;

import java.util.Date;

public class Solicitud {
    private String codigoEstudiante;
    private String nombreEstudiante;
    private int codigoLibro;
    private Date fechaSolicitud;
    
    public Solicitud(String codigoEstudiante, String nombreEstudiante, int codigoLibro, Date fechaSolicitud) {
        this.codigoEstudiante = codigoEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.codigoLibro = codigoLibro;
        this.fechaSolicitud = fechaSolicitud;
    }
    

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public int getCodigoLibro() {
        return codigoLibro;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }
    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public void setCodigoLibro(int codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    @Override
    public String toString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fechaFormateada = (fechaSolicitud != null) ? sdf.format(fechaSolicitud) : "Sin fecha";
        
        return String.format("Estudiante: %s [%s] | Libro ID: %d | Fecha: %s",
            nombreEstudiante, codigoEstudiante, codigoLibro, fechaFormateada
        );
    }
}
