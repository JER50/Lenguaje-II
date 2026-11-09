package pack;
//tema
import com.formdev.flatlaf.FlatLightLaf;
//Librerias
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.nio.file.Path;

public class InterfazDatos extends JFrame{
    //Dispositivo
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Path carpetaImagenes = Path.of(Path.of("").toAbsolutePath().toString(), "archivos", "imagenes");

    //inicializo Paneles
    JPanel pnlNorte = new JPanel();
    JPanel pnlEste = new JPanel();
    JPanel pnlOeste = new JPanel();
    JPanel pnlCentro = new JPanel();
    JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 5));

    JPanel pnlContenedor = new JPanel(new BorderLayout());

    JPanel pnlMapa = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

    //Inicializo Listas
    ArrayList<String> imagenes = new ArrayList<String>();
    String[] operacion = {"Venta","Alquiler","Alquiler Temporal"};
    String[] tipoPropiedad = {"Departamento", "Casa", "Terreno", "Local", "Quinta", "Galpón", "Deposito", "Campo", "Hotel", "Edificio", "Otros"};
    
    InterfazDatos(Propiedad p, Runnable alAceptar) {
        setTitle("Propiedad");
        setSize((int)(pantalla.width * 0.4), (int)(pantalla.height * 0.95));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(carpetaImagenes.resolve("UcasalIcono.jpg").toString()));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        //Ajuste de Paneles
        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.Y_AXIS));

        pnlNorte.setPreferredSize(new Dimension(0,20));
        pnlEste.setPreferredSize(new Dimension(10,0));
        pnlOeste.setPreferredSize(new Dimension(10,0));

        int mpAnchura =(int)(pantalla.width*0.375);
        int mpAltura =(int)(pantalla.height*0.3);

        pnlMapa.setPreferredSize(new Dimension(mpAnchura, mpAltura));
        pnlMapa.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlMapa.setOpaque(false);


        //Labels
        //Inicializo Labels
        JLabel lblNombre = new JLabel("Titulo:");
        JLabel lblPrecio = new JLabel("Precio($):");
        JLabel lblExpensas = new JLabel("Expensas($):");
        JLabel lblTipoInmueble = new JLabel("Tipo De Inmueble:");
        JLabel lblTipoListado = new JLabel("Tipo de Listado:");
        JLabel lblDireccion = new JLabel("Direccion:");
        JLabel lblMetrosTotales = new JLabel("Metros Cuadrados Totales:");
        JLabel lblMetrosCubiertos = new JLabel("Metros Cuadrados Cubiertos:");
        JLabel lblCantAmb = new JLabel("Cantidad de Ambientes:");
        JLabel lblCantBaños = new JLabel("Cantidad de baños:");
        JLabel lblCochera = new JLabel("¿Tiene Cochera?");
        JLabel lblAntiguedad = new JLabel("Antiguedad:");
        JLabel lblCantPisos = new JLabel("Cantidad de Pisos:");
        JLabel lblDescripcion = new JLabel("Deje una corta Descripcion:");
        JLabel lblUbicacion = new JLabel("Ubicacion:");
        


        //Ajuste de Labels
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblExpensas.setAlignmentX(Component.LEFT_ALIGNMENT);


        //Componentes
        //Inicializo Componentes
        JTextField txtNombre = new JTextField(p.getNombre(),55);
        JTextField txtDireccion = new JTextField(p.getDireccion());
        JTextField txtLat = new JTextField(String.valueOf(p.getLatitud()));
        JTextField txtLon = new JTextField(String.valueOf(p.getLongitud()));

        JComboBox<String> cmbTipoLista = new JComboBox<>(operacion);
        cmbTipoLista.setSelectedItem(p.getTipoListado());

        JComboBox<String> cmbTipoListado = new JComboBox<>(tipoPropiedad);
        cmbTipoListado.setSelectedItem(p.getTipoInmueble());
        
        SpinnerNumberModel numMdlPrecio = new SpinnerNumberModel(p.getPrecio(), 1, 1000000000, 1);
        JSpinner spinPrecio = new JSpinner(numMdlPrecio);

        SpinnerNumberModel numMdlExpensas = new SpinnerNumberModel(p.getExpensas(), 1, 1000000, 1);
        JSpinner spinExpensas = new JSpinner(numMdlExpensas);

        SpinnerNumberModel numMdlMetrosTotales = new SpinnerNumberModel(p.getMetrosTotales(), 1, 1000, 1);
        JSpinner spinMetrosTotales = new JSpinner(numMdlMetrosTotales);

        SpinnerNumberModel numMdlMetrosCubiertos = new SpinnerNumberModel(p.getMetrosTotales(), 1, 1000, 1);
        JSpinner spinMetrosCubiertos = new JSpinner(numMdlMetrosCubiertos);

        SpinnerNumberModel numMdlCantAmb = new SpinnerNumberModel(p.getCantAmb(), 1, 20, 1);
        JSpinner spinCantAmb = new JSpinner(numMdlCantAmb);

        SpinnerNumberModel numMdlCantBaños = new SpinnerNumberModel(p.getCantBaños(), 1, 20, 1);
        JSpinner spinCantBaños = new JSpinner(numMdlCantBaños);

        SpinnerNumberModel numMdlCantPisos = new SpinnerNumberModel(p.getPisos(), 1, 50, 1);
        JSpinner spinCantPisos = new JSpinner(numMdlCantPisos);

        SpinnerNumberModel numMdlAntiguedad = new SpinnerNumberModel(p.getAntigüedad(), 1, 1000, 1);
        JSpinner spinAntiguedad = new JSpinner(numMdlAntiguedad);

        JTextArea txtDescripcion = new JTextArea(8, 55);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        JRadioButton rbSi = new JRadioButton("Si");
        JRadioButton rbNo = new JRadioButton("No");

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        
        //Ajuste de Componentes
        txtNombre.setMaximumSize(txtNombre.getPreferredSize());
        txtNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinPrecio.setMaximumSize(spinPrecio.getPreferredSize());
        spinPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinExpensas.setMaximumSize(spinExpensas.getPreferredSize());
        spinExpensas.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtDireccion.setMaximumSize(txtDireccion.getPreferredSize());
        txtDireccion.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinMetrosTotales.setMaximumSize(spinMetrosTotales.getPreferredSize());
        spinMetrosTotales.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinMetrosCubiertos.setMaximumSize(spinMetrosCubiertos.getPreferredSize());
        spinMetrosCubiertos.setAlignmentX(Component.LEFT_ALIGNMENT);

        JScrollPane pnlscrolltxt = new JScrollPane(txtDescripcion);
        pnlscrolltxt.setMaximumSize(pnlscrolltxt.getPreferredSize());
        pnlscrolltxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinCantAmb.setMaximumSize(spinCantAmb.getPreferredSize());
        spinCantAmb.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinCantBaños.setMaximumSize(spinCantBaños.getPreferredSize());
        spinCantBaños.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinCantPisos.setMaximumSize(spinCantPisos.getPreferredSize());
        spinCantPisos.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinAntiguedad.setMaximumSize(spinAntiguedad.getPreferredSize());
        spinAntiguedad.setAlignmentX(Component.LEFT_ALIGNMENT);

        cmbTipoLista.setMaximumSize(cmbTipoLista.getPreferredSize());
        cmbTipoLista.setAlignmentX(Component.LEFT_ALIGNMENT);

        cmbTipoLista.addActionListener(e -> {
            int i = cmbTipoLista.getSelectedIndex();
            if (i == 0){
                 spinExpensas.setModel(new SpinnerNumberModel(1, 0, 1000000, 1));
                 return;
            }
            spinExpensas.setModel(new SpinnerNumberModel(0, 0, 0, 0));
        });

        cmbTipoListado.setMaximumSize(cmbTipoListado.getPreferredSize());
        cmbTipoListado.setAlignmentX(Component.LEFT_ALIGNMENT);

        rbNo.setSelected(true);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbSi);
        grupo.add(rbNo);

        txtLat.setMaximumSize(txtLat.getPreferredSize());
        txtLat.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtLat.setFocusable(false);

        txtLon.setMaximumSize(txtLon.getPreferredSize());
        txtLon.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtLon.setFocusable(false);

        Mapa mp = new Mapa(pnlMapa, p.getLatitud(), p.getLongitud(), (lat, lon) -> {
            txtLat.setText(String.valueOf(lat));
            txtLon.setText(String.valueOf(lon));
        });
        mp.getMapa().setPreferredSize(new Dimension(mpAnchura,mpAltura));
        

        btnAceptar.addActionListener(e -> {
            p.setNombre(txtNombre.getText());
            //rellenar

            alAceptar.run();
            dispose();
        });

        
        btnCancelar.addActionListener(e -> dispose());


        //Agrego
        //Agregar a Paneles
        pnlCentro.add(lblNombre);
        pnlCentro.add(txtNombre);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblPrecio);
        pnlCentro.add(spinPrecio);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblExpensas);
        pnlCentro.add(spinExpensas);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblTipoInmueble);
        pnlCentro.add(cmbTipoListado);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblTipoListado);
        pnlCentro.add(cmbTipoLista);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblDireccion);
        pnlCentro.add(txtDireccion);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblMetrosTotales);
        pnlCentro.add(spinMetrosTotales);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblMetrosCubiertos);
        pnlCentro.add(spinMetrosCubiertos);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblCantPisos);
        pnlCentro.add(spinCantPisos);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblCantAmb);
        pnlCentro.add(spinCantAmb);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblCantBaños);
        pnlCentro.add(spinCantBaños);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblCochera);
        pnlCentro.add(rbSi);
        pnlCentro.add(rbNo);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblAntiguedad);
        pnlCentro.add(spinAntiguedad);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblDescripcion);
        pnlCentro.add(pnlscrolltxt);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblUbicacion);
        pnlCentro.add(txtLat);
        pnlCentro.add(txtLon);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(pnlMapa);

        pnlSur.add(btnAceptar);
        pnlSur.add(btnCancelar);

        pnlContenedor.add(pnlNorte, BorderLayout.NORTH);
        pnlContenedor.add(pnlEste, BorderLayout.EAST);
        pnlContenedor.add(pnlCentro, BorderLayout.CENTER);
        pnlContenedor.add(pnlOeste, BorderLayout.WEST);
        pnlContenedor.add(pnlSur, BorderLayout.SOUTH);

        //Agregar a Panel Principal
        add(pnlContenedor);
        JScrollPane pnlscroll = new JScrollPane(pnlContenedor);
        setContentPane(pnlscroll);

    }

    /*
    |
    |
    |
    */
    
    //Acá habria que vincular la actualización del panel cuando le den a agregar
    
    InterfazDatos(InterfazPrincipal ventanaPrincipal){
        setTitle("Propiedad");
        setSize((int)(pantalla.width * 0.4), (int)(pantalla.height * 0.95));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(carpetaImagenes.resolve("UcasalIcono.jpg").toString()));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        //Ajuste de Paneles
        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.Y_AXIS));

        pnlNorte.setPreferredSize(new Dimension(0,20));
        pnlEste.setPreferredSize(new Dimension(10,0));
        pnlOeste.setPreferredSize(new Dimension(10,0));

        int mpAnchura =(int)(pantalla.width*0.375);
        int mpAltura =(int)(pantalla.height*0.3);

        pnlMapa.setPreferredSize(new Dimension(mpAnchura, mpAltura));
        pnlMapa.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlMapa.setOpaque(false);


        //Labels
        //Inicializo Labels
        JLabel lblNombre = new JLabel("Titulo:");
        JLabel lblPrecio = new JLabel("Precio($):");
        JLabel lblExpensas = new JLabel("Expensas($):");
        JLabel lblTipoInmueble = new JLabel("Tipo De Inmueble:");
        JLabel lblTipoListado = new JLabel("Tipo de Listado:");
        JLabel lblDireccion = new JLabel("Direccion:");
        JLabel lblMetrosTotales = new JLabel("Metros Cuadrados Totales:");
        JLabel lblMetrosCubiertos = new JLabel("Metros Cuadrados Cubiertos:");
        JLabel lblCantAmb = new JLabel("Cantidad de Ambientes:");
        JLabel lblCantBaños = new JLabel("Cantidad de baños:");
        JLabel lblCochera = new JLabel("¿Tiene Cochera?");
        JLabel lblAntiguedad = new JLabel("Antiguedad:");
        JLabel lblCantPisos = new JLabel("Cantidad de Pisos:");
        JLabel lblDescripcion = new JLabel("Deje una corta Descripcion:");
        JLabel lblUbicacion = new JLabel("Ubicacion:");
        


        //Ajuste de Labels
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblExpensas.setAlignmentX(Component.LEFT_ALIGNMENT);


        //Componentes
        //Inicializo Componentes
        JTextField txtNombre = new JTextField(55);
        JTextField txtDireccion = new JTextField(55);
        JTextField txtLat = new JTextField(13);
        JTextField txtLon = new JTextField(13);

        JComboBox<String> cmbTipoLista = new JComboBox<>(operacion);

        JComboBox<String> cmbTipoProiedad = new JComboBox<>(tipoPropiedad);

        SpinnerNumberModel numMdlPrecio = new SpinnerNumberModel(1, 0, 1000000000, 1);
        JSpinner spinPrecio = new JSpinner(numMdlPrecio);

        SpinnerNumberModel numMdlExpensas = new SpinnerNumberModel(1, 0, 1000000, 1);
        JSpinner spinExpensas = new JSpinner(numMdlExpensas);

        SpinnerNumberModel numMdlMetrosTotales = new SpinnerNumberModel(5, 5, 1000, 1);
        JSpinner spinMetrosTotales = new JSpinner(numMdlMetrosTotales);

        SpinnerNumberModel numMdlMetrosCubiertos = new SpinnerNumberModel(5, 5, 1000, 1);
        JSpinner spinMetrosCubiertos = new JSpinner(numMdlMetrosCubiertos);

        SpinnerNumberModel numMdlCantAmb = new SpinnerNumberModel(1, 1, 20, 1);
        JSpinner spinCantAmb = new JSpinner(numMdlCantAmb);

        SpinnerNumberModel numMdlCantBaños = new SpinnerNumberModel(1, 0, 20, 1);
        JSpinner spinCantBaños = new JSpinner(numMdlCantBaños);

        SpinnerNumberModel numMdlCantPisos = new SpinnerNumberModel(1, 1, 50, 1);
        JSpinner spinCantPisos = new JSpinner(numMdlCantPisos);

        SpinnerNumberModel numMdlAntiguedad = new SpinnerNumberModel(1, 0, 1000, 1);
        JSpinner spinAntiguedad = new JSpinner(numMdlAntiguedad);

        JTextArea txtDescripcion = new JTextArea(8, 55);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        JRadioButton rbSi = new JRadioButton("Si");
        JRadioButton rbNo = new JRadioButton("No");

        JButton btnImagenes = new JButton("seleccionar imagenes: ");

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        
        //Ajuste de Componentes
        txtNombre.setMaximumSize(txtNombre.getPreferredSize());
        txtNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinPrecio.setMaximumSize(spinPrecio.getPreferredSize());
        spinPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinExpensas.setMaximumSize(spinExpensas.getPreferredSize());
        spinExpensas.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtDireccion.setMaximumSize(txtDireccion.getPreferredSize());
        txtDireccion.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinMetrosTotales.setMaximumSize(spinMetrosTotales.getPreferredSize());
        spinMetrosTotales.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinMetrosCubiertos.setMaximumSize(spinMetrosCubiertos.getPreferredSize());
        spinMetrosCubiertos.setAlignmentX(Component.LEFT_ALIGNMENT);

        JScrollPane pnlscrolltxt = new JScrollPane(txtDescripcion);
        pnlscrolltxt.setMaximumSize(pnlscrolltxt.getPreferredSize());
        pnlscrolltxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinCantAmb.setMaximumSize(spinCantAmb.getPreferredSize());
        spinCantAmb.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinCantBaños.setMaximumSize(spinCantBaños.getPreferredSize());
        spinCantBaños.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinCantPisos.setMaximumSize(spinCantPisos.getPreferredSize());
        spinCantPisos.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinAntiguedad.setMaximumSize(spinAntiguedad.getPreferredSize());
        spinAntiguedad.setAlignmentX(Component.LEFT_ALIGNMENT);

        cmbTipoLista.setMaximumSize(cmbTipoLista.getPreferredSize());
        cmbTipoLista.setAlignmentX(Component.LEFT_ALIGNMENT);

        cmbTipoLista.addActionListener(e -> {
            int i = cmbTipoLista.getSelectedIndex();
            if (i == 0){
                 spinExpensas.setModel(new SpinnerNumberModel(1, 0, 1000000, 1));
                 return;
            }
            spinExpensas.setModel(new SpinnerNumberModel(0, 0, 0, 0));
        });

        cmbTipoProiedad.setMaximumSize(cmbTipoProiedad.getPreferredSize());
        cmbTipoProiedad.setAlignmentX(Component.LEFT_ALIGNMENT);

        rbNo.setSelected(true);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbSi);
        grupo.add(rbNo);

        txtLat.setMaximumSize(txtLat.getPreferredSize());
        txtLat.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtLat.setFocusable(false);

        txtLon.setMaximumSize(txtLon.getPreferredSize());
        txtLon.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtLon.setFocusable(false);

        Mapa mp = new Mapa(pnlMapa, -24.795784554541317, -65.42473102022629, (lat, lon) -> {
            txtLat.setText(String.valueOf(lat));
            txtLon.setText(String.valueOf(lon));
        });
        mp.getMapa().setPreferredSize(new Dimension(mpAnchura,mpAltura));

        btnImagenes.addActionListener(e -> {
            imagenes = GestorArchivo.elegirImagenes(this);
        });

        btnGuardar.addActionListener(e -> {
            double lat = Double.parseDouble(txtLat.getText().trim());
            double lon= Double.parseDouble(txtLon.getText().trim());
            
            if(imagenes.size()==0) imagenes.add("archivos/imagenes/default.png");
            Propiedad nuePropiedad = new Propiedad(txtNombre.getText(),(int)spinPrecio.getValue(),(String) cmbTipoProiedad.getSelectedItem(),(String) cmbTipoLista.getSelectedItem(),txtDireccion.getText(),(int) spinMetrosTotales.getValue(),(int) spinMetrosCubiertos.getValue(),(int) spinCantAmb.getValue(), (int) spinCantBaños.getValue(), rbSi.isSelected(), (int) spinExpensas.getValue(), (int) spinAntiguedad.getValue(), (int) spinCantPisos.getValue(), txtDescripcion.getText(), imagenes, lat, lon );
            
            //Actualización de la grida de propiedades
           
            dispose();
           
            ventanaPrincipal.añadirPropiedad(nuePropiedad);
            ventanaPrincipal.refrescarGrilla();
            
        });
        
        btnCancelar.addActionListener(e -> dispose());


        //Agrego
        //Agregar a Paneles
        pnlCentro.add(lblNombre);
        pnlCentro.add(txtNombre);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblPrecio);
        pnlCentro.add(spinPrecio);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblExpensas);
        pnlCentro.add(spinExpensas);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblTipoInmueble);
        pnlCentro.add(cmbTipoProiedad);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblTipoListado);
        pnlCentro.add(cmbTipoLista);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblDireccion);
        pnlCentro.add(txtDireccion);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblMetrosTotales);
        pnlCentro.add(spinMetrosTotales);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblMetrosCubiertos);
        pnlCentro.add(spinMetrosCubiertos);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblCantPisos);
        pnlCentro.add(spinCantPisos);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblCantAmb);
        pnlCentro.add(spinCantAmb);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblCantBaños);
        pnlCentro.add(spinCantBaños);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblCochera);
        pnlCentro.add(rbSi);
        pnlCentro.add(rbNo);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblAntiguedad);
        pnlCentro.add(spinAntiguedad);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblDescripcion);
        pnlCentro.add(pnlscrolltxt);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(btnImagenes);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(lblUbicacion);
        pnlCentro.add(txtLat);
        pnlCentro.add(txtLon);
        pnlCentro.add(Box.createVerticalStrut(15));
        pnlCentro.add(pnlMapa);

        pnlSur.add(btnGuardar);
        pnlSur.add(btnCancelar);

        pnlContenedor.add(pnlNorte, BorderLayout.NORTH);
        pnlContenedor.add(pnlEste, BorderLayout.EAST);
        pnlContenedor.add(pnlCentro, BorderLayout.CENTER);
        pnlContenedor.add(pnlOeste, BorderLayout.WEST);
        pnlContenedor.add(pnlSur, BorderLayout.SOUTH);

        //Agregar a Panel Principal
        add(pnlContenedor);
        JScrollPane pnlScroll = new JScrollPane(pnlContenedor);
        pnlScroll.getVerticalScrollBar().setUnitIncrement(16);
        setContentPane(pnlScroll);
    }

    public static void main(String[] args){
        FlatLightLaf.setup();
        
        /* Desactivada la llamada desde acá
        SwingUtilities.invokeLater(() -> {new InterfazDatos().setVisible(true);
  
        });
        
        */
    }
}
