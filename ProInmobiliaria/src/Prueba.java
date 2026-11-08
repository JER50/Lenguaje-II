import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.net.URL;

public class Prueba {

    public static void main(String[] args) {

        // Buscar la imagen dentro del classpath
        URL ruta = Prueba.class.getResource("/imagenes/LogoT.png");
        
        System.out.print(ruta);
        
        // Comprobar que existe
        if (ruta == null) {
            System.out.println("No se encontró la imagen");
            return;
        }

        // Cargar la imagen
        ImageIcon imagen = new ImageIcon(ruta);

        // Crear una ventana
        JFrame ventana = new JFrame("Visualizador de imagen");

        // Incorporar la imagen a un JLabel
        JLabel etiqueta = new JLabel(imagen);

        // Agregar el JLabel a la ventana
        ventana.add(etiqueta);

        // Configurar la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack(); // Ajusta la ventana al tamaño de la imagen
        ventana.setLocationRelativeTo(null); // Centrar en pantalla
        ventana.setVisible(true);
    }
}