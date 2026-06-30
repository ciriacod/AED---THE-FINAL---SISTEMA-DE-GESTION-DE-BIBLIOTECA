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
    ILibroControlador - Operaciones de Libro
    IPrestamoControlador - Operaciones de transacciones
    IReporteControlador - Solo genera reporte
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
        ArbolAVL<Libro> librosCargados = gestorCSV.cargarLibros();
        catalogoLibros.setRaiz(librosCargados.getRaiz());
    }

    // Metodo para la guardar de libros
    public void guardarDatos(){
        gestorCSV.guardarLibros(catalogoLibros);
    }
    
    // test exportacion
    public void exportarDatos(String ruta){
        new GestorCSV(ruta+".csv").guardarLibros(catalogoLibros);
    }

    // === Modulo ILibroControlador ===
    
    @Override
    public void registrarLibro(Libro libro) {
        if (libro == null) {
            System.out.println("No hay libro");
            return;
        }
        // El arbol se encarga de buscar si ya existe el codigo internamente de igual manera se validara aqui
        Libro libroExistente = buscarLibroPorCodigo(libro.getCodigo());
        if (libroExistente != null) {
            libroExistente.setStock(libroExistente.getStock() + libro.getStock());
            // Si el libro estaba agotado ("Prestado") y se añade stock, vuelve a estar disponible
            if (libroExistente.getStock() > 0) {
                libroExistente.setEstado("Disponible");
            }
            System.out.println("Libro ya existente. Se incrementó el stock a: " + libroExistente.getStock());
            return;
        }
        
        catalogoLibros.insertar(libro);
        System.out.println("Libro registrado: " + libro.getTitulo());
    }

    @Override
    public void mostrarTodosLosLibros() {
        if (catalogoLibros.estaVacio()) {
            System.out.println("Catálogo vacío.");
            return;
        }
        System.out.println("\n--- CATÁLOGO COMPLETO ---");
        // Accion del AVL
        catalogoLibros.inOrden(); // Muestra ordenados por código ascendentemente
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

        if (libro.getStock()>1) {
            libro.setStock(libro.getStock()-1);
            System.out.println("1 libro eliminado de stock");
        } else {
            catalogoLibros.eliminar(libro);
            System.out.println("Libro eliminado: " + libro.getTitulo());
        }
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
    public void mostrarLibrosPrestados() {
        mostrarLibrosPrestados(catalogoLibros.getRaiz());
    }

    private void mostrarLibrosPrestados(NodoAVL<Libro> nodo) {

        if (nodo == null)
            return;

        mostrarLibrosPrestados(nodo.getIzquierdo());

        if (nodo.getDato().getEstado().equalsIgnoreCase("Prestado")) {
            System.out.println(nodo.getDato());
        }

        mostrarLibrosPrestados(nodo.getDerecho());
    }

    //Contara por el estado que se va enviar "DISPONIBLE" o "PRESTADO"

    private int contarPorEstado(NodoAVL<Libro> nodo, String estado){

        if(nodo==null)
            return 0;

        int contador=0;

        contador+=contarPorEstado(nodo.getIzquierdo(),estado);

        if(nodo.getDato().getEstado().equalsIgnoreCase(estado))
            contador++;

        contador+=contarPorEstado(nodo.getDerecho(),estado);

        return contador;
    }

    public int contarLibrosDisponibles(){

        return contarPorEstado(catalogoLibros.getRaiz(),"Disponible");

    }

    public int contarLibrosPrestados(){

        return contarPorEstado(catalogoLibros.getRaiz(),"Prestado");

    }

//---------------------------Solicitudes---------------------------------------

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
            if(libro.getStock() <= 0){
                System.out.println("Libro agotado en stock");
                return;
            }
            // Cambiar su estado a prestado
            libro.setEstado("Prestado");
            // Mostrar un mensaje con el resultado de la operacion
            System.out.println("Prestamos aprobado. Libro '" + libro.getTitulo() + 
                               "' entregado a " + siguienteSol.getNombreEstudiante());
            libro.setStock(libro.getStock() - 1);
        } else {
            System.out.println("OPERACIÓN RECHAZADA: El libro '" + libro.getTitulo() + "' ya se encuentra prestado.");
            colaSolicitudes.enqueue(siguienteSol);
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

        // Cambiar el estado del libro a disponible
        libro.setStock(libro.getStock() + 1);
        libro.setEstado("Disponible");
        // Mostrar un mensaje de confirmacion
        System.out.println("Devolucion lista. Libro: " + libro.getTitulo() + " | Stock nuevo: " + libro.getStock());
    }

    // === Modulo IReporteControlador ===

    @Override
    public void generarReporteEstadistico() {
        // Metodos q se encarga el arbol
        int disponibles = this.contarPorEstado(catalogoLibros.getRaiz(),"Disponible");
        int prestados = this.contarPorEstado(catalogoLibros.getRaiz(),"Prestado");

        System.out.println("\n======== REPORTE ESTADÍSTICO =========");
        System.out.println("Cantidad total de libros       : " + catalogoLibros.contar());
        System.out.println("Cantidad de libros disponibles : " + disponibles);
        System.out.println("Cantidad de libros prestados   : " + prestados);
        System.out.println("Cantidad de solicitudes pendientes: " + colaSolicitudes.size());
        System.out.println("========================================");
    }
}
