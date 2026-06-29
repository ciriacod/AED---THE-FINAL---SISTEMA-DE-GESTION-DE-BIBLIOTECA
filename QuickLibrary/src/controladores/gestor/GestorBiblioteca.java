package controladores.gestor;

import controladores.interfaces.ILibroControlador;
import controladores.interfaces.IPrestamoControlador;
import controladores.interfaces.IReporteControlador;
import estructuras.ArbolAVL;
import estructuras.LinkedQueue;
import estructuras.NodoAVL;
import modelos.Libro;
import modelos.Solicitud;
import persistencia.GestorCSV;

public class GestorBiblioteca implements ILibroControlador, IPrestamoControlador, IReporteControlador {
    
    /*
    ILibroControlador - TODO DE LIBRO
    IPrestamoControlador - TODO DE TRANSACCIONES
    IReporteControlador - GENERA REPORTE
    */

    // Dependemos de las abstracciones de las estructuras
    private final ArbolAVL<Libro> catalogoLibros;
    private final LinkedQueue<Solicitud> colaSolicitudes;
    private final GestorCSV gestorCSV;

    // Constructor que recibe las estructuras
    public GestorBiblioteca(ArbolAVL<Libro> catalogoLibros, LinkedQueue<Solicitud> colaSolicitudes) {
        this.catalogoLibros = catalogoLibros;
        this.colaSolicitudes = colaSolicitudes;
        this.gestorCSV = new GestorCSV("test.csv");
    }

    // Metodo para la carga de libros iniciales
    public void cargarDatosIniciales(){
        LinkedQueue<Libro> librosCargados = gestorCSV.cargarLibros();

        int cont = 0;
        while (!librosCargados.isEmpty()) {
            Libro libro = librosCargados.dequeue();
            registrarLibro(libro);
            cont++;
        }

        System.out.println("Libros cargados: " + cont);
    }

    // Metodo para la guardar de libros
    public void guardarDatos(){
        gestorCSV.guardarLibros(catalogoLibros);
    }
    
    // test exportacion
    public void exportarDatos(String ruta){
        new GestorCSV(ruta).guardarLibros(catalogoLibros);
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
    public void mostrarTodosLosLibros() {
        if (catalogoLibros.isEmpty()) {
            System.out.println("Catálogo vacío.");
            return;
        }
        System.out.println("\n--- CATÁLOGO COMPLETO ---");
        // Accion del AVL
        catalogoLibros.recorridoInorden(); // Muestra ordenados por código ascendentemente
    }

    @Override
    public void modificarLibro(int codigo, String nuevoTitulo, String nuevoAutor, String nuevaCategoria, int nuevoAnio) {
        Libro libro = buscarLibroPorCodigo(codigo);
        if (libro == null) {
            System.out.println("El libro con código " + codigo + " no existe.");
            return;
        }
        
        libro.setTitulo(nuevoTitulo);
        libro.setAutor(nuevoAutor);
        libro.setCategoria(nuevaCategoria);
        libro.setAnio(nuevoAnio);
        System.out.println("Datos del libro modificados exitosamente.");
    }

    @Override
    public void eliminarLibro(int codigo) {
        Libro libro = buscarLibroPorCodigo(codigo);
        if (libro == null) {
            System.out.println("No se puede eliminar. El libro no existe.");
            return;
        }
        catalogoLibros.eliminar(libro);
        System.out.println("Libro eliminado: " + libro.getTitulo());
    }

    @Override
    public Libro buscarLibroPorCodigo(int codigo) {
        Libro actual = new Libro(codigo, "", "", "", 0);  // Vacio por siaca
        return catalogoLibros.buscar(actual);
    }

    @Override
    
    public void buscarLibroPorTitulo(String titulo) {
        buscarLibroPorTitulo(catalogoLibros.getRaiz(), titulo);
    }

    private void buscarLibroPorTitulo(NodoAVL<Libro> nodo, String titulo) {

        if (nodo == null)
            return;

        buscarLibroPorTitulo(nodo.getIzquierdo(), titulo);

        if (nodo.getDato().getTitulo().equalsIgnoreCase(titulo)) {
            System.out.println(nodo.getDato());
        }

        buscarLibroPorTitulo(nodo.getDerecho(), titulo);
    }

    @Override
    public void buscarLibroPorAutor(String autor) {
        buscarLibroPorAutor(catalogoLibros.getRaiz(), autor);
    }

    private void buscarLibroPorAutor(NodoAVL<Libro> nodo, String autor) {

        if (nodo == null)
            return;

        buscarLibroPorAutor(nodo.getIzquierdo(), autor);

        if (nodo.getDato().getAutor().equalsIgnoreCase(autor)) {
            System.out.println(nodo.getDato());
        }

        buscarLibroPorAutor(nodo.getDerecho(), autor);
    }

    @Override
    public void buscarLibroPorCategoria(String categoria) {
        buscarLibroPorCategoria(catalogoLibros.getRaiz(), categoria);
    }

    private void buscarLibroPorCategoria(NodoAVL<Libro> nodo, String categoria) {

        if (nodo == null)
            return;

        buscarLibroPorCategoria(nodo.getIzquierdo(), categoria);

        if (nodo.getDato().getCategoria().equalsIgnoreCase(categoria)) {
            System.out.println(nodo.getDato());
        }

        buscarLibroPorCategoria(nodo.getDerecho(), categoria);
    }

    @Override
    public void mostrarLibrosDisponibles() {
        mostrarLibrosDisponibles(catalogoLibros.getRaiz());
    }

    private void mostrarLibrosDisponibles(NodoAVL<Libro> nodo) {

        if (nodo == null)
            return;

        mostrarLibrosDisponibles(nodo.getIzquierdo());

        if (nodo.getDato().getEstado().equalsIgnoreCase("Disponible")) {
            System.out.println(nodo.getDato());
        }

        mostrarLibrosDisponibles(nodo.getDerecho());
    }

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
        System.out.println("Solicitud en espera para el estudiante: " + solicitud.getNombreEstudiante()); 
    }

    @Override
    public void mostrarColaSolicitudes() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes en la cola de espera.");
            return;
        }
        System.out.println("\n--- COLA DE SOLICITUDES PENDIENTES ---");
        colaSolicitudes.mostrar(); 
    }

    @Override
    public Solicitud consultarSiguienteSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return null;
        }
        return colaSolicitudes.peek(); 
    }

    @Override
    public void atenderSiguienteSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes");
            return;
        }

        // Retirar la solicitud de la cola
        Solicitud siguienteSol = colaSolicitudes.dequeue();
        // Verificar que el libro exista
        Libro libro = buscarLibroPorCodigo(siguienteSol.getCodigoLibro());
        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }
        // Comprobar que el libro este disponible
        if ("Disponible".equalsIgnoreCase(libro.getEstado())) {
            // Cambiar su estado a prestado
            libro.setEstado("Prestado");
            // Mostrar un mensaje con el resultado de la operacion
            System.out.println("Prestamos aprobado. Libro '" + libro.getTitulo() + 
                               "' entregado a " + siguienteSol.getNombreEstudiante());
        } else {
            System.out.println("OPERACIÓN RECHAZADA: El libro '" + libro.getTitulo() + "' ya se encuentra prestado.");
        }
    }

    @Override
    public void registrarDevolucion(int codigoLibro) {
        // Registrar la devolucion de un libro
        Libro libro = buscarLibroPorCodigo(codigoLibro);
        if (libro == null) {
            System.out.println("El libro con codigo " + codigoLibro + " no pertenece al catalogo");
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
