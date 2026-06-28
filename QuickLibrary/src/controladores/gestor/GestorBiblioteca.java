package controlador.impl;

import controlador.interfaces.ILibroControlador;
import controlador.interfaces.IPrestamoControlador;
import controlador.interfaces.IReporteControlador;
import estructuras.ArbolBinarioBusqueda;  // Hay q cambiar el nombre segun como coloquen el arbol
import estructuras.ColaGenerica;   // Hay q cambiar el nombre segun como coloquen la cola
import modelos.Libro;
import modelos.SolicitudPrestamo;

public class GestorBiblioteca implements ILibroControlador, IPrestamoControlador, IReporteControlador {
    
    // Dependemos de las abstracciones de las estructuras
    private final ArbolBinarioBusqueda<Libro> catalogoLibros;
    private final ColaGenerica<SolicitudPrestamo> colaSolicitudes;

    // Constructor que recibe las estructuras
    public GestorBiblioteca(ArbolBinarioBusqueda<Libro> catalogoLibros, ColaGenerica<SolicitudPrestamo> colaSolicitudes) {
        this.catalogoLibros = catalogoLibros;
        this.colaSolicitudes = colaSolicitudes;
    }

    // Metodo para la carga de libros iniciales
    public void cargarDatosIniciales() {
        // Aqui mijos colocan el meotodo o el llamado para la carga del csv :3
        System.out.println("Cargando datos guardados");
    }
}
