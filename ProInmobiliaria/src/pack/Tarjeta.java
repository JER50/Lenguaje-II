package pack;
//Librerias
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tarjeta extends JPanel {
    //Dispositivo
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();



    public Tarjeta(Propiedad prop, Runnable alClick) {
        setLayout(new BorderLayout(0, 8));
        setPreferredSize(new Dimension((int)(pantalla.width * 0.28), (int)(pantalla.height*0.4)));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Imagen
        ImageIcon icono = new ImageIcon(prop.getImagenes().get(0));
        
        Image img = icono.getImage().getScaledInstance(450, 250, Image.SCALE_SMOOTH);
        add(new JLabel(new ImageIcon(img)), BorderLayout.NORTH);


        // Info en el centro
        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JLabel lblTitulo = new JLabel(String.valueOf(prop.getNombre()));
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 25));

        JLabel lblPrecio = new JLabel(" - Precio: $" + String.valueOf(prop.getPrecio()));
        lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel lblAlquiler = new JLabel("Tipo: " + String.valueOf(prop.getTipoListado()));
        lblAlquiler.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel lblAmbientes = new JLabel("Ambientes: " + String.valueOf(prop.getCantAmb()));
        lblAmbientes.setFont(new Font("SansSerif", Font.BOLD, 13));

        JLabel lblBaños = new JLabel(" - Baños: " + String.valueOf(prop.getCantBaños()));
        lblBaños.setFont(new Font("SansSerif", Font.BOLD, 13));

        JLabel lblCochera = new JLabel(" - Cochera: " + (prop.isCochera() ? "Si" : "No"));
        lblCochera.setFont(new Font("SansSerif", Font.BOLD, 13));

        JLabel lblDireccion = new JLabel(" - Dirección: " + String.valueOf(prop.getDireccion()));
        lblDireccion.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblDireccion.setToolTipText(prop.getDireccion());

        JPanel filaDireccion = new JPanel(new BorderLayout());
        filaDireccion.setOpaque(false);
        filaDireccion.setAlignmentX(Component.LEFT_ALIGNMENT);
        filaDireccion.add(lblDireccion, BorderLayout.CENTER);
        filaDireccion.setMaximumSize(new Dimension(Integer.MAX_VALUE, filaDireccion.getPreferredSize().height));

        JPanel filaDatos1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filaDatos1.setOpaque(false);
        filaDatos1.setAlignmentX(Component.LEFT_ALIGNMENT);
        filaDatos1.add(lblAlquiler);
        filaDatos1.add(lblPrecio);
        filaDatos1.setMaximumSize(new Dimension(Integer.MAX_VALUE, filaDatos1.getPreferredSize().height));

        JPanel filaDatos2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filaDatos2.setOpaque(false);
        filaDatos2.setAlignmentX(Component.LEFT_ALIGNMENT);
        filaDatos2.add(lblAmbientes);
        filaDatos2.add(lblBaños);
        filaDatos2.add(lblCochera);
        filaDatos2.add(filaDireccion);
        filaDatos2.setMaximumSize(new Dimension(Integer.MAX_VALUE, filaDatos2.getPreferredSize().height));

        info.add(lblTitulo);
        info.add(filaDatos1);
        info.add(filaDatos2);
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