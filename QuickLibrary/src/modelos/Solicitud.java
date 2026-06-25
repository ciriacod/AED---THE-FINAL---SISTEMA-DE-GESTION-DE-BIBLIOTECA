package modelos;

public class Solicitud {
    private String codigoEstudiante;
    private String nombreEstudiante;
    private int codigoLibro;
    private Fecha fechaSolicitud;
    
    public Solicitud(String codigoEstudiante, String nombreEstudiante, int codigoLibro) {
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

    public Fecha getFechaSolicitud() {
        return fechaSolicitud;
    }

    @Override
    public String toString() {
        return String.format("Estudiante: %s (%s) | Libro ID: %d | Solicitado: %s",
        nombreEstudiante, codigoEstudiante, codigoLibro, fechaSolicitud.toString());
    }

    

    


}
