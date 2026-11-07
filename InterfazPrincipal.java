//tema
import com.formdev.flatlaf.FlatLightLaf;
//Librerias
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import  java.util.List;


public class InterfazPrincipal extends JFrame{
    //Dispositivo
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

    //Paneles
    JPanel pnlLogo = new JPanel();
    JPanel pnlCentro = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 5));

    JScrollPane scroll = new JScrollPane(pnlCentro);

    PanelRedondo pnlFiltrador = new PanelRedondo(20);
    JPanel pnlFiltrador2 = new JPanel();
    JPanel pnlGrilla = new JPanel();

    String[] operacion = {"Venta","Alquiler","Alquiler Temporal"};
    List<String> opciones = List.of("Departamento", "Casa", "Terreno", "Local", "Quinta", "Galpón", "Deposito", "Campo", "Hotel", "Edificio", "Otros");
    List<JCheckBox> checks = new ArrayList<>();

    //imagenes
    ImageIcon imglogo = new ImageIcon("\\Java\\Lenguaje II\\ProInmobiliaria\\imagenes\\LogoT.png");

    InterfazPrincipal(ArrayList<Propiedad> prop){
        setTitle("Inmobiliaria");
        setSize((int)(pantalla.width * 0.6), (int)(pantalla.height * 0.7));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("\\Java\\Lenguaje II\\ProInmobiliaria\\imagenes\\UsacalIcono.jpg"));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Ajuste de Imagenes
        Image img = imglogo.getImage().getScaledInstance(180, 110, Image.SCALE_SMOOTH);


        //Ajustes de Layout
        pnlFiltrador.setLayout(new BorderLayout());
        pnlFiltrador2.setLayout(new BoxLayout(pnlFiltrador2, BoxLayout.Y_AXIS));

        pnlGrilla.setLayout(new GridLayout(0, 3, 15, 15));


        //Ajuste de Paneles
        pnlLogo.setPreferredSize(new Dimension(MAXIMIZED_HORIZ,110));
        pnlLogo.setBackground(Color.WHITE);

        pnlFiltrador.setBackground(Color.WHITE);
        pnlFiltrador2.setBackground(Color.WHITE);

        pnlGrilla.setPreferredSize(new Dimension((int)(pantalla.width * 0.86), (int)(pantalla.height * 0.9)));
        pnlGrilla.setBorder(BorderFactory.createTitledBorder("Explorador"));


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

        JTextField txtDesde = new JTextField(10);
        JTextField txtHasta = new JTextField(10); 


        //Ajuste de Eventos
        cmbOperacion.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbOperacion.setMaximumSize(cmbOperacion.getPreferredSize());

        btnAgregar.addActionListener(e -> {InterfazDatos ventana = new InterfazDatos(); ventana.setVisible(true);});
        btnFiltrar.addActionListener(null);

        txtDesde.setMaximumSize(txtDesde.getPreferredSize());
        txtDesde.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtHasta.setMaximumSize(txtHasta.getPreferredSize());
        txtHasta.setAlignmentX(Component.LEFT_ALIGNMENT);


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
        pnlFiltrador2.add(txtDesde);
        pnlFiltrador2.add(lblPrecioF);
        pnlFiltrador2.add(txtHasta);
        pnlFiltrador2.add(Box.createVerticalStrut(15));

        pnlFiltrador.add(btnAgregar, BorderLayout.NORTH);
        pnlFiltrador.add(pnlFiltrador2, BorderLayout.CENTER);
        pnlFiltrador.add(btnFiltrar, BorderLayout.SOUTH);

        pnlCentro.add(pnlFiltrador);
        pnlCentro.add(pnlGrilla);


        //Agregar Al panel Principal
        add(pnlLogo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
    
    public static void main(String[] args){
    FlatLightLaf.setup();
    ArrayList<String> List1 = new ArrayList<>();
    for(int i = 0; i<2; i++){
        List1.add("E:\\Java\\Lenguaje II\\ProInmobiliaria\\imagenes\\Logo.png");
        List1.add("E:\\Java\\Lenguaje II\\ProInmobiliaria\\imagenes\\LogoT.png");
    }

    ArrayList<Propiedad> List2 = new ArrayList<>();
    for(int i = 0; i<12; i++){
        Propiedad prop = new Propiedad("Casa", 110, "Casa", "Venta", "aqui Direccion", 1,1,1,1,true,10,10,1,"Hola",List1,-25,-25);
        List2.add(prop);
    }

    SwingUtilities.invokeLater(() -> {new InterfazPrincipal(List2).setVisible(true);
        });
    }
}
