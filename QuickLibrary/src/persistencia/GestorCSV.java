package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import estructuras.ArbolAVL;
import estructuras.LinkedQueue;
import estructuras.NodoAVL;
import modelos.Libro;

public class GestorCSV {
    private final Path rutaPredeterminada;
    public GestorCSV(String rutaPredeterminada) {
        this.rutaPredeterminada = Paths.get(rutaPredeterminada);
    }

    public LinkedQueue<Libro> cargarLibros() {
        LinkedQueue<Libro> libros = new LinkedQueue<>();
        if (!Files.exists(rutaPredeterminada)) {
            return libros;
        }

        try (BufferedReader br = Files.newBufferedReader(rutaPredeterminada, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }
                String[] campos = linea.split(",");
                if (campos.length >= 5) {
                    int codigo = Integer.parseInt(campos[0].trim());
                    String titulo = campos[1].trim();
                    String autor = campos[2].trim();
                    String categoria = campos[3].trim();
                    int anio = Integer.parseInt(campos[4].trim());

                    if (campos.length >= 6) {
                        String estado = campos[5].replace(";", "").trim();
                        libros.enqueue(
                                new Libro(
                                        codigo,
                                        titulo,
                                        autor,
                                        categoria,
                                        anio,
                                        estado
                                )
                        );
                    } else {
                        libros.enqueue(
                                new Libro(
                                        codigo,
                                        titulo,
                                        autor,
                                        categoria,
                                        anio
                                )
                        );
                    }
                }
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo CSV.", e);
        }
        return libros;
    }

    public void guardarLibros(ArbolAVL<Libro> catalogo) {
        if (catalogo == null || catalogo.estaVacio()) {
            return;
        }

        try (BufferedWriter bw = Files.newBufferedWriter(rutaPredeterminada, StandardCharsets.UTF_8)) {
            LinkedQueue<Libro> cola = new LinkedQueue<>();
            convertirAVLCola(catalogo.getRaiz(), cola);

            while (!cola.isEmpty()) {
                Libro libro = cola.dequeue();
                bw.write(String.format("%d,%s,%s,%s,%d,%s",
                        libro.getCodigo(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        libro.getCategoria(),
                        libro.getAnio(),
                        libro.getEstado()));
                bw.newLine();
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo CSV.", e);
        }
    }

    private void convertirAVLCola(NodoAVL<Libro> nodo, LinkedQueue<Libro> cola) {

        if (nodo == null) {
            return;
        }
        convertirAVLCola(nodo.getIzquierdo(), cola);
        cola.enqueue(nodo.getDato());
        convertirAVLCola(nodo.getDerecho(), cola);
    }
}
