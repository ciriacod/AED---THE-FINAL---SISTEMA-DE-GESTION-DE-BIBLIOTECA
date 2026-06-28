package controladores;

import estructuras.ArbolAVL;
import estructuras.LinkedQueue;

public class GestionBiblioteca_prueba {
    private ArbolAVL<Libro> catalogoLibros;
    private LinkedQueue<Solicitud> colaSolicitudes;

    public GestionBiblioteca() {
        this.catalogoLibros = new ArbolAVL<>();
        this.colaSolicitudes = new LinkedQueue<>();
    }

    public void registrarLibro(Libro libro) {
        if (libro == null) return;
        if (buscarLibroPorCodigo(libro.getCodigo()) != null) {
            System.out.println("El libro con codigo " + libro.getCodigo() + " ya existe.");
            return;
        }
        catalogoLibros.insert(libro);
        System.out.println("Libro registrado con exito: " + libro.getTitulo());
    }

    public Libro buscarLibroPorCodigo(int codigo) {
        Libro pivote = new Libro(codigo, "", "", "", 0, "");
        return catalogoLibros.search(pivote);
    }
    public void registrarSolicitud(Solicitud solicitud) {
        colaSolicitudes.enqueue(solicitud);
        System.out.println("Solicitud añadida a la cola para el estudiante: " + solicitud.getNombreEstudiante());
    }
    public void atenderSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes en cola.");
            return;
        }
        Solicitud prox = colaSolicitudes.dequeue();
        Libro lib = buscarLibroPorCodigo(prox.getCodigoLibro());

        if (lib != null && "Disponible".equalsIgnoreCase(lib.getEstado())) {
            lib.setEstado("Prestado");
            System.out.println("Prestamo aprobado para: " + prox.getNombreEstudiante());
        } else {
            System.out.println("No se pudo procesar el prestamo /no esta disponible");
        }
    }
    public void mostrarReporteBasico() {
        System.out.println("\n=REPORTE BÁSICO QUICKLIBRARY ");
        System.out.println("Cantidad total de libros en catalogo: " + catalogoLibros.countAllNodes());
        System.out.println("Solicitudes pendientes en la cola: " + colaSolicitudes.size());
    }
}
