import javax.swing.*;
import java.util.*;

public class Ejemplo extends JFrame {
    private List<JCheckBox> checks = new ArrayList<>();

    public Ejemplo() {
        List<String> opciones = List.of("Cochera", "Pileta", "Jardín", "Balcón");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (String op : opciones) {
            JCheckBox cb = new JCheckBox(op);
            checks.add(cb);
            panel.add(cb);
        }

        JButton btn = new JButton("Ver seleccionados");
        btn.addActionListener(e -> {
            for (JCheckBox cb : checks) {
                if (cb.isSelected()) {
                    System.out.println(cb.getText());
                }
            }
        });
        panel.add(btn);

        add(panel);
        setSize(300, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ejemplo();
    }
}