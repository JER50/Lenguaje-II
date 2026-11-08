package pack;
//Librerias
import javax.swing.*;
import java.awt.*;

public class InterfazAmpliada extends JPanel{
    // Dispositivo
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

    //Paneles
    JPanel pnlNorte = new JPanel();
    JPanel pnlEste = new JPanel();
    JPanel pnlOeste = new JPanel();
    JPanel pnlSur = new JPanel();

    JPanel pnlCentro = new JPanel(new BorderLayout());
    JScrollPane scroll = new JScrollPane(pnlCentro);

    JPanel pnlContenido = new JPanel();

    JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));
    JPanel pnlTexto = new JPanel(new FlowLayout(FlowLayout.CENTER,100,30));

    PanelRedondo pnlCaracteristicas = new PanelRedondo(20);
    PanelRedondo pnlDescripcion = new PanelRedondo(20);
    PanelRedondo pnlMapa = new PanelRedondo(15);


    InterfazAmpliada(Propiedad p, Runnable alAtras){
        setLayout(new BorderLayout());

        //Paneles
        //Ajustes de Paneles
        pnlNorte.setPreferredSize(new Dimension(0, 20));
        pnlEste.setPreferredSize(new Dimension(25,0));
        pnlOeste.setPreferredSize(new Dimension(25,0));
        pnlSur.setPreferredSize(new Dimension(0, 5));

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


        int mpAnchura =(int)(pantalla.width*0.375);
        int mpAltura =(int)(pantalla.height*0.3);

        pnlMapa.setPreferredSize(new Dimension(mpAnchura, mpAltura));        
        pnlMapa.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        pnlMapa.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlMapa.setBackground(Color.WHITE);

        pnlContenido.setLayout(new BoxLayout(pnlContenido, BoxLayout.Y_AXIS));


        //Labels
        //Inicializo Labels
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

        Mapa mp = new Mapa(pnlMapa, p.getLatitud(), p.getLongitud());
        mp.getMapa().setPreferredSize(new Dimension(mpAnchura, mpAltura));


        //Ajusto Eventos
        btnRegresar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRegresar.addActionListener(e -> alAtras.run());

        btnEditar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEditar.addActionListener(e ->{
            InterfazDatos ventana = new InterfazDatos(p, () -> {
                lbltitulo.setText(p.getNombre());
                // actualiza aquí los demás labels que muestren datos de p
                revalidate();
                repaint();
            });
            ventana.setVisible(true);
        });

        btnBorrar.setAlignmentX(Component.LEFT_ALIGNMENT);


        //Agrego
        //Agrego a Paneles

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
        add(scroll, BorderLayout.CENTER);
    }
}
