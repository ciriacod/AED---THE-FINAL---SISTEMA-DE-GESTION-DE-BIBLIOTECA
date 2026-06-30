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
        // Carga inicial automatizada desde test.csv
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
                        buscarLibroPorCodigo();
                        break;
                    case 4:
                        buscarLibrosPorFiltros();
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
                        mostrarReporteYExportar();
                        break;
                    case 12:
                        salir();
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                opcion = 0;
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
                opcion = 0;
            }
            
            if (opcion != 12) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 12);
    }

    private static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println("         QUICK LIBRARY - MENÚ");
        System.out.println("==========================================");
        System.out.println(" 1. Registrar libro");
        System.out.println(" 2. Mostrar libros");
        System.out.println(" 3. Buscar libro por código");
        System.out.println(" 4. Buscar libros por otros criterios (Submenú)");
        System.out.println(" 5. Modificar libro");
        System.out.println(" 6. Eliminar libro");
        System.out.println(" 7. Registrar solicitud de préstamo");
        System.out.println(" 8. Mostrar cola de solicitudes");
        System.out.println(" 9. Atender siguiente solicitud");
        System.out.println("10. Registrar devolución");
        System.out.println("11. Mostrar reporte");
        System.out.println("12. Salir");
        System.out.println("==========================================");
    }

    private static void registrarLibro() {
        System.out.println("\n--- REGISTRAR LIBRO ---");
        try {
            System.out.print("Código (Entero): ");
            int codigo = Integer.parseInt(scanner.nextLine());

            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Autor: ");
            String autor = scanner.nextLine();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine();

            System.out.print("Año de publicación: ");
            int anio = Integer.parseInt(scanner.nextLine());

            Libro libro = new Libro(codigo, titulo, autor, categoria, anio);
            gestor.registrarLibro(libro);
        } catch (NumberFormatException e) {
            System.out.println("Error: Código y Año deben ser numéricos.");
        }
    }

    private static void mostrarLibros() {
        gestor.mostrarTodosLosLibros();
    }

    private static void buscarLibroPorCodigo() {
        System.out.println("\n--- BUSCAR LIBRO POR CÓDIGO ---");
        try {
            System.out.print("Ingrese el código del libro: ");
            int codigo = Integer.parseInt(scanner.nextLine());

            Libro libro = gestor.buscarLibroPorCodigo(codigo);
            if (libro == null) {
                System.out.println("Libro no encontrado (Código inexistente).");
            } else {
                System.out.println("\nLibro Encontrado:");
                System.out.println(libro);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El código debe ser un número entero.");
        }
    }

    private static void buscarLibrosPorFiltros() {
        System.out.println("\n--- CRITERIOS DE BÚSQUEDA ---");
        System.out.println("1. Por Categoría");
        System.out.println("2. Por Título");
        System.out.println("3. Por Autor");
        System.out.println("4. Mostrar solo Disponibles");
        System.out.println("5. Mostrar solo Prestados");
        System.out.print("Seleccione un criterio: ");
        
        try {
            int subOpcion = Integer.parseInt(scanner.nextLine());
            switch (subOpcion) {
                case 1:
                    System.out.print("Ingrese la categoría: ");
                    gestor.buscarLibroPorCategoria(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Ingrese el título: ");
                    gestor.buscarLibroPorTitulo(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Ingrese el autor: ");
                    gestor.buscarLibroPorAutor(scanner.nextLine());
                    break;
                case 4:
                    System.out.println("\n--- LIBROS DISPONIBLES ---");
                    gestor.mostrarLibrosDisponibles();
                    break;
                case 5:
                    System.out.println("\n--- LIBROS PRESTADOS ---");
                    gestor.mostrarLibrosPrestados();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar una opción numérica.");
        }
    }

    private static void modificarLibro() {
        System.out.println("\n--- MODIFICAR LIBRO ---");
        try {
            System.out.print("Código del libro a modificar: ");
            int codigo = Integer.parseInt(scanner.nextLine());

            Libro libro = gestor.buscarLibroPorCodigo(codigo);
            if (libro == null) {
                System.out.println("El libro con código " + codigo + " no existe.");
                return;
            }

            System.out.print("Nuevo título: ");
            String titulo = scanner.nextLine();
            System.out.print("Nuevo autor: ");
            String autor = scanner.nextLine();
            System.out.print("Nueva categoría: ");
            String categoria = scanner.nextLine();
            System.out.print("Nuevo año: ");
            int anio = Integer.parseInt(scanner.nextLine());

            gestor.modificarLibro(codigo, titulo, autor, categoria, anio);
        } catch (NumberFormatException e) {
            System.out.println("Error: El código y el año deben ser números enteros.");
        }
    }

    private static void eliminarLibro() {
        System.out.println("\n--- ELIMINAR LIBRO ---");
        try {
            System.out.print("Código del libro a eliminar: ");
            int codigo = Integer.parseInt(scanner.nextLine());
            gestor.eliminarLibro(codigo);
        } catch (NumberFormatException e) {
            System.out.println("Error: El código debe ser numérico.");
        }
    }

    private static void registrarSolicitud() {
        System.out.println("\n--- REGISTRAR SOLICITUD DE PRÉSTAMO ---");
        try {
            System.out.print("Código del estudiante: ");
            String codigoEst = scanner.nextLine();

            System.out.print("Nombre del estudiante: ");
            String nombreEst = scanner.nextLine();

            System.out.print("Código del libro solicitado: ");
            int codigoLibro = Integer.parseInt(scanner.nextLine());

            Solicitud solicitud = new Solicitud(codigoEst, nombreEst, codigoLibro, new Date());
            gestor.registrarSolicitud(solicitud);
        } catch (NumberFormatException e) {
            System.out.println("Error: El código del libro debe ser numérico.");
        }
    }

    private static void mostrarSolicitudes() {
        gestor.mostrarColaSolicitudes();
    }

    private static void atenderSolicitud() {
        System.out.println("\n--- ATENDIENDO SIGUIENTE SOLICITUD ---");
        gestor.atenderSiguienteSolicitud();
    }

    private static void registrarDevolucion() {
        System.out.println("\n--- REGISTRAR DEVOLUCIÓN ---");
        try {
            System.out.print("Código del libro que se devuelve: ");
            int codigo = Integer.parseInt(scanner.nextLine());
            gestor.registrarDevolucion(codigo);
        } catch (NumberFormatException e) {
            System.out.println("Error: El código del libro debe ser numérico.");
        }
    }

    private static void mostrarReporteYExportar() {
        // Imprime el reporte estadístico obligatorio en consola
        gestor.generarReporteEstadistico();
        
        // Ofrece la funcionalidad opcional de exportar
        System.out.print("\n¿Desea exportar una copia del catálogo actual a un archivo CSV? (S/N): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.print("Ingrese el nombre del archivo: ");
            String nombreArchivo = scanner.nextLine().trim();
            
            // Solución al problema de extensión: si el usuario escribe "reporte.csv", 
            // se limpia para que al concatenar ".csv" en tu GestorBiblioteca no quede "reporte.csv.csv"
            if (nombreArchivo.toLowerCase().endsWith(".csv")) {
                nombreArchivo = nombreArchivo.substring(0, nombreArchivo.length() - 4);
            }
            
            if (nombreArchivo.isEmpty()) {
                System.out.println("Nombre inválido. Operación cancelada.");
                return;
            }

            gestor.exportarDatos(nombreArchivo);
            System.out.println("Catálogo exportado exitosamente como: " + nombreArchivo + ".csv");
        }
    }

    private static void salir() {
        // Guarda automáticamente en "test.csv" antes de finalizar
        gestor.guardarDatos();
        System.out.println("\nDatos guardados en el sistema. Gracias por usar QuickLibrary.");
    }
}