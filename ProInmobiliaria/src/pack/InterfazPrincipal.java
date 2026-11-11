package pack;
//Librerias
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.*;

//La interfaz principal desde la cual se acceden las demás interfaces. Permite añadir, filtrar y ampliar detalles de cada Propiedad almacenada. 
public class InterfazPrincipal extends JFrame{
	
    //Datos del dispotivo local
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

    //Inicialización de Paneles
    private JPanel pnlLogo = new JPanel();
    private JPanel pnlCentro = new JPanel(new BorderLayout());

    private JScrollPane scroll = new JScrollPane();

    private PanelRedondo pnlFiltrador = new PanelRedondo(20);
    private JPanel pnlFiltrador2 = new JPanel();
    private JPanel pnlGrilla = new JPanel();
    private JPanel pnlMarco = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    private JPanel pnlLado = new JPanel(new BorderLayout());

    private CardLayout cards = new CardLayout();
    private JPanel pnlCards = new JPanel(cards);
    private JPanel pnlVistaGrilla = new JPanel(new BorderLayout());
    
    private InterfazAmpliada detalle;

    private final String[] operacion = {"Todos","Venta","Alquiler","Alquiler Temporal"};
    private final List<String> opciones = List.of("Departamento", "Casa", "Terreno", "Local", "Quinta", "Galpón", "Deposito", "Campo", "Hotel", "Edificio", "Otros");
    private List<JCheckBox> checks = new ArrayList<>();
    private ArrayList<Propiedad> listaPropiedades;
    private ArrayList<Propiedad> mostrando;
    
    
    //Ícono de la ventana
    private static ImageIcon imglogo = new ImageIcon(Toolkit.getDefaultToolkit().getImage("archivos/imagenes/LogoT.png"));

    InterfazPrincipal(){
    	
    	//Creación de la ventana
        setTitle("Inmobiliaria");
        setSize((int)(pantalla.width * 0.6), (int)(pantalla.height * 0.7));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("archivos/imagenes/UcasalIcono.jpg"));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Ajuste de Imagenes
        Image img = imglogo.getImage().getScaledInstance((int)(pantalla.width * 0.09), (int)(pantalla.width * 0.05), Image.SCALE_SMOOTH);


        //Ajustes de Layout
        pnlFiltrador.setLayout(new BorderLayout());
        pnlFiltrador2.setLayout(new BoxLayout(pnlFiltrador2, BoxLayout.Y_AXIS));

        pnlGrilla.setLayout(new GridLayout(0, 3, 15, 15));


        //Ajuste de Paneles
        pnlLogo.setPreferredSize(new Dimension(MAXIMIZED_HORIZ,110));
        pnlLogo.setBackground(Color.WHITE);

        pnlFiltrador.setBackground(Color.WHITE);
        pnlFiltrador2.setBackground(Color.WHITE);

        pnlMarco.setBorder(BorderFactory.createTitledBorder("Explorador"));
        pnlGrilla.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        scroll.getVerticalScrollBar().setUnitIncrement(16);


        //Labels
        //Inicializo Labels
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        JLabel lblTipoLista = new JLabel("Tipo de lista: ");
        JLabel lblInmueble = new JLabel("Tipo de Inmueble: ");
        JLabel lblPrecioI = new JLabel("Precio desde: ");
        JLabel lblPrecioF = new JLabel("Hasta:");


        //Ajustes de Labels
        lblTipoLista.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblInmueble.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPrecioI.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPrecioF.setAlignmentX(Component.LEFT_ALIGNMENT);


        //Componenetes
        //Inicializo botones Componentes
        JButton btnAgregar = new JButton("Agregar");
        JButton btnFiltrar = new JButton("Filtrar");

        JComboBox<String> cmbOperacion = new JComboBox<>(operacion);

        SpinnerNumberModel numMdlDesde= new SpinnerNumberModel(0, 0, 1000000000, 1);
        JSpinner spinDesde = new JSpinner(numMdlDesde);

        SpinnerNumberModel numMdlHasta= new SpinnerNumberModel(0, 0, 1000000000, 1);
        JSpinner spinHasta = new JSpinner(numMdlHasta);


        //Ajuste de Eventos
        cmbOperacion.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbOperacion.setMaximumSize(cmbOperacion.getPreferredSize());

        btnAgregar.addActionListener(e -> {InterfazDatos ventana = new InterfazDatos(this); ventana.setVisible(true);});

        btnFiltrar.addActionListener(e -> {
            mostrando = new ArrayList<>(listaPropiedades);

            if (cmbOperacion.getSelectedIndex() != 0) {
                mostrando.removeIf(p -> !p.getTipoListado().equals((String) cmbOperacion.getSelectedItem()));
            }

            List<String> tipos = new ArrayList<>();
            for (JCheckBox cb : checks) {
                if (cb.isSelected()) tipos.add(cb.getText());
            }
            if (!tipos.isEmpty()) {
                mostrando.removeIf(p -> !tipos.contains(p.getTipoInmueble()));
            }

            int desde = ((Number) spinDesde.getValue()).intValue();
            int hasta = ((Number) spinHasta.getValue()).intValue();
            if (desde > 0){
                mostrando.removeIf(p -> p.getPrecio() < desde);
            }
            if (hasta > 0){
                mostrando.removeIf(p -> p.getPrecio() > hasta);
            }

            refrescarGrilla();
        });

