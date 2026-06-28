package controladores.gestor;

import controladores.interfaces.ILibroControlador;
import controladores.interfaces.IPrestamoControlador;
import controladores.interfaces.IReporteControlador;
import estructuras.ArbolAVL;
import estructuras.LinkedQueue;
import modelos.Libro;
import modelos.Solicitud;

public class GestorBiblioteca implements ILibroControlador, IPrestamoControlador, IReporteControlador {
    
    /*
    ILibroControlador - TODO DE LIBRO
    IPrestamoControlador - TODO DE TRANSACCIONES
    IReporteControlador - GENERA REPORTE
    */

    // Dependemos de las abstracciones de las estructuras
    private final ArbolAVL<Libro> catalogoLibros;
    private final LinkedQueue<Solicitud> colaSolicitudes;

    // Constructor que recibe las estructuras
    public GestorBiblioteca(ArbolAVL<Libro> catalogoLibros, LinkedQueue<Solicitud> colaSolicitudes) {
        this.catalogoLibros = catalogoLibros;
        this.colaSolicitudes = colaSolicitudes;
    }

    // Metodo para la carga de libros iniciales
    public void cargarDatosIniciales() {
        // Aqui mijos colocan el meotodo o el llamado para la carga del csv :3
        System.out.println("Cargando datos guardados");
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("test.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                if (p.length >= 5) {
                    int cod = Integer.parseInt(p[0].trim());
                    String tit = p[1].trim();
                    String aut = p[2].trim();
                    String est = p[3].trim();
                    int stk = Integer.parseInt(p[4].trim());

                    Libro nuevo = new Libro(cod, tit, aut, est, stk);
                    registrarLibro(nuevo);
                }
            }
            System.out.println("Carga de datos terminada.");
        }catch (Exception e) {
            System.out.println("No se pudo cargar el CSV / no exisrte: " + e.getMessage());
        }
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
    public void registrarSolicitud(Solicitud solicitud) {
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
        Solicitud siguienteSol = colaSolicitudes.dequeue();
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

        libro.setStock(libro.getStock() + 1);
        libro.setEstado("Disponible");
        System.out.println("Devolucion lista. Libro: " + libro.getTitulo() + " | Stock nuevo: " + libro.getStock());
    }

    // === Modulo IReporteControlador ===

    @Override
    public void generarReporteEstadistico() {
        System.out.println("\n======== REPORTE ESTADISTICO =========");
        System.out.println("Libros en Catalogo: " + catalogoLibros.contar());
        System.out.println("Solicitudes en Espera: " + colaSolicitudes.size());
        System.out.println("========================================");
        // por aca se hara el reporte segun el metodo de busqda de arbol usen (inorden-preorden-postorden) o si quieren por pdf no se ya ven ustedes eso xd
    }
}
