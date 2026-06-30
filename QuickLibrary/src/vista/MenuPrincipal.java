package vista;

import java.util.Date;
import java.util.Scanner;

import controladores.gestor.GestorBiblioteca;
import estructuras.ArbolAVL;
import estructuras.LinkedQueue;
import modelos.Libro;
import modelos.Solicitud;

public class MenuPrincipal {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArbolAVL<Libro> arbolLibros = new ArbolAVL<>();
    private static final LinkedQueue<Solicitud> colaSolicitudes = new LinkedQueue<>();

    private static final GestorBiblioteca gestor = new GestorBiblioteca(arbolLibros, colaSolicitudes);
    
    public static void main(String[] args) {
        gestor.cargarDatosIniciales();
        int opcion;

        do {
            mostrarMenu();
            System.out.print("\nSeleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        registrarLibro();
                        break;

                    case 2:
                        mostrarLibros();
                        break;
                
                    case 3:
                        buscarLibro();
                        break;
                
                    case 4:
                        buscarCategoria();
                        break;
                
                    case 5:
                        modificarLibro();
                        break;
                
                    case 6:
                        eliminarLibro();
                        break;
                
                    case 7:
                        registrarSolicitud();
                        break;
                
                    case 8:
                        mostrarSolicitudes();
                        break;
                
                    case 9:
                        atenderSolicitud();
                        break;
                
                    case 10:
                        registrarDevolucion();
                        break;
                
                    case 11:
                        mostrarReporte();
                        break;
                
                    case 12:
                        buscarTitulo();
                        break;
                
                    case 13:
                        buscarAutor();
                        break;
                
                    case 14:
                        mostrarDisponibles();
                        break;
                
                    case 15:
                        mostrarPrestados();
                        break;
                
                    case 16:
                        exportarCSV();
                        break;
                
                    case 17:
                        salir();
                        break;
                
                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (Exception e) {
                System.out.println("Debe ingresar un número válido.");
                opcion = 0;

            }
            if (opcion != 17){
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 17);
    }

    private static void mostrarMenu() {

    System.out.println("\n==========================================");
    System.out.println("         QUICK LIBRARY");
    System.out.println("==========================================");
    System.out.println(" 1. Registrar libro");
    System.out.println(" 2. Mostrar todos los libros");
    System.out.println(" 3. Buscar libro por código");
    System.out.println(" 4. Buscar libros por categoría");
    System.out.println(" 5. Modificar libro");
    System.out.println(" 6. Eliminar libro");
    System.out.println(" 7. Registrar solicitud");
    System.out.println(" 8. Mostrar cola de solicitudes");
    System.out.println(" 9. Atender siguiente solicitud");
    System.out.println("10. Registrar devolución");
    System.out.println("11. Mostrar reporte");
    System.out.println("12. Buscar por título");
    System.out.println("13. Buscar por autor");
    System.out.println("14. Mostrar libros disponibles");
    System.out.println("15. Mostrar libros prestados");
    System.out.println("16. Exportar catálogo CSV");
    System.out.println("17. Salir");
    System.out.println("==========================================");

}

    private static void registrarLibro() {

        System.out.println("\n REGISTRAR LIBRO ");

        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Anio: ");
        int anio = Integer.parseInt(scanner.nextLine());

        Libro libro = new Libro(codigo, titulo, autor, categoria, anio);

        gestor.registrarLibro(libro);
    }

    private static void mostrarLibros() {
        gestor.mostrarTodosLosLibros();
    }

    private static void buscarLibro() {

        System.out.println("\n BUSCAR LIBRO ");

        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        Libro libro = gestor.buscarLibroPorCodigo(codigo);

        if (libro == null) {

            System.out.println("Libro no encontrado.");

        } else {
            System.out.println(libro);
        }
    }
    private static void buscarCategoria() {

    System.out.println("\n--- CRITERIOS DE BÚSQUEDA ---");
    System.out.println("1. Buscar por categoría");
    System.out.println("2. Buscar por título");
    System.out.println("3. Buscar por autor");
    System.out.println("4. Mostrar disponibles");
    System.out.println("5. Mostrar prestados");

    System.out.print("Seleccione una opción: ");

    try {

        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {

            case 1:
                System.out.print("Categoría: ");
                gestor.buscarLibroPorCategoria(scanner.nextLine());
                break;

            case 2:
                System.out.print("Título: ");
                gestor.buscarLibroPorTitulo(scanner.nextLine());
                break;

            case 3:
                System.out.print("Autor: ");
                gestor.buscarLibroPorAutor(scanner.nextLine());
                break;

            case 4:
                System.out.println("\nLIBROS DISPONIBLES\n");
                gestor.mostrarLibrosDisponibles();
                break;

            case 5:
                System.out.println("\nLIBROS PRESTADOS\n");
                gestor.mostrarLibrosPrestados();
                break;

            default:
                System.out.println("Opción inválida.");
        }

    } catch (NumberFormatException e) {
        System.out.println("Debe ingresar un número.");
    }}

    private static void modificarLibro() {

        System.out.println("\n MODIFICAR LIBRO ");

        System.out.print("Codigo del libro: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        Libro libro = gestor.buscarLibroPorCodigo(codigo);

        if (libro == null) {

            System.out.println("Libro no encontrado.");
            return;

        }

        System.out.print("Nuevo titulo: ");
        String titulo = scanner.nextLine();

        System.out.print("Nuevo autor: ");
        String autor = scanner.nextLine();

        System.out.print("Nueva categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Nuevo año: ");
        int anio = Integer.parseInt(scanner.nextLine());

        gestor.modificarLibro(
                codigo,
                titulo,
                autor,
                categoria,
                anio
        );

    }
    private static void eliminarLibro() {

        System.out.println("\n ELIMINAR LIBRO ");

        System.out.print("Codigo del libro: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        gestor.eliminarLibro(codigo);

    }

    private static void registrarSolicitud() {

        System.out.println("\n NUEVA SOLICITUD ");

        System.out.print("Codigo estudiante: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre estudiante: ");
        String nombre = scanner.nextLine();

        System.out.print("Codigo del libro: ");
        int codigoLibro = Integer.parseInt(scanner.nextLine());

        Solicitud solicitud =
                new Solicitud(
                        codigo,
                        nombre,
                        codigoLibro,
                        new Date()
                );

        gestor.registrarSolicitud(solicitud);

    }

    private static void mostrarSolicitudes() {
        gestor.mostrarColaSolicitudes();
    }
    private static void atenderSolicitud() {
        gestor.atenderSiguienteSolicitud();
    }

    private static void registrarDevolucion() {
        System.out.println("\n DEVOLUCION ");
        System.out.print("Codigo del libro: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        gestor.registrarDevolucion(codigo);
    }

    private static void mostrarReporte() {

    gestor.generarReporteEstadistico();

    System.out.print("\n¿Desea exportar el catálogo? (S/N): ");
    String respuesta = scanner.nextLine();

    if (respuesta.equalsIgnoreCase("S")) {

        System.out.print("Nombre del archivo: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.toLowerCase().endsWith(".csv")) {
            nombre = nombre.substring(0, nombre.length() - 4);
        }

        if (!nombre.isEmpty()) {

            gestor.exportarDatos(nombre);

            System.out.println("Archivo exportado correctamente.");

        } else {
            System.out.println("Nombre inválido.");
        }}
    }
        
    private static void buscarTitulo() {
        System.out.println("\n BUSCAR POR TITULO ");
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        
        gestor.buscarLibroPorTitulo(titulo);
    }
    private static void buscarAutor() {
        System.out.println("\n BUSCAR POR AUTOR ");
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
            
        gestor.buscarLibroPorAutor(autor);
    
    }
    private static void mostrarDisponibles() {
        System.out.println("\n LIBROS DISPONIBLES\n");
        gestor.mostrarLibrosDisponibles();
    
    }
    private static void mostrarPrestados() {
        System.out.println("\n LIBROS PRESTADOS\n");
        gestor.mostrarLibrosPrestados();
    }
    private static void exportarCSV() {
        System.out.println("\n EXPORTAR CATALOGO ");
        System.out.print("Nombre del archivo (sin .csv): ");
        String nombre = scanner.nextLine();
        gestor.exportarDatos(nombre);
        System.out.println("Archivo exportado correctamente.");
    
    }
    private static void salir() {
        gestor.guardarDatos();
        System.out.println("\nGracias por usar QuickLibrary.");
    }
}
