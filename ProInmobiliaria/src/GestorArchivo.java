import java.io.File;
import java.io.FileWriter;
import java.nio.file.*;
import java.util.ArrayList;

public class GestorArchivo {
    private static String evitaProblemas(String texto) {
        if (texto==null)
            return "";
        texto.replace("\\", "\\\\");
        texto.replace("\"", "\\\"");
        return texto;
    }

    private static String extraerStrings(String json, String clave) {
        String patron = "\"" + clave + "\": \"";
        int inicio = json.indexOf(patron);
        if (inicio == -1) return "";
        inicio += patron.length();
        int fin = json.indexOf("\"", inicio);
        String valorConEscape = json.substring(inicio, fin);
        return valorConEscape.replace("\\\"", "\"").replace("\\\\", "\\");
    }
    private static String extraerValores(String json, String clave) {

        //String patron = "正式\"" + clave + "\":"; // Buscamos por ejemplo -> "edad":
        
        int inicio = json.indexOf("\"" + clave + "\":");
        if (inicio == -1) return "0";
        inicio += ("\"" + clave + "\":").length();
        int finComa = json.indexOf(",", inicio);
        int finLlave = json.indexOf("}", inicio);
        int finSalto = json.indexOf("\n", inicio);
        int fin = json.length();
        if (finComa != -1 && finComa < fin) fin = finComa;
        if (finLlave != -1 && finLlave < fin) fin = finLlave;
        if (finSalto != -1 && finSalto < fin) fin = finSalto;
        return json.substring(inicio, fin).trim();
    }

    public void guardadoJSON(Propiedad prop, String rutaArchivo) {
        String nombre = evitaProblemas(prop.getNombre());
        String tipoInmueble = evitaProblemas(prop.getTipoInmueble());
        String tipoListado = evitaProblemas(prop.getTipoListado());
        String direccion = evitaProblemas(prop.getDireccion());
        String descripción = evitaProblemas(prop.getDescripción());

        String json = "{\n" +
                "  \"nombre\": \"" + nombre + "\",\n" +
                "  \"precio\": \"" + prop.getPrecio() + "\",\n" +
                "  \"tipoInmueble\": \"" + tipoInmueble + "\",\n" +
                "  \"tipoListado\": \"" + tipoListado + "\",\n" +
                "  \"direccion\": \"" + direccion + "\",\n" +
                "  \"metrosTotales\": \"" + prop.getMetrosTotales() + "\",\n" +
                "  \"metrosCubiertos\": \"" + prop.getMetrosCubiertos() + "\",\n" +
                "  \"cantAmb\": \"" + prop.getCantAmb() + "\",\n" +
                "  \"cantBaños\": \"" + prop.getCantBaños() + "\",\n" +
                "  \"cochera\": \"" + prop.isCochera() + "\",\n" +
                "  \"expensas\": \"" + prop.getExpensas() + "\",\n" +
                "  \"antigüedad\": \"" + prop.getAntigüedad() + "\",\n" +
                "  \"pisos\": \"" + prop.getPisos() + "\",\n" +
                "  \"descripción\": \"" + descripción + "\",\n" +
                "  \"imagenes\": \"" + prop.getImagenes() + "\",\n" +
                "  \"latitud\": \"" + prop.getLatitud() + "\",\n" +
                "  \"longitud\": \"" + prop.getLongitud() + "\"\n" +
                "}";
        File archivo = new File(rutaArchivo, "Propiedad.json");
        int n = 1;
        while(archivo.exists()){
            archivo = new File(rutaArchivo, "Propiedad(" + n++ +").json");
        }
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(json);
        } catch (Exception e) {
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
            double latitud = Double.parseDouble(extraerValores(json, "latitud"));
            double longitud = Double.parseDouble(extraerValores(json, "longitud"));

            return new Propiedad(nombre, precio, tipoInmueble, tipoListado, direccion, metrosTotales, metrosCubiertos, cantAmb, cantBaños, cochera, expensas, antigüedad, pisos, descripción, new ArrayList<>(), latitud, longitud);

        } catch (NumberFormatException e) {
            System.out.println("Error: El formato numérico en el JSON está corrupto.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
}