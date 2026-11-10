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
    JPanel pnlContenido2 = new JPanel(new GridBagLayout());
    JPanel pnlContIzq = new JPanel();
    JPanel pnlContDer = new JPanel();

    PanelRedondo pnlFoto = new PanelRedondo(20);
    JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));

    JScrollPane scroll = new JScrollPane(pnlCentro);

    PanelRedondo pnlCaracteristicas = new PanelRedondo(20);
    PanelRedondo pnlDescripcion = new PanelRedondo(20);

    PanelRedondo pnlMapa = new PanelRedondo(20);
    JPanel pnlContMapa = new JPanel();

    JPanel pnlContBotones = new JPanel(new FlowLayout());


    //Atributos
    int contador=0;
    JLabel foto = new JLabel();


    InterfazAmpliada(Propiedad p, InterfazPrincipal ventana){
        setLayout(new BorderLayout());
    
        //Paneles
        //Ajustes de Paneles
        pnlNorte.setPreferredSize(new Dimension(0, 20));
        pnlOeste.setPreferredSize(new Dimension(25,0));
        pnlSur.setPreferredSize(new Dimension(0, 5));

        pnlCaracteristicas.setLayout(new BoxLayout(pnlCaracteristicas, BoxLayout.Y_AXIS));
        pnlCaracteristicas.setBackground(Color.WHITE);

        pnlDescripcion.setLayout(new BoxLayout(pnlDescripcion, BoxLayout.Y_AXIS));
        pnlDescripcion.setBackground(Color.WHITE);
        

        pnlContenido.setLayout(new BoxLayout(pnlContenido, BoxLayout.Y_AXIS));

        pnlBotones.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlContIzq.setLayout(new BoxLayout(pnlContIzq, BoxLayout.Y_AXIS));

        pnlContDer.setLayout(new BoxLayout(pnlContDer, BoxLayout.Y_AXIS));

        pnlContenido2.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlContenido2.setPreferredSize(new Dimension((int)(pantalla.width*0.98),(int)(pantalla.height*1)));

        pnlMapa.setLayout(new BoxLayout(pnlMapa, BoxLayout.Y_AXIS));
        pnlMapa.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlMapa.setBackground(Color.WHITE);
        
        pnlContMapa.setPreferredSize(new Dimension((int)(pantalla.width*0.45), (int)(pantalla.height*0.3)));        

        int mpAnchura =(int)(pantalla.width*0.5);
        int mpAltura =(int)(pantalla.height*0.3);

        pnlContMapa.setPreferredSize(new Dimension(mpAnchura, mpAltura));        
        pnlContMapa.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        pnlContMapa.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlContMapa.setBackground(Color.WHITE);

        pnlFoto.setLayout(new BoxLayout(pnlFoto, BoxLayout.Y_AXIS));
        pnlFoto.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlFoto.setBackground(Color.WHITE);

        pnlContBotones.setPreferredSize(new Dimension((int)(pantalla.width*0.01),(int)(pantalla.height*0.04)));
        pnlContBotones.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlContBotones.setBackground(Color.WHITE);

      //Labels
        //Inicializo Labels
        JLabel lbltitulo = new JLabel(p.getNombre());
        JLabel lblPrecio = new JLabel("Precio $"+String.valueOf(p.getPrecio()));
        JLabel lblExpensas = new JLabel("Expensas $"+String.valueOf(p.getExpensas()));
        JLabel lblTipo = new JLabel(p.getTipoInmueble()+" - "+p.getTipoListado());
        JLabel lblMetros = new JLabel(String.valueOf(p.getMetrosTotales()) + "m\u00B2 Totales / " + String.valueOf(p.getMetrosCubiertos()) + "m\u00B2 Cubiertos: ");
        JLabel lblCantidades = new JLabel(String.valueOf(p.getPisos())+" Pisos / "+String.valueOf(p.getCantAmb())+" Ambientes / "+String.valueOf(p.getCantBaños())+" Baños");
        JLabel lblOtros = new JLabel("Otros: " + String.valueOf(p.getAntigüedad()) + " Años de Antiguedad / " + (p.isCochera()? "Tiene Cochera" : "No tiene Cochera"));

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

        lblDescripcion.setFont(new Font("Arial", Font.BOLD, 17));
        lblDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);

        
        //Componentes
        //inicializo Componentes
        JButton btnRegresar = new JButton("(<<)Regresar");
        JButton btnEditar = new JButton("Editar");
        JButton btnBorrar = new JButton("Borrar(x)");

        Mapa mp = new Mapa(pnlContMapa, p.getLatitud(), p.getLongitud());
        mp.getMapa().setPreferredSize(new Dimension(mpAnchura, mpAltura));

        JButton btnSiguiente = new JButton("Siguiente(>>)");

        JButton btnAnterior = new JButton("(<<)Anterior");

        JTextArea txtDescripcion = new JTextArea(p.getDescripción(), 8, 55);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        //Ajusto Eventos
        btnRegresar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRegresar.addActionListener(e -> {ventana.refrescarGrilla(); ventana.volverAGrilla();});

        btnEditar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEditar.addActionListener(e ->{
        
        Propiedad auxiliar = new Propiedad(p);
 
	        InterfazDatos ventanaMod = new InterfazDatos(p, () -> {
	        	
	            lbltitulo.setText(p.getNombre());
	            lblPrecio.setText("Precio $"+String.valueOf(p.getPrecio()));
	            lblExpensas.setText("Expensas $"+String.valueOf(p.getExpensas()));
                lblTipo.setText(p.getTipoInmueble()+" - "+p.getTipoListado());
                lblMetros.setText(String.valueOf(p.getMetrosTotales()) + "m\u00B2 Totales / " + String.valueOf(p.getMetrosCubiertos()) + "m\u00B2 Cubiertos: ");
                lblCantidades.setText(String.valueOf(p.getPisos())+" Pisos / "+String.valueOf(p.getCantAmb())+" Ambientes / "+String.valueOf(p.getCantBaños())+" Baños");
                lblOtros.setText("Otros: " + String.valueOf(p.getAntigüedad()) + " Años de Antiguedad / " + (p.isCochera()? "Tiene Cochera" : "No tiene Cochera"));

	            txtDescripcion.setText(p.getDescripción());
	            lblDireccion.setText(p.getDireccion());

	            colocarImagen(p);
	            
	            ventana.eliminarPropiedad(p,67);
	            ventana.eliminarPropiedad(auxiliar);
	            ventana.añadirPropiedad(p);
	                
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

        pnlContBotones.add(btnAnterior);
        pnlContBotones.add(btnSiguiente);

        pnlCaracteristicas.add(Box.createVerticalStrut(10));
        pnlCaracteristicas.add(lbltitulo);
        pnlCaracteristicas.add(Box.createVerticalStrut(10));
        pnlCaracteristicas.add(lblPrecio);
        pnlCaracteristicas.add(Box.createVerticalStrut(10));
        pnlCaracteristicas.add(lblExpensas);
        pnlCaracteristicas.add(Box.createVerticalStrut(10));
        pnlCaracteristicas.add(lblTipo);
        pnlCaracteristicas.add(Box.createVerticalStrut(10));
        pnlCaracteristicas.add(lblMetros);
        pnlCaracteristicas.add(Box.createVerticalStrut(10));
        pnlCaracteristicas.add(lblCantidades);
        pnlCaracteristicas.add(Box.createVerticalStrut(10));
        pnlCaracteristicas.add(lblOtros);
        pnlCaracteristicas.add(Box.createVerticalStrut(15));

        pnlDescripcion.add(Box.createVerticalStrut(10));
        pnlDescripcion.add(lblDescripcion);
        pnlDescripcion.add(txtDescripcion);

        pnlFoto.add(Box.createVerticalStrut(20));
        pnlFoto.add(foto);
        pnlFoto.add(Box.createVerticalStrut(15));
        pnlFoto.add(pnlContBotones);
        pnlFoto.add(Box.createVerticalStrut(10));

        pnlMapa.add(lblUbicacion);
        pnlMapa.add(lblDireccion);
        pnlMapa.add(Box.createVerticalStrut(10));
        pnlMapa.add(pnlContMapa);
        pnlMapa.add(Box.createVerticalStrut(10));

        pnlContIzq.add(pnlFoto);
        pnlContIzq.add(Box.createVerticalStrut(14));
        pnlContIzq.add(pnlMapa);


        pnlContDer.add(pnlCaracteristicas);
        pnlContDer.add(Box.createVerticalStrut(14));
        pnlContDer.add(pnlDescripcion);

        pnlContenido.add(pnlBotones);
        pnlContenido.add(pnlContenido2);

        // Esto pega los paneles arriba
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 40, 0, 0);

        gbc.gridx = 0;
        gbc.weightx = 0;
        pnlContenido2.add(pnlContIzq, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        pnlContenido2.add(pnlContDer, gbc);
        //

        pnlCentro.add(pnlNorte, BorderLayout.NORTH);
        pnlCentro.add(pnlContenido, BorderLayout.CENTER);
        pnlCentro.add(pnlOeste, BorderLayout.WEST);
        pnlCentro.add(pnlSur, BorderLayout.SOUTH);


        //Agrego a Panel Principal
        add(scroll, BorderLayout.CENTER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void colocarImagen(Propiedad p) {
	    ImageIcon icono = new ImageIcon(p.getImagenes().get(contador));
	    Image img = icono.getImage().getScaledInstance((int)(pantalla.width*0.5), (int)(pantalla.height*0.5), Image.SCALE_SMOOTH);
	    foto.setIcon(new ImageIcon(img));
    }
    
}
