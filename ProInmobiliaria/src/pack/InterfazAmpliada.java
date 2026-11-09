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
    JPanel pnlCentro = new JPanel();
    JPanel pnlOeste = new JPanel();
    JPanel pnlSur = new JPanel();

    JPanel pnlContenido = new JPanel();
    JPanel pnlContenido2 = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));
    PanelRedondo pnlContIzq = new PanelRedondo(20);
    JPanel pnlContDer = new JPanel();


    JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));

    JScrollPane scroll = new JScrollPane(pnlCentro);

    PanelRedondo pnlCaracteristicas = new PanelRedondo(20);
    PanelRedondo pnlDescripcion = new PanelRedondo(20);
    PanelRedondo pnlMapa = new PanelRedondo(15);

    //Atributos
    int contador;


    InterfazAmpliada(Propiedad p, Runnable alAtras){
        setLayout(new BorderLayout());

        //Paneles
        //Ajustes de Paneles
        pnlNorte.setPreferredSize(new Dimension(0, 20));
        pnlEste.setPreferredSize(new Dimension(25,0));
        pnlOeste.setPreferredSize(new Dimension(25,0));
        pnlSur.setPreferredSize(new Dimension(0, 5));

        pnlDescripcion.setLayout(new BoxLayout(pnlDescripcion, BoxLayout.Y_AXIS));
        pnlDescripcion.setBackground(Color.WHITE);


        pnlContenido.setLayout(new BoxLayout(pnlContenido, BoxLayout.Y_AXIS));

        pnlBotones.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlContIzq.setLayout(new BoxLayout(pnlContIzq, BoxLayout.Y_AXIS));
        pnlContIzq.setBackground(Color.WHITE);

        pnlContDer.setLayout(new BoxLayout(pnlContDer, BoxLayout.Y_AXIS));
        pnlContDer.setBackground(Color.WHITE);

        pnlContenido2.setPreferredSize(new Dimension((int)(pantalla.width*0.97),(int)(pantalla.height*0.2)));
        pnlContenido2.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlContenido2.setBackground(Color.BLUE);

        //pnlTexto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 360));
        //pnlTexto.setAlignmentX(Component.LEFT_ALIGNMENT);

        int mpAnchura =(int)(pantalla.width*0.375);
        int mpAltura =(int)(pantalla.height*0.3);

        pnlMapa.setPreferredSize(new Dimension(mpAnchura, mpAltura));        
        pnlMapa.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        pnlMapa.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlMapa.setBackground(Color.WHITE);


        //Labels
        //Inicializo Labels
        JLabel lbltitulo = new JLabel(p.getNombre());
        JLabel lblPrecio = new JLabel("Precio $"+String.valueOf(p.getPrecio()));
        JLabel lblExpensas = new JLabel("Expensas $"+String.valueOf(p.getExpensas()));

        JLabel lblDescripcion = new JLabel("Descripción:");
        JLabel lblUbicacion = new JLabel("Ubicación:");


        //Ajuste de Labels
        lbltitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lbltitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblExpensas.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblUbicacion.setFont(new Font("Arial", Font.BOLD, 20));
        lblUbicacion.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);


        //Componentes
        //inicializo Componentes
        JButton btnRegresar = new JButton("(<<)Regresar");
        JButton btnEditar = new JButton("Editar");
        JButton btnBorrar = new JButton("Borrar(x)");

        Mapa mp = new Mapa(pnlMapa, p.getLatitud(), p.getLongitud());
        mp.getMapa().setPreferredSize(new Dimension(mpAnchura, mpAltura));

        JButton btnSiguiente = new JButton("Siguiente(>>)");

        JTextArea txtDescripcion = new JTextArea(p.getDescripción(), 6, 55);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

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

        btnSiguiente.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtDescripcion.setFocusable(false);


        //Agrego
        //Agrego a Paneles
        pnlBotones.add(btnRegresar);
        pnlBotones.add(btnEditar);
        pnlBotones.add(btnBorrar);

        pnlDescripcion.add(lbltitulo);
        pnlDescripcion.add(lblPrecio);
        pnlDescripcion.add(lblExpensas);
        pnlDescripcion.add(lblDescripcion);
        pnlDescripcion.add(txtDescripcion);

        pnlContIzq.add(btnSiguiente);
        pnlContIzq.add(Box.createVerticalStrut(20));
        pnlContIzq.add(lblUbicacion);
        pnlContDer.add(pnlDescripcion);

        pnlContenido.add(pnlBotones);
        pnlContenido.add(pnlContenido2);

        pnlContenido2.add(pnlContIzq);
        pnlContenido2.add(pnlContDer);

        pnlCentro.add(pnlNorte, BorderLayout.NORTH);
        pnlCentro.add(pnlContenido, BorderLayout.CENTER);
        pnlCentro.add(pnlEste, BorderLayout.EAST);
        pnlCentro.add(pnlOeste, BorderLayout.WEST);
        pnlCentro.add(pnlSur, BorderLayout.SOUTH);


        //Agrego a Panel Principal
        add(scroll, BorderLayout.CENTER);
    }
}
