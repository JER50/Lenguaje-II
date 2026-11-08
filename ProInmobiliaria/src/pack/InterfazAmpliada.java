package pack;//tema

import com.formdev.flatlaf.FlatLightLaf;
//Librerias
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.nio.file.Path;

public class InterfazAmpliada extends JFrame{
    // Dispositivo
	
	private static final Path carpetaImagenes = Path.of(Path.of("").toAbsolutePath().toString(), "archivos", "imagenes");
	
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

    //Paneles
    JPanel pnlNorte = new JPanel();
    JPanel pnlEste = new JPanel();
    JPanel pnlOeste = new JPanel();
    JPanel pnlSur = new JPanel();
    
    JPanel pnlLogo = new JPanel();

    JPanel pnlCentro = new JPanel(new BorderLayout());
    JScrollPane scroll = new JScrollPane(pnlCentro);

    JPanel pnlContenido = new JPanel();

    JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));
    JPanel pnlTexto = new JPanel(new FlowLayout(FlowLayout.CENTER,100,30));

    PanelRedondo pnlCaracteristicas = new PanelRedondo(20);
    PanelRedondo pnlDescripcion = new PanelRedondo(20);
    PanelRedondo pnlMapa = new PanelRedondo(15);


    ImageIcon imglogo = new ImageIcon(carpetaImagenes.resolve("LogoT.png").toString());

    InterfazAmpliada(Propiedad p){
        setTitle("Inmobiliaria");
        setSize((int)(pantalla.width * 0.6), (int)(pantalla.height * 0.7));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(carpetaImagenes.resolve("UcasalIcono.png").toString()));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Imagenes
        //Ajustes de Imagenes
        Image img = imglogo.getImage().getScaledInstance(180, 110, Image.SCALE_SMOOTH);


        //Paneles
        //Ajustes de Paneles
        pnlLogo.setPreferredSize(new Dimension(MAXIMIZED_HORIZ,110));
        pnlLogo.setBackground(Color.WHITE);

        pnlNorte.setPreferredSize(new Dimension(MAXIMIZED_HORIZ, 20));
        pnlEste.setPreferredSize(new Dimension(25,MAXIMIZED_VERT));
        pnlOeste.setPreferredSize(new Dimension(25,MAXIMIZED_VERT));
        pnlSur.setPreferredSize(new Dimension(MAXIMIZED_HORIZ, 5));

        pnlTexto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 360));
        pnlTexto.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlBotones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        pnlBotones.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlDescripcion.setPreferredSize(new Dimension(500,300));
        pnlDescripcion.setLayout(new BoxLayout(pnlDescripcion, BoxLayout.Y_AXIS));
        pnlDescripcion.setBackground(Color.WHITE);

        pnlCaracteristicas.setPreferredSize(new Dimension(200,300));
        pnlCaracteristicas.setLayout(new BoxLayout(pnlCaracteristicas, BoxLayout.Y_AXIS));
        pnlCaracteristicas.setBackground(Color.WHITE);

        pnlMapa.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlMapa.setBackground(Color.WHITE);

        pnlContenido.setLayout(new BoxLayout(pnlContenido, BoxLayout.Y_AXIS));


        //Labels
        //Inicializo Labels
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        JLabel lbltitulo = new JLabel(p.getNombre());

        JLabel lblDescripcion = new JLabel("Descripción:");
        JLabel lblCaracteristicas = new JLabel("Caracteristicas:");
        JLabel lblUbicacion = new JLabel("Ubicación:");


        //Ajuste de Labels
        lbltitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lbltitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblCaracteristicas.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblUbicacion.setFont(new Font("Arial", Font.BOLD, 20));
        lblUbicacion.setAlignmentX(Component.LEFT_ALIGNMENT);


        //Componentes
        //inicializo Componentes
        JButton btnRegresar = new JButton("(<<)Regresar");
        JButton btnEditar = new JButton("Editar");
        JButton btnBorrar = new JButton("Borrar(x)");

        new Mapa(pnlMapa, p.getLatitud(), p.getLongitud());


        //Ajusto Eventos
        btnRegresar.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnEditar.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnBorrar.setAlignmentX(Component.LEFT_ALIGNMENT);


        //Agrego
        //Agrego a Paneles
        pnlLogo.add(lblLogo);

        pnlTexto.add(pnlDescripcion);
        pnlTexto.add(pnlCaracteristicas);

        pnlBotones.add(btnRegresar);
        pnlBotones.add(btnEditar);
        pnlBotones.add(btnBorrar);

        pnlDescripcion.add(lblDescripcion);
        pnlCaracteristicas.add(lblCaracteristicas);

        pnlContenido.add(pnlBotones);
        pnlContenido.add(Box.createVerticalStrut(15));
        pnlContenido.add(lbltitulo);
        pnlContenido.add(Box.createVerticalStrut(15));
        pnlContenido.add(pnlTexto);
        pnlContenido.add(lblUbicacion);
        pnlContenido.add(Box.createVerticalStrut(15));
        pnlContenido.add(pnlMapa);

        pnlCentro.add(pnlNorte, BorderLayout.NORTH);
        pnlCentro.add(pnlContenido, BorderLayout.CENTER);
        pnlCentro.add(pnlEste, BorderLayout.EAST);
        pnlCentro.add(pnlOeste, BorderLayout.WEST);
        pnlCentro.add(pnlSur, BorderLayout.SOUTH);


        //Agrego a Panel Principal
        add(pnlLogo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        Propiedad p = new Propiedad("Casa", 1000, "Casa", "Venta", "Luis Angel de Dios", 80, 50, 5, 2, true, 200, 20, 2, "Una casa muy Bonita :D", new ArrayList<>(), -24.795784554541317, -65.42473102022629);
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> {new InterfazAmpliada(p).setVisible(true);
        });
    }
}
