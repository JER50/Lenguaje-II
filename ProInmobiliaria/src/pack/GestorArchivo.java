package ProInmobiliaria.src.pack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GestorArchivo {
    public static void guardadoJSON(Propiedad prop) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        URL pathURL = GestorArchivo.class.getResource("/propiedades/" + prop.getNombre() + ".json");

        if (pathURL != null) {
            try {
                File json = new File(pathURL.toURI());
                try (FileWriter writer = new FileWriter(json, true)) {
                    gson.toJson(prop, writer);
                } catch (IOException e) {
                    System.out.println("Error al escribir: " + e.getMessage());
                }
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Propiedad cargarJSON(String rutaArchivo) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            Gson gson = new Gson();
            return gson.fromJson(json, Propiedad.class);

        } catch (NumberFormatException e) {
            System.out.println("Error: El formato numérico en el JSON está corrupto.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
}