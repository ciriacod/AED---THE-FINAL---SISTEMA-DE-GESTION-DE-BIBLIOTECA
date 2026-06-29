package controladores.interfaces;

import modelos.Solicitud;

public interface IPrestamoControlador {
    void registrarSolicitud(Solicitud solicitud);
    void mostrarColaSolicitudes();
    Solicitud consultarSiguienteSolicitud();
    void atenderSiguienteSolicitud();
    // Faltaria el metodo para Eliminar una solicitud atendida de la cola
}
