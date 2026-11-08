package pack;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GestorArchivo {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    private static final Path carpetaPropiedades = Path.of(Path.of("").toAbsolutePath().toString(), "archivos", "propiedades");
    private static final Path carpetaImagenes = Path.of(Path.of("").toAbsolutePath().toString(), "archivos", "imagenes");

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
        Path archivo = carpetaPropiedades.resolve(nombre);

        try (Reader reader = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Propiedad.class);
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<String> elegirImagenes(JFrame parent) {
        ArrayList<String> nombres = new ArrayList<>();

        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new FileNameExtensionFilter("Imágenes (jpg, jpeg, png, gif)", "jpg", "jpeg", "png", "gif"));

        int resultado = chooser.showOpenDialog(parent);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            for (File f : chooser.getSelectedFiles()) {
                try {
                    Path destino = carpetaImagenes.resolve(f.getName());
                    Files.copy(f.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
                    nombres.add(Path.of("").toAbsolutePath().relativize(destino).toString().replace("\\", "/"));;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(parent, "No se pudieron copiar las imagenes: " + e.getMessage());
                }
            }
        }
        return nombres;
    }
}