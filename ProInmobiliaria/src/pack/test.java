package pack;

import java.nio.file.Path;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        System.out.println("Directorio de trabajo: " + Path.of("").toAbsolutePath());

        ArrayList<String> imagenes1 = new ArrayList<>();
        imagenes1.add("archivos/imagenes/casa1.jpg");
        imagenes1.add("archivos/imagenes/casa2.jpg");

        ArrayList<String> imagenes2 = new ArrayList<>();
        imagenes2.add("archivos/imagenes/depto1.jpg");

        Propiedad casa = new Propiedad(
                "Casa Centro", 150000, "Casa", "Venta",
                "Balcarce 123, Salta", 300, 180, 5, 2,
                true, 0, 10, 2,
                "Casa amplia con jardín y parrilla.",
                imagenes1, -24.7821, -65.4232);

        Propiedad depto = new Propiedad(
                "Depto Tres Cerritos", 800, "Departamento", "Alquiler",
                "Av. Reyes Católicos 456, Salta", 70, 65, 3, 1,
                false, 120, 5, 8,
                "Departamento luminoso con balcón.",
                imagenes2, -24.7900, -65.4100);

        // Guardar
        GestorArchivo.guardadoJSON(casa);
        GestorArchivo.guardadoJSON(depto);

        // Cargar
        Propiedad cargada1 = GestorArchivo.cargarJSON("Casa Centro");
        Propiedad cargada2 = GestorArchivo.cargarJSON("Depto Tres Cerritos");

        if (cargada1 != null) {
            System.out.println("Cargada: " + cargada1.getNombre());
            System.out.println("Dirección: " + cargada1.getDireccion());
            System.out.println("Imágenes: " + cargada1.getImagenes());
        } else {
            System.out.println("No se pudo cargar Casa Centro");
        }

        if (cargada2 != null) {
            System.out.println("Cargada: " + cargada2.getNombre());
            System.out.println("Precio: " + cargada2.getPrecio());
        } else {
            System.out.println("No se pudo cargar Depto Tres Cerritos");
        }
    }
}