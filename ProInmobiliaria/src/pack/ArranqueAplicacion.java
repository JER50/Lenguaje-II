package pack;

//Tema
import com.formdev.flatlaf.FlatLightLaf;

//librerias
import javax.swing.SwingUtilities;

//Clase de ejecución: Para iniciar el programa
public class ArranqueAplicacion {

	public static void main(String[] args) {
		
		//Se aplica el nuevo tema para los objetos
		FlatLightLaf.setup();
    	
		//Se inicializa la ventana principal
		InterfazPrincipal ventana = new InterfazPrincipal(); 
    	    
		//Se añade al hilo de ejecución de Swing la ejecución del programa
    	SwingUtilities.invokeLater( () -> { ventana.setVisible(true); } );
	}

}