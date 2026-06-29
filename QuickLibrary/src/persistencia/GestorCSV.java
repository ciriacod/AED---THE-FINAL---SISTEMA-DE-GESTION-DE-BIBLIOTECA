package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import estructuras.ArbolAVL;
import estructuras.LinkedQueue;
import estructuras.NodoAVL;

import java.nio.file.Files;
import java.nio.file.Paths;
import modelos.Libro;

public class GestorCSV {
    private final Path rutaPredeterminada;

    public GestorCSV(String rutaPredeterminada){
        this.rutaPredeterminada = Paths.get(rutaPredeterminada);
    }

//----------------------------Cargar------------------------------------

    public LinkedQueue<Libro> cargarLibros(){
        LinkedQueue<Libro> libreriaCargada = new LinkedQueue<>();

        if (!Files.exists(rutaPredeterminada)) {
            return libreriaCargada;     //ruta predeterminada no disponible, creando nueva;
        }
        
        try (BufferedReader br = Files.newBufferedReader(rutaPredeterminada, StandardCharsets.UTF_8)){
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] campos = linea.split(",");
                if (campos.length >= 5) {
                    try {
                        int codigo = Integer.parseInt(campos[0].trim());
                        String titulo = campos[1].trim();
                        String autor = campos[2].trim();
                        String categoria = campos[3].trim();
                        int anio = Integer.parseInt(campos[4].trim());
                        if (campos.length >= 6 &&!campos[5].trim().isEmpty()) { // con estado
                            String estado = campos[5].trim();
                            libreriaCargada.enqueue(new Libro(codigo, titulo, autor, categoria, anio, estado));
                        } else{                                                 // sin estado
                            libreriaCargada.enqueue(new Libro(codigo, titulo, autor, categoria, anio));
                        }
                    } catch (Exception e) {
                        System.out.println("Error al procesar linea en CSV " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al procer CSV " + e.getMessage());
        }

        return libreriaCargada;
    }

//----------------------------Exportar------------------------------------

    public void guardarLibros(ArbolAVL<Libro> catalogoLibros){
        if (catalogoLibros == null || catalogoLibros.estaVacio()) {
            System.out.println("Arbol Vacio");
            return;
        }

        try (BufferedWriter bw = Files.newBufferedWriter(rutaPredeterminada, StandardCharsets.UTF_8)){
            LinkedQueue<Libro> colaTemp = new LinkedQueue<>();
            
            // convertir el arbol en cola para mejor manejo
            conversorAVLtoQueueInOrder(catalogoLibros.getRaiz(), colaTemp);
            
            // escribir elementos en archivo

            while (!colaTemp.isEmpty()) {
                Libro libro = colaTemp.dequeue();

                String linea = String.format("%d,%s,%s,%s,%d,%s",
                    libro.getCodigo(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getCategoria(),
                    libro.getAnio(),
                    libro.getEstado()
                );

                bw.write(linea);
                bw.newLine();
            }

            System.out.println("Exito");

        } catch (IOException e) {
            System.out.println("No Exito " + e.getMessage());
        }
    }

    private void conversorAVLtoQueueInOrder(NodoAVL<Libro> nodo, LinkedQueue<Libro> cola){
        if (nodo != null) {
            conversorAVLtoQueueInOrder(nodo.getIzquierdo(), cola);
            cola.enqueue(nodo.getDato());
            conversorAVLtoQueueInOrder(nodo.getDerecho(), cola);
        }
    }
}
