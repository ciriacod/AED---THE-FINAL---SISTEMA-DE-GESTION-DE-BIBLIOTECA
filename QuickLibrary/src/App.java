import java.util.Scanner;
import estructuras.LinkedBST;
import estructuras.LinkedQueue;
import modelos.Libro;
import modelos.Solicitud;

public class App {
    private static LinkedBST<Libro> bstLibros = new LinkedBST<>();
    private static LinkedQueue<Solicitud> colaSolicitudes = new LinkedQueue<>();
    private static Scanner scanner = new Scanner(System.String.getScanner() != null ? System.getScanner() : new Scanner(System.in));
