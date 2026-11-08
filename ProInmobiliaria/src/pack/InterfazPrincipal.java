package pack;
//tema
import com.formdev.flatlaf.FlatLightLaf;
//Librerias
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import  java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;

import java.io.*;

//Principal
public class InterfazPrincipal extends JFrame{
    //Carpeta de imagenes
    private static final Path carpetaArchivos = Path.of(Path.of("").toAbsolutePath().toString(), "archivos");

    //Dispositivo
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

    //Paneles
    JPanel pnlLogo = new JPanel();
    JPanel pnlCentro = new JPanel(new BorderLayout());

    JScrollPane scroll = new JScrollPane();

    PanelRedondo pnlFiltrador = new PanelRedondo(20);
    JPanel pnlFiltrador2 = new JPanel();
    JPanel pnlGrilla = new JPanel();
    JPanel pnlMarco = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    JPanel pnlLado = new JPanel(new BorderLayout());

    CardLayout cards = new CardLayout();
    JPanel pnlCards = new JPanel(cards);
    JPanel pnlVistaGrilla = new JPanel(new BorderLayout());
    InterfazAmpliada detalle;

    String[] operacion = {"Venta","Alquiler","Alquiler Temporal"};
    List<String> opciones = List.of("Departamento", "Casa", "Terreno", "Local", "Quinta", "Galpón", "Deposito", "Campo", "Hotel", "Edificio", "Otros");
    List<JCheckBox> checks = new ArrayList<>();

    //imagenes
    ImageIcon imglogo = new ImageIcon(Toolkit.getDefaultToolkit().getImage(carpetaArchivos.resolve("imagenes").resolve("LogoT.png").toString()));

    InterfazPrincipal(ArrayList<Propiedad> propList){
        setTitle("Inmobiliaria");
        setSize((int)(pantalla.width * 0.6), (int)(pantalla.height * 0.7));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(carpetaArchivos.resolve("imagenes").resolve("UcasalIcono.jpg").toString()));
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

        btnAgregar.addActionListener(e -> {InterfazDatos ventana = new InterfazDatos(); ventana.setVisible(true);});
        btnFiltrar.addActionListener(null);

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

        for(Propiedad propiedad : propList){
            Tarjeta tarjeta = new Tarjeta(propiedad, () -> abrirDetalle(propiedad));
            pnlGrilla.add(tarjeta);
        }

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

    private void abrirDetalle(Propiedad p) {
        if (detalle != null) pnlCards.remove(detalle);
        detalle = new InterfazAmpliada(p, this::volverAGrilla);
        pnlCards.add(detalle, "detalle");
        cards.show(pnlCards, "detalle");
    }

    private void volverAGrilla() {
        cards.show(pnlCards, "grilla");
    }
    
    public static void main(String[] args){
    	
    	FlatLightLaf.setup();
    	 
    	 ArrayList<String> List1 = new ArrayList<>();
    	 
    	 for(int i = 0; i<2; i++){
    	        
    	        List1.add(carpetaArchivos.resolve("imagenes").resolve("Logo.png").toString());
    	        List1.add(carpetaArchivos.resolve("imagenes").resolve("LogoT.png").toString());
    	 }

    	 ArrayList<Propiedad> propiedades = new ArrayList<>();
    	    
         try (var archivos = Files.list(carpetaArchivos.resolve("propiedades"))) {
        	 
        	 
        	 archivos.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".json"))
    	        	.forEach( nombre -> {propiedades.add(GestorArchivo.cargarJSON(nombre.getFileName().toString()));});

         } catch (IOException e) {
    	        System.out.println("Error accediendo a la carpeta");
    	 }
    	    
    	    
    	for(int i = 0; i<24; i++){
    	        Propiedad prop = new Propiedad("Casa", 110, "Casa", "Venta", "aqui Direccion", 1,1,1,1,true,10,10,1,"Hola",List1,-25,-25);
    	        propiedades.add(prop);
    	        propiedades.add(prop);
    	    }

    	    SwingUtilities.invokeLater(() -> {new InterfazPrincipal(propiedades).setVisible(true);
    	        });
    	    }
}
