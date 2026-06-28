package controlador.interfaces;

import modelos.SolicitudPrestamo;

public interface IPrestamoControlador {
    void registrarSolicitud(SolicitudPrestamo solicitud);
    void atenderSiguienteSolicitud();
    void registrarDevolucion(int codigoLibro);
}
