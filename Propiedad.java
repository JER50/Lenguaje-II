import java.util.ArrayList;
public class Propiedad {
    
	//Bombardeen caracas
	
	//Atributos
    private String nombre;
    private int precio;
    private String tipoInmueble;
    private String tipoListado;
    private String direccion;
    private int metrosTotales;
    private int metrosCubiertos;
    private int cantAmb;
    private int cantBaños;
    private boolean cochera;
    private int expensas;
    private int antigüedad;
    private int pisos;
    private String descripción;
    private ArrayList<String> imagenes;
    private double latitud;
    private double longitud;

    //Constructor
    public Propiedad(String nombre, int precio, String tipoInmueble, String tipoListado, String direccion, int metrosTotales, int metrosCubiertos,int cantAmb, int cantBaños, boolean cochera, int expensas, int antigüedad, int pisos, String descripción, ArrayList<String> imagenes, double latitud, double longitud) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoInmueble = tipoInmueble;
        this.tipoListado = tipoListado;
        this.direccion = direccion;
        this.metrosTotales = metrosTotales;
        this.metrosCubiertos = metrosCubiertos;
        this.cantAmb = cantAmb;
        this.cantBaños = cantBaños;
        this.cochera = cochera;
        this.expensas = expensas;
        this.antigüedad = antigüedad;
        this.pisos = pisos;
        this.descripción = descripción;
        this.imagenes = imagenes;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    //Getters y Setters
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getPrecio() {return precio;}
    public void setPrecio(int precio) {this.precio = precio;}

    public String getTipoInmueble() {return tipoInmueble;}
    public void setTipoInmueble(String tipoInmueble) {this.tipoInmueble = tipoInmueble;}

    public String getTipoListado() {return tipoListado;}
    public void setTipoListado(String tipoListado) {this.tipoListado = tipoListado;}

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public int getMetrosTotales() {return metrosTotales;}
    public void setMetrosTotales(int metrosTotales) {this.metrosTotales = metrosTotales;}

    public int getMetrosCubiertos() {return metrosCubiertos;}
    public void setMetrosCubiertos(int metrosCubiertos) {this.metrosCubiertos = metrosCubiertos;}

    public int getCantAmb() {return cantAmb;}
    public void setCantAmb(int cantAmb) {this.cantAmb = cantAmb;}

    public int getCantBaños() {return cantBaños;}
    public void setCantBaños(int cantBaños) {this.cantBaños = cantBaños;}

    public boolean isCochera() {return cochera;}
    public void setCochera(boolean cochera) {this.cochera = cochera;}

    public int getExpensas() {return expensas;}
    public void setExpensas(int expensas) {this.expensas = expensas;}

    public int getAntigüedad() {return antigüedad;}
    public void setAntigüedad(int antigüedad) {this.antigüedad = antigüedad;}

    public int getPisos() {return pisos;}
    public void setPisos(int pisos) {this.pisos = pisos;}

    public String getDescripción() {return descripción;}
    public void setDescripción(String descripción) {this.descripción = descripción;}

    public ArrayList<String> getImagenes() {return imagenes;}
    public void setImagenes(ArrayList<String> imagenes) {this.imagenes = imagenes;}

    public double getLatitud() {return latitud;}
    public void setLatitud(double latitud) {this.latitud = latitud;}

    public double getLongitud() {return longitud;}
    public void setLongitud(double longitud) {this.longitud = longitud;}
}
