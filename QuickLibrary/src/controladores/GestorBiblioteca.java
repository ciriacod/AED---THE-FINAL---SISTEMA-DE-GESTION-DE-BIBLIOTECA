package controladores;

import estructuras.LinkedBST;
import estructuras.LinkedQueue;

public class GestionBiblioteca {
    private LinkedBST<Libro> catalogoLibros;
    private LinkedQueue<Solicitud> colaSolicitudes;

    public GestionBiblioteca() {
        this.catalogoLibros = new LinkedBST<>();
        this.colaSolicitudes = new LinkedQueue<>();
    }

    public void registrarLibro(Libro libro) {
        catalogoLibros.insert(libro);
        System.out.println("Libro registrado con éxito: " + libro.getTitulo());
    }

    public Libro buscarLibroPorCodigo(int codigo) {
        Libro pivote = new Libro(codigo, "", "", "", 0, "");
        return catalogoLibros.search(pivote);
    }
    public void registrarSolicitud(Solicitud solicitud) {
        colaSolicitudes.enqueue(solicitud);
        System.out.println("Solicitud añadida a la cola para el estudiante: " + solicitud.getNombreEstudiante());
    }
    public void mostrarReporteBasico() {
        System.out.println("\n=REPORTE BÁSICO QUICKLIBRARY ");
        System.out.println("Cantidad total de libros en catalogo: " + catalogoLibros.countAllNodes());
        System.out.println("Solicitudes pendientes en la cola: " + colaSolicitudes.size());
    }
}
