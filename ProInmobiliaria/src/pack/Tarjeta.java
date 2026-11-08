package ProInmobiliaria.src.pack;//Librerias
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tarjeta extends JPanel {
    //Dispositivo
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

    public Tarjeta(Propiedad prop, Runnable alClick) {
        setLayout(new BorderLayout(0, 8));
        setPreferredSize(new Dimension((int)(pantalla.width * 0.28), 500));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        // Imagen
        ImageIcon icono = new ImageIcon(prop.getImagenes().get(0));
        Image img = icono.getImage().getScaledInstance(300, 180, Image.SCALE_SMOOTH);
        add(new JLabel(new ImageIcon(img)), BorderLayout.NORTH);


        // Info en el centro
        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JLabel lblPrecio = new JLabel(String.valueOf(prop.getPrecio()));
        lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 22));

        info.add(lblPrecio);
        info.add(new JLabel(prop.getDireccion()));
        info.add(Box.createVerticalStrut(8));
        add(info, BorderLayout.CENTER);


        // Clic y efecto hover en toda la tarjeta
        MouseAdapter ma = new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { alClick.run(); }
            @Override public void mouseEntered(MouseEvent e) { setBackground(new Color(240, 245, 255)); }
            @Override public void mouseExited(MouseEvent e)  { setBackground(Color.WHITE); }
        };
        addMouseListener(ma);
        for (Component c : getComponents()) c.addMouseListener(ma);
    }
}