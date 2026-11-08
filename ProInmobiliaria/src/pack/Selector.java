package pack;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

public class Selector {

    public static ArrayList<String> elegirImagenes(JFrame parent) {
        ArrayList<String> nombres = new ArrayList<>();

        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new FileNameExtensionFilter("Imágenes (jpg, jpeg, png, gif)", "jpg", "jpeg", "png", "gif"));

        int resultado = chooser.showOpenDialog(parent);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            for (File f : chooser.getSelectedFiles()) {
                nombres.add(f.getName());          // solo el nombre
                // nombres.add(f.getAbsolutePath()); // o la ruta completa
            }
        }
        return nombres;
    }
}