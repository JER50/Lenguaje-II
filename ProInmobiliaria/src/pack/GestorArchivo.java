package ProInmobiliaria.src.pack;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class GestorArchivo {
    public static void guardadoJSON(Propiedad prop, String nombre) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(nombre + ".json")) {
            gson.toJson(prop, writer);
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    public static Propiedad cargarJSON(String rutaArchivo) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            String nombre = extraerStrings(json, "nombre");
            int precio = Integer.parseInt(extraerValores(json, "precio"));
            String tipoInmueble = extraerStrings(json, "tipoInmueble");
            String tipoListado = extraerStrings(json, "tipoListado");
            String direccion = extraerStrings(json, "direccion");
            int metrosTotales = Integer.parseInt(extraerValores(json, "metrosTotales"));
            int metrosCubiertos = Integer.parseInt(extraerStrings(json, "metrosCubiertos"));
            int cantAmb = Integer.parseInt(extraerValores(json, "cantAmb"));
            int cantBaños = Integer.parseInt(extraerValores(json, "cantBaños"));
            boolean cochera = Boolean.parseBoolean(extraerValores(json, "cochera"));
            int expensas = Integer.parseInt(extraerValores(json, "expensas"));
            int antigüedad = Integer.parseInt(extraerValores(json, "antigüedad"));
            int pisos = Integer.parseInt(extraerValores(json, "pisos"));
            String descripción = extraerStrings(json, "descripción");
            int cantImagenes = Integer.parseInt(extraerValores(json, "-"));
            String nombreImagenes = extraerStrings(json, "nombreImagenes");
            double latitud = Double.parseDouble(extraerValores(json, "latitud"));
            double longitud = Double.parseDouble(extraerValores(json, "longitud"));

            return new Propiedad(nombre, precio, tipoInmueble, tipoListado, direccion, metrosTotales, metrosCubiertos, cantAmb, cantBaños, cochera, expensas, antigüedad, pisos, descripción, cantImagenes, nombreImagenes, latitud, longitud);

        } catch (NumberFormatException e) {
            System.out.println("Error: El formato numérico en el JSON está corrupto.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
}