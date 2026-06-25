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
