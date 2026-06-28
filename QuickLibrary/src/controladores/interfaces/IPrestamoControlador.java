package controladores.interfaces;

import modelos.Solicitud;

public interface IPrestamoControlador {
    void registrarSolicitud(Solicitud solicitud);
    void atenderSiguienteSolicitud();
    void registrarDevolucion(int codigoLibro);
}
