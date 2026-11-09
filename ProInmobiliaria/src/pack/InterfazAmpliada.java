package pack;
//Librerias
import javax.swing.*;
import java.awt.*;

public class InterfazAmpliada extends JPanel{
    // Dispositivo
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

    //Paneles
    JPanel pnlNorte = new JPanel();
    JPanel pnlCentro = new JPanel();
    JPanel pnlOeste = new JPanel();
    JPanel pnlSur = new JPanel();

    JPanel pnlContenido = new JPanel();
    JPanel pnlContenido2 = new JPanel();
    PanelRedondo pnlContIzq = new PanelRedondo(20);
    JPanel pnlContDer = new JPanel();
    JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));

    JScrollPane scroll = new JScrollPane(pnlCentro);

    PanelRedondo pnlCaracteristicas = new PanelRedondo(20);
    PanelRedondo pnlDescripcion = new PanelRedondo(20);
    JPanel pnlMapa = new JPanel();

    JPanel pnlContBotones = new JPanel(new FlowLayout());
    JPanel pnlContBotIzq = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
    JPanel pnlContBotDer = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));


    //Atributos
    int contador=0;
    JLabel foto = new JLabel();


    InterfazAmpliada(Propiedad p, InterfazPrincipal ventana){
        setLayout(new BorderLayout());
        //Imagenes
        

        //Paneles
        //Ajustes de Paneles
        pnlNorte.setPreferredSize(new Dimension(0, 20));
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

        pnlContenido2.setAlignmentX(Component.LEFT_ALIGNMENT);
        //pnlContenido2.setPreferredSize(new Dimension((int)(pantalla.width*0.98),(int)(pantalla.height*0.7)));
        pnlContenido2.setBackground(Color.BLUE);

        pnlContBotIzq.setPreferredSize(new Dimension((int)(pantalla.width*0.1),2));
        
        
       
        
        
        pnlMapa.setPreferredSize(new Dimension((int)(pantalla.width*0.45), (int)(pantalla.height*0.3)));        
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
        JLabel lblDireccion = new JLabel(p.getDireccion());
        colocarImagen(p);
        
        
        

        //Ajuste de Labels
        lbltitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lbltitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblExpensas.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblUbicacion.setFont(new Font("Arial", Font.BOLD, 20));
        lblUbicacion.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);

        actualizarAmpliada();
        
        //Componentes
        //inicializo Componentes
        JButton btnRegresar = new JButton("(<<)Regresar");
        JButton btnEditar = new JButton("Editar");
        JButton btnBorrar = new JButton("Borrar(x)");

        Mapa mp = new Mapa(pnlMapa, p.getLatitud(), p.getLongitud());
        mp.getMapa().setPreferredSize(new Dimension((int)(pantalla.height*0.3), (int)(pantalla.height*0.3)));

        JButton btnSiguiente = new JButton("Siguiente(>>)");

        JButton btnAnterior = new JButton("(<<)Anterior");

        JTextArea txtDescripcion = new JTextArea(p.getDescripción(), 6, 55);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        //Ajusto Eventos
        btnRegresar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRegresar.addActionListener(e -> {ventana.volverAGrilla();});

        btnEditar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEditar.addActionListener(e ->{
        	
            InterfazDatos ventanaMod = new InterfazDatos(p, () -> {
                lbltitulo.setText(p.getNombre());
                
                // actualiza aquí los demás labels que muestren datos de p
                revalidate();
                repaint();
            });
            
            ventanaMod.setVisible(true);
        });
        
        btnBorrar.addActionListener(e -> {
            ventana.eliminarPropiedad(p);
            ventana.refrescarGrilla();
            ventana.volverAGrilla();
        });
        
        btnBorrar.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnAnterior.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAnterior.addActionListener(e -> {
            int total = p.getImagenes().size();
            contador = (contador - 1 + total) % total;
            colocarImagen(p);
        });

        btnSiguiente.setAlignmentX(Component.RIGHT_ALIGNMENT);
        btnSiguiente.addActionListener(e -> {
            contador = (contador + 1) % p.getImagenes().size();
            colocarImagen(p);
        });

        txtDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtDescripcion.setFocusable(false);

        //Agrego
        //Agrego a Paneles
        pnlBotones.add(btnRegresar);
        pnlBotones.add(btnEditar);
        pnlBotones.add(btnBorrar);

        pnlContBotones.add(pnlContBotIzq);
        pnlContBotones.add(pnlContBotDer);

        pnlContBotIzq.add(btnAnterior);
        pnlContBotDer.add(btnSiguiente);

        pnlDescripcion.add(lbltitulo);
        pnlDescripcion.add(lblPrecio);
        pnlDescripcion.add(lblExpensas);
        pnlDescripcion.add(lblDescripcion);
        pnlDescripcion.add(txtDescripcion);

        pnlContIzq.add(foto);
        pnlContIzq.add(Box.createVerticalStrut(15));
        pnlContIzq.add(pnlContBotones);
        pnlContIzq.add(Box.createVerticalStrut(40));
        pnlContIzq.add(lblUbicacion);
        pnlContIzq.add(lblDireccion);
        pnlContIzq.add(pnlMapa);
        pnlContIzq.add(Box.createVerticalStrut(20));

        pnlContDer.add(pnlDescripcion);

        pnlContenido.add(pnlBotones);
        pnlContenido.add(pnlContenido2);

        pnlContenido2.add(pnlContIzq);
        pnlContenido2.add(pnlContDer);

        pnlCentro.add(pnlNorte, BorderLayout.NORTH);
        pnlCentro.add(pnlContenido, BorderLayout.CENTER);
        pnlCentro.add(pnlOeste, BorderLayout.WEST);
        pnlCentro.add(pnlSur, BorderLayout.SOUTH);


        //Agrego a Panel Principal
        add(scroll, BorderLayout.CENTER);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void colocarImagen(Propiedad p) {
	    ImageIcon icono = new ImageIcon(p.getImagenes().get(contador));
	    Image img = icono.getImage().getScaledInstance((int)(pantalla.width*0.5), (int)(pantalla.height*0.5), Image.SCALE_SMOOTH);
	    foto.setIcon(new ImageIcon(img));
    }
    
    protected void actualizarAmpliada() {
    	
    }
    
}
