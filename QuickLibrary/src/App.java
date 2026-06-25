import java.util.Scanner;
import estructuras.LinkedBST;
import estructuras.LinkedQueue;
import modelos.Libro;
import modelos.Solicitud;

public class App {
    private static LinkedBST<Libro> bstLibros = new LinkedBST<>();
    private static LinkedQueue<Solicitud> colaSolicitudes = new LinkedQueue<>();
    private static Scanner scanner = new Scanner(System.String.getScanner() != null ? System.getScanner() : new Scanner(System.in));

    public static void main(String[] args) {
        int opcion = 0;
        do {
            try {
                mostrarMenu();
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        registrarLibro();
                        break;
                    case 2:
                        System.out.println("\n CATÁLOGO DE LIBROS ");
                        bstLibros.inOrder();
                        break;
                    case 3:
                        buscarLibroPorCodigo();
                        break;
                    case 7:
                        registrarSolicitud();
                        break;
                    case 8:
                        System.out.println("\n COLA DE SOLICITUDES PENDIENTES");
                        colaSolicitudes.mostrar();
                        break;
                    case 11:
                        mostrarReporte();
                        break;
                    case 12:
                        System.out.println("Saliendo de QuickLibrary...");
                        break;
                    default:
                        System.out.println("Opción no válida o aún en desarrollo por el equipo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Se solicita ingresar un número válido.");
            }
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine();
        } while (opcion != 12);
    }

    private static void mostrarMenu() {
        System.out.println("          QUICKLIBRARY           ");
        System.out.println("1. Registrar libro");
        System.out.println("2. Mostrar libros");
        System.out.println("3. Buscar libro por código");
        System.out.println("4. Buscar libros por categoría");
        System.out.println("5. Modificar libro");
        System.out.println("6. Eliminar libro");
        System.out.println("7. Registrar solicitud de préstamo");
        System.out.println("8. Mostrar cola de solicitudes");
        System.out.println("9. Atender siguiente solicitud");
        System.out.println("10. Registrar devolución");
        System.out.println("11. Mostrar reporte");
        System.out.println("12. Salir");
    }
    private static void registrarLibro() {
        System.out.println("\n REGISTRAR NUEVO LIBRO");
        System.out.print("Código numerico: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Año de publicación: ");
        int anio = Integer.parseInt(scanner.nextLine());

        Libro nuevoLibro = new Libro(codigo, titulo, autor, categoria, anio, "Disponible");
        bstLibros.insert(nuevoLibro);
        System.out.println("Libro guardado exitosamente");
    }

    private static void buscarLibroPorCodigo() {
        System.out.println("\n BUSCAR LIBRO ");
        System.out.print("Ingrese el código a buscar: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        Libro molde = new Libro(codigo, "", "", "", 0, "");
        Libro encontrado = bstLibros.search(molde);

        if (encontrado != null) {
            System.out.println("Libro Encontrado: " + encontrado);
        } else {
            System.out.println("El libro con código " + codigo + " no existe en el sistema.");
        }
    }

    private static void registrarSolicitud() {
        System.out.println("\n  REGISTRAR SOLICITUD DE PRÉSTAMO ");
        System.out.print("Código del estudiante: ");
        String codEst = scanner.nextLine();
        System.out.print("Nombre del estudiante: ");
        String nomEst = scanner.nextLine();
        System.out.print("Código del libro solicitado: ");
        int codLibro = Integer.parseInt(scanner.nextLine());

        Solicitud nuevaSolicitud = new Solicitud(codEst, nomEst, codLibro, "24/06/2026");
        colaSolicitudes.enqueue(nuevaSolicitud);
        System.out.println("¡Solicitud encolada correctamente!");
    }

    private static void mostrarReporte() {
        System.out.println("        REPORTE GENERAL          ");
        System.out.println("Cantidad total de libros: " + bstLibros.countAllNodes());
        System.out.println("Solicitudes pendientes en cola: " + colaSolicitudes.size());
    }
}
