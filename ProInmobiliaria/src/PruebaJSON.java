import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;

public class PruebaJSON {
	static Propiedad casa = new Propiedad("Casa de prueba", 100,"Casona","Alquiler","ahi a la vuela",100,50,2,1,true,10,2,2,"Casa muy linda",new ArrayList<String>(),0,0);
	
	public static void main(String[] args) throws JsonIOException, IOException {
		
		Gson administrador = new Gson(); 
		
		FileWriter escritor = new FileWriter("C:\\Users\\Matias Avila\\Documents\\2026\\Repositorios\\Proyecto Lenguajes II\\ProInmobiliaria\\propiedades\\propiedadA.json"); 
		
		administrador.toJson(casa,escritor);
		
		escritor.close();
		
		try(Reader lector = new FileReader("C:\\Users\\Matias Avila\\Documents\\2026\\Repositorios\\Proyecto Lenguajes II\\ProInmobiliaria\\propiedades\\propiedadA.json")){
			Propiedad casa2 = administrador.fromJson(lector, Propiedad.class);
			System.out.print(casa2.toString());
		}
		
		
		
		
	}
	
	
}
