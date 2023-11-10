package modelo;

import java.util.concurrent.TimeUnit;

/**
 * <h1>Clase Comensal</h1>
 * <p>Clase que representa a los clientes que llegan al restaurante.</p>
 * 
 * @author Equipo 2
 */
public class Comensal{
    /** Booleano que representa si el comensal ya tiene su platillo, para que empiece a comer.  */
    private boolean platillo; 
    /** Booleano que representa si el comensal ya tiene su pedido listo, para que el mesero lo atienda.  */
    private boolean pedido; 
    /** Booleano que indica que el comensal ya comió, por lo que se puede retirar.  */
    private boolean satisfecho; 
    /** Objeto pedido que guarda la orden deseada.  */
    private Pedido orden; 
    /** int que indica en qué mesa está sentado.  */
    private int mesaAsignada; 
    
    /**
     * Crea un comensal, sin platillo, ni pedido (y por lo tanto tampoco está satisfecho). Se le asigna el número de mesa.
     * @param int n que indica el número de mesa en el que está sentado.
     */
    public Comensal(int n){
        this.platillo = false;
        this.pedido = false;
        this.satisfecho = false;
        this.mesaAsignada = n;
    }
    
    
    //***GETTERS Y SETTERS***
    public void setPlatillo(boolean b){
        this.platillo = b;
    }
    public boolean getPlatillo(){
        return platillo;
    }

    public void setPedido(boolean b){
        this.pedido = b;
    }
    public boolean getPedido(){
        return pedido;
    }

    public void setSatisfecho(boolean b){
        this.satisfecho = b;
    }
    public boolean getSatisfecho(){
        return satisfecho;
    }

    public void setOrden(Pedido orden){
        this.orden = orden;
        setPedido(true);
    }
    public Pedido getOrden(){
        return orden;
    }
    
    public void setMesaAsignada(int m){
        this.mesaAsignada = m;
    }
    public int getMesaAsignada(){
        return mesaAsignada;
    }
    
    
    /**
     * Método que, en el momento en el que el comensal recibe su platillo, hace que mediante un ciclo for imprima 5 veces que está comiendo, cada una con un segundo de diferencia. Posteriormente, se marca que está satisfecho, y platillo y pedido ambos se marcan como false.
     * @param Ninguno
     * @return nada
     * @throws InterruptedException En caso de que sleep interrumpa las acciones del programa.
     */
    public void comer() throws InterruptedException{
        if(platillo){
            for(int i = 0; i<5; i++){
                System.out.println("Comensal de la mesa " + mesaAsignada + " esta comiendo");
                TimeUnit.SECONDS.sleep(1);
            }
            setSatisfecho(true);
            setPedido(false);
            setPlatillo(false);
        }
    }
    
    /**
     * Metodo que sobreescribe el metodo default toString, permitiendo imprimir al comensal.
     * @param Ninguno
     * @return String que indica la mesa en la que está sentado el comensal. Además imprime si está comiendo, si espera que le tomen la orden o si ya terminó de comer.
     */
    @Override
    public String toString(){
        String mesa = "El cliente esta en la mesa: " + mesaAsignada + "\n";
        if(this.platillo)
            return mesa + "El comensal esta comiendo\n";
        else if(this.pedido)
            return mesa + "El comensal espera que le tomen la orden.\n";
        else if(this.satisfecho)
            return mesa + "El comensal ha terminado de comer.\n";
        else
            return mesa;
    }
}
