package pack;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GestorArchivo {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path carpeta = Path.of(Path.of("").toAbsolutePath().toString(), "archivos", "propiedades");
    
    private static final Path carpetaPropiedades = Path.of(Path.of("").toAbsolutePath().toString(), "archivos", "propiedades");

    public static void guardadoJSON(Propiedad prop) {
        try {
            Path archivo = carpetaPropiedades.resolve(prop.getNombre() + ".json");

            try (Writer writer = Files.newBufferedWriter(archivo, StandardCharsets.UTF_8)) {
                gson.toJson(prop, writer);
            }
            System.out.println("Guardado en: " + archivo.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Propiedad cargarJSON(String nombre) {
        Path archivo = carpetaPropiedades.resolve(nombre + ".json");

        try (Reader reader = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Propiedad.class);
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
}