package modelos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;

public class LectorCSV {
    private String ruta;

    public LectorCSV(String ruta){
        this.ruta = ruta;
    }

    public void leer() {
        try(BufferedReader br = Files.newBufferedReader(Path.of(ruta), 
        StandardCharsets.UTF_8)){
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new LectorCSV("test.csv").leer();
    }
}
