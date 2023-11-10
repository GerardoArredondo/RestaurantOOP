package modelo;

/**
 * <h1>Clase Comida</h1>
 * <p>Esta clase representa a los platillos en el restaurante.</p>
 * 
 * @author Equipo 2
 */
public class Comida {
    /** Float que asigna el precio del platillo */
    private float precio;
    /** String que guarda el nombre del platillo */
    private String nombre;
    /** Int que guarda el identificador unico del platillo */
    private int codigo;

    /**
     * Crea un objeto comida vacío.
     * @param Ninguno
     */
    public Comida(){
    }
     
    /**
     * Crea un platillo, solo con el código.
     * @param int codigo unico que indica qué platillo es.
     */
    public Comida(int codigo){
        this.codigo = codigo;
    }
    
    /**
     * Crea un platillo, con precio, nombre y codigo
     * @param float precio que guarda el precio del platillo.
     * @param String nombre que guarda el nombre del platillo.
     * @param int codigo unico que indica qué platillo es.
     */
    public Comida(float precio, String nombre, int codigo){
        this.precio = precio;
        this.nombre = nombre;
        this.codigo = codigo;
    }
    
    //***GETTERS Y SETTERS***
    
    public void setPrecio(float precio){
        this.precio = precio;
    }
    public float getPrecio(){
        return precio;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public int getCodigo(){
        return codigo;
    }
    
    /**
     * Metodo que sobreescribe el metodo default toString, permitiendo imprimir el objeto Comida.
     * @param Ninguno
     * @return String que sigue el formato "Nombre - Precio #Codigo".
     */
    @Override
    public String toString(){
        return nombre + " - $" + precio + " #" + codigo ;
    }

}
