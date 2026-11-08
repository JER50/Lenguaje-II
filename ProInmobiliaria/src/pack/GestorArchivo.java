package pack;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GestorArchivo {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path carpeta = Path.of("propiedades");

    public static void guardadoJSON(Propiedad prop) {
        Path archivo = carpeta.resolve(prop.getNombre());

        try (FileWriter writer = new FileWriter(archivo.toFile(), StandardCharsets.UTF_8)) {
            gson.toJson(prop, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Propiedad cargarJSON(String rutaArchivo) {
        Path archivo = carpeta.resolve(rutaArchivo);
        try {
            FileReader json = new FileReader(archivo.toFile(), StandardCharsets.UTF_8);
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