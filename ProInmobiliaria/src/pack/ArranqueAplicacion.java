package pack;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

public class ArranqueAplicacion {

	public static void main(String[] args) {
		
		FlatLightLaf.setup();
    	
		InterfazPrincipal ventana = new InterfazPrincipal(); 
    	    
    	SwingUtilities.invokeLater(() -> {ventana.setVisible(true);
    	
    	});
	}

}
