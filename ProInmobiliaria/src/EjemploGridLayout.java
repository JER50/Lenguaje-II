import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;

public class EjemploGridLayout extends JFrame {

    private JLabel pantalla;

    public EjemploGridLayout() {
        setTitle("Ejemplo con GridLayout");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Arriba: etiqueta que muestra el número presionado
        pantalla = new JLabel("Presiona un número", SwingConstants.CENTER);
        pantalla.setFont(new Font("Arial", Font.BOLD, 18));
        pantalla.setPreferredSize(new Dimension(0, 60));
        add(pantalla, BorderLayout.NORTH);

        // Centro: cuadrícula de 3 filas x 3 columnas con espacio de 5 px
        JPanel panelNumeros = new JPanel(new GridLayout(4, 3, 10, 10));

        for (int i = 1; i <= 12; i++) {
            JButton boton = new JButton(String.valueOf(i));
            boton.setFont(new Font("Arial", Font.BOLD, 20));
            int numero = i;
            boton.addActionListener(e -> pantalla.setText("Presionaste: " + numero));
            panelNumeros.add(boton);
        }

        add(panelNumeros, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.WEST);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> {
            new EjemploGridLayout().setVisible(true);
        });
    }
}