        spinDesde.setMaximumSize(spinDesde.getPreferredSize());
        spinDesde.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinHasta.setMaximumSize(spinHasta.getPreferredSize());
        spinHasta.setAlignmentX(Component.LEFT_ALIGNMENT);


        //Agregar
        //Agregar a Paneles
        pnlLogo.add(lblLogo);

        pnlFiltrador2.add(Box.createVerticalStrut(8)); //Todo esto es el menu del "Filtro"
        pnlFiltrador2.add(lblTipoLista);
        pnlFiltrador2.add(cmbOperacion);
        pnlFiltrador2.add(Box.createVerticalStrut(15));
        pnlFiltrador2.add(lblInmueble);
        for(String op: opciones){
            JCheckBox cb = new JCheckBox(op);
            checks.add(cb);
            cb.setAlignmentX(Component.LEFT_ALIGNMENT);
            pnlFiltrador2.add(cb);
        }
        pnlFiltrador2.add(Box.createVerticalStrut(15));
        pnlFiltrador2.add(lblPrecioI);
        pnlFiltrador2.add(spinDesde);
        pnlFiltrador2.add(lblPrecioF);
        pnlFiltrador2.add(spinHasta);
        pnlFiltrador2.add(Box.createVerticalStrut(15));

        pnlFiltrador.add(btnAgregar, BorderLayout.NORTH);
        pnlFiltrador.add(pnlFiltrador2, BorderLayout.CENTER);
        pnlFiltrador.add(btnFiltrar, BorderLayout.SOUTH);
        
        listaPropiedades = cargarPropiedades();
        mostrando = new ArrayList<Propiedad>(listaPropiedades); 
        
        refrescarGrilla(); 

        pnlCentro.add(pnlGrilla, BorderLayout.NORTH);

        scroll.setViewportView(pnlCentro);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        pnlLado.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 0));
        pnlLado.add(pnlFiltrador, BorderLayout.NORTH);

        pnlVistaGrilla.add(pnlLado, BorderLayout.WEST);
        pnlVistaGrilla.add(scroll, BorderLayout.CENTER);

        pnlCards.add(pnlVistaGrilla, "grilla");

        add(pnlLogo, BorderLayout.NORTH);
        add(pnlCards, BorderLayout.CENTER);
    }
    
    //Función dada al hacer click en la tarjeta particular a una Propiedad
    
    private void abrirDetalle(Propiedad p) {
        if (detalle != null) pnlCards.remove(detalle);
        detalle = new InterfazAmpliada(p, this);
        pnlCards.add(detalle, "detalle");
        cards.show(pnlCards, "detalle");
    }

    //Función de retorno para volver a la interfaz principal
    protected void volverAGrilla() {
        cards.show(pnlCards, "grilla");
    }
    
    //Reinicialización de los Objetos Propiedad que se muestran. Esto solo ocurre si se añadió o borro una propiedad. 
    public void refrescarGrilla() {
    	
    	pnlGrilla.removeAll();
    	for(Propiedad propiedad : mostrando){
            Tarjeta tarjeta = new Tarjeta(propiedad, () -> abrirDetalle(propiedad));
            pnlGrilla.add(tarjeta);
        }
    	
    	revalidate();
    	repaint();
    }    
    
    
    //Método que añade la propiedad a los archivos y renueva la lista de las que se mostrarán en la interfaz principal
    public void añadirPropiedad(Propiedad añadiendo) {
        GestorArchivo.guardadoJSON(añadiendo);
    	listaPropiedades.add(añadiendo);
    	mostrando = new ArrayList<Propiedad>(listaPropiedades);
    }
    
    //Método que elimina la propiedad a los archivos y renueva la lista de las que se mostrarán en la interfaz principal

    public void eliminarPropiedad(Propiedad removiendo) {
        GestorArchivo.borradoJSON(removiendo);
    	listaPropiedades.remove(removiendo);
    	mostrando = new ArrayList<Propiedad>(listaPropiedades);
    }
    
    //Sobrecarga del método anterior, con la diferencia que no intena borrar el archivo, solo la remueve de la lista.  
    public void eliminarPropiedad(Propiedad removiendo, int auxiliar) {
        
    	listaPropiedades.remove(removiendo);
    	mostrando = new ArrayList<Propiedad>(listaPropiedades);
    }
    
    //Método utilizado para cargar las propiedades desde los archivos almacenados a un ArrayList<Propiedad> que se retorna. 
    //Se utiliza para cargarlas al crear el objeto InterfazPrincipal.
    public ArrayList<Propiedad> cargarPropiedades() {
    	
    	ArrayList<Propiedad> propiedades = new ArrayList<Propiedad>();
    	try (var archivos = Files.list(Path.of("").toAbsolutePath().resolve("archivos").resolve("propiedades"))) {
        	archivos.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".json"))
    	        	.forEach( direccion -> {propiedades.add(GestorArchivo.cargarJSON(direccion.getFileName().toString()));});
        } catch (IOException e) {
    	        System.out.println("Error accediendo a la carpeta");
    	}
    	
    	return propiedades;
    }

}
