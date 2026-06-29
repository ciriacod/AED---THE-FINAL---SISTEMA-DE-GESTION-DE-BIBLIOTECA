package modelos;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import estructuras.LinkedQueue;

import java.nio.file.Files;

public class LectorCSV {
    private String ruta;

    public LectorCSV(String ruta){
        this.ruta = ruta;
    }

    public LinkedQueue<Libro> cargarLibros(){
        LinkedQueue<Libro> libreriaCargada = new LinkedQueue<>();

        try (BufferedReader br = ){
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return libreriaCargada;
    }

    public static void main(String[] args) {
        new LectorCSV("test.csv").leer();
    }
}
