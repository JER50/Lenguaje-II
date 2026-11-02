import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GestorArchivo {
    private static String evitaProblemas(String texto) {
        if (texto==null)
            return "";
        return texto.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static ArrayList<String> evitaProblemasListas(ArrayList<String> lista) {
        ArrayList<String> listaLimpia = new ArrayList<String>();
        if (lista==null || lista.isEmpty())
            return listaLimpia;
        for (int i = 0; i < lista.size(); i++) {
            String elemento = lista.get(i);
            if (elemento != null) {
                listaLimpia.add(elemento.replace("\\", "\\\\").replace("\"", "\\\""));
            }
        }
        return listaLimpia;
    }

    private static String extraerStrings(String json, String clave) {
        int inicio = json.indexOf("\"" + clave + "\": \"");
        if (inicio == -1) return "";
        inicio += ("\"" + clave + "\": \"").length();
        int fin = json.indexOf("\"", inicio);
        String valorConEscape = json.substring(inicio, fin);
        return valorConEscape.replace("\\\"", "\"").replace("\\\\", "\\");
    }

    private static String extraerValores(String json, String clave) {
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

    private static ArrayList<String> extraerLista(String json, String clave) {
        ArrayList<String> listaExtraida = new ArrayList<String>();
        int inicio = json.indexOf("\"" + clave + "\":");
        if (inicio == -1) return listaExtraida;
        inicio += ("\"" + clave + "\":").length();
        int corchete = json.indexOf("[", inicio);
        if (corchete == -1) return listaExtraida;
        while () {
            if (== "]") {
                break;
            }
        }

        return listaExtraida;
    }

    public static void guardadoJSON(Propiedad prop, String rutaArchivo) {
        String nombre = evitaProblemas(prop.getNombre());
        String tipoInmueble = evitaProblemas(prop.getTipoInmueble());
        String tipoListado = evitaProblemas(prop.getTipoListado());
        String direccion = evitaProblemas(prop.getDireccion());
        String descripción = evitaProblemas(prop.getDescripción());
        ArrayList<String> listaImagenes = evitaProblemasListas(prop.getImagenes());

        String json = "{\n" +
                "  \"nombre\": \"" + nombre + "\",\n" +
                "  \"precio\": " + prop.getPrecio() + ",\n" +
                "  \"tipoInmueble\": \"" + tipoInmueble + "\"\n" +
                "  \"tipoListado\": \"" + tipoListado + "\"\n" +
                "  \"direccion\": \"" + direccion + "\"\n" +
                "  \"metrosTotales\": " + prop.getMetrosTotales() + ",\n" +
                "  \"metrosCubiertos\": " + prop.getMetrosCubiertos() + ",\n" +
                "  \"cantAmb\": " + prop.getCantAmb() + ",\n" +
                "  \"cantBaños\": " + prop.getCantBaños() + ",\n" +
                "  \"cochera\": " + prop.isCochera() + ",\n" +
                "  \"expensas\": " + prop.getExpensas() + ",\n" +
                "  \"antigüedad\": " + prop.getAntigüedad() + ",\n" +
                "  \"pisos\": " + prop.getPisos() + ",\n" +
                "  \"descripción\": \"" + descripción + "\"\n" +
                "  \"listaImagenes\": \"" + listaImagenes + "\"\n" +
                "  \"latitud\": " + prop.getLatitud() + ",\n" +
                "  \"longitud\": " + prop.getLongitud() + ",\n" +
                "}";

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
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
            int metrosCubiertos = Integer.parseInt(extraerValores(json, "metrosCubiertos"));
            int cantAmb = Integer.parseInt(extraerValores(json, "cantAmb"));
            int cantBaños = Integer.parseInt(extraerValores(json, "cantBaños"));
            boolean cochera = Boolean.parseBoolean(extraerValores(json, "cochera"));
            int expensas = Integer.parseInt(extraerValores(json, "expensas"));
            int antigüedad = Integer.parseInt(extraerValores(json, "antigüedad"));
            int pisos = Integer.parseInt(extraerValores(json, "pisos"));
            String descripción = extraerStrings(json, "descripción");
            ArrayList<String> listaImagenes = extraerLista(json, "listaImagenes");
            double latitud = Double.parseDouble(extraerValores(json, "latitud"));
            double longitud = Double.parseDouble(extraerValores(json, "longitud"));

            return new Propiedad(nombre, precio, tipoInmueble, tipoListado, direccion, metrosTotales, metrosCubiertos, cantAmb, cantBaños, cochera, expensas, antigüedad, pisos, descripción, listaImagenes, latitud, longitud);

        } catch (NumberFormatException e) {
            System.out.println("Error: El formato numérico en el JSON está corrupto.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }

    public  static void borrarJSON(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        boolean eliminado = archivo.delete();

        if (eliminado) {
            System.out.println("Archivo eliminado correctamente");
        } else {
            System.out.println("El archivo no pudo ser borrado");
        }
    }
}