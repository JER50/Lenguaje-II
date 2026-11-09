package pack;

//Tema
import com.formdev.flatlaf.FlatLightLaf;

//librerias
import javax.swing.SwingUtilities;

public class ArranqueAplicacion {

	public static void main(String[] args) {
		
		FlatLightLaf.setup();
    	
		InterfazPrincipal ventana = new InterfazPrincipal(); 
    	    
    	SwingUtilities.invokeLater( () -> { ventana.setVisible(true); } );
	}

}