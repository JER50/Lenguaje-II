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

//Clase para manipular archivos: imagenes y .json (Propiedades)
public class GestorArchivo {
	
	//Objeto Gson. Para la manipulación de los archivos .json
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    //Direcciones de carpetas para almacenar cada tipo de archivo. Son absolutas al directorio de trabajo (donde se inicia el programa)
    private static final Path carpetaPropiedades = Path.of(Path.of("").toAbsolutePath().toString(), "archivos", "propiedades");
    private static final Path carpetaImagenes = Path.of(Path.of("").toAbsolutePath().toString(), "archivos", "imagenes");

    //Para guardar cada .json en la carpeta. El objeto Gson solo necesita el objeto Propiedad y el archivo donde se guardará
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
    
    
    //Para borrar cada .json de la carpeta y sus imagenes asociadas. Utiliza el objeto Propiedad para ver si está utilizando la imagen
    //default y evitar eliminarla, y busca el archivo por el nombre asignado en sus propiedades. 
    public static void borradoJSON(Propiedad propiedad) {
    	Path eliminando = carpetaPropiedades.resolve(propiedad.getNombre() + ".json");
    	
    	try {
    	    Files.delete(eliminando);
    	    
    	    if(!propiedad.getImagenes().get(0).equals("archivos/imagenes/default.png")) {
	    	    
    	    	for(String img : propiedad.getImagenes()) {
	    	    	System.out.print(carpetaImagenes.resolve(img));
	    	    	Files.delete(Path.of("").toAbsolutePath().resolve(img));
	    	    }
    	    	
    	    }
    	    
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
    
    //Para extraer la información de un archivo y devolver el objeto correspondiente. Gson solo requiere el archivo del cual extraer y
    //la clase a la cual corresponderá el objeto creado (Que en este caso es uno del tipo Propiedad). 
    public static Propiedad cargarJSON(String nombre) {
        Path archivo = carpetaPropiedades.resolve(nombre);

        try (Reader reader = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Propiedad.class);
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
    
    //Esta función se utiliza para cargar imagenes a la carpeta /archivos/imagenes, de donde se extraerán luego para mostrarlas en las 
    //interfaces. Está diseñada para devolver los nombres de las imagenes que se cargaron. 
    
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