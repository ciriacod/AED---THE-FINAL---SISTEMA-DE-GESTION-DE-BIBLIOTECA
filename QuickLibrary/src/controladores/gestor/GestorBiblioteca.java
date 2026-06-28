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

    // === Modulo IPrestamoControlador ===

    @Override
    public void registrarSolicitud(SolicitudPrestamo solicitud) {
        if (solicitud == null) {
            System.out.println("Solicitud invalida");
            return;
        }
        
        Libro libro = buscarLibroPorCodigo(solicitud.getCodigoLibro());
        if (libro == null) {
            System.out.println("El libro solicitado no existe en el catalogo");
            return;
        }
        
        colaSolicitudes.enqueue(solicitud);
        System.out.println("Solicitud en espera para el estudiante: " + solicitud.getNombreEstudiante());  // Si no se va a registrar un estudiante se borra
    }

    @Override
    public void atenderSiguienteSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes");
            return;
        }

        // Desencolamos respetando estrictamente el orden FIFO
        SolicitudPrestamo siguienteSol = colaSolicitudes.dequeue();
        Libro libro = buscarLibroPorCodigo(siguienteSol.getCodigoLibro());

        // Verificamos disponibilidad
        if (libro != null && "Disponible".equalsIgnoreCase(libro.getEstado())) {
            libro.setEstado("Prestado");
            System.out.println("Prestamo aprobado. Libro '" + libro.getTitulo() + 
                               "' entregado a " + siguienteSol.getNombreEstudiante());
        } else {
            System.out.println("El libro con codigo " + siguienteSol.getCodigoLibro() + 
                               " ya se encuentra prestado o no esta disponible");
        }
    }

    @Override
    public void registrarDevolucion(int codigoLibro) {
        Libro libro = buscarLibroPorCodigo(codigoLibro);
        if (libro == null) {
            System.out.println("El libro con codigo " + codigoLibro + " no pertenece al catalogo.");
            return;
        }

        if ("Disponible".equalsIgnoreCase(libro.getEstado())) {
            System.out.println("El libro ya se encontraba como Disponible");
            return;
        }

        libro.setEstado("Disponible");
        System.out.println("Devolucion procesada. El libro '" + libro.getTitulo() + "' vuelve a estar Disponible");
    }
}
