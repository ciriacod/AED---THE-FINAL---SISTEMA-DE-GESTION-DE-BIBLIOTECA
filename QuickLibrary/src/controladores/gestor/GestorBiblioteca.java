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

    // === Modulo ILibroControlador ===
    
    @Override
    public void registrarLibro(Libro libro) {
        if (libro == null) {
            System.out.println("No hay libro");
            return;
        }
        // El arbol se encarga de buscar si ya existe el codigo internamente de igual manera se validara aqui
        if (buscarLibroPorCodigo(libro.getCodigo()) != null) {
            System.out.println("Libro duplicado");
            return;
        }
        catalogoLibros.insertar(libro);
        System.out.println("Libro registrado: " + libro.getTitulo());
    }

    @Override
    public Libro buscarLibroPorCodigo(int codigo) {
        Libro actual = new Libro(codigo, "", "", "", 0);  // Vacio por siaca
        return catalogoLibros.buscar(actual);
    }
}
