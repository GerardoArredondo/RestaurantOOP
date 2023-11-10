package modelo;

import java.util.LinkedList;

/**
 * <h1>Clase Mesa</h1>
 * <p>Esta clase es un hilo que contiene a los comensales, permitiendo que coman o no dependiendo de si tienen su platillo o no.</p>
 * 
 * @author Equipo 2
 */
public class Mesa extends Thread{
    /** int que indica el numero de mesa, permitiendo encontrarla en el restaurante. */
    private int id;
    /** booleano que indica si la mesa está ocupada (tiene comensales) o no. */
    private boolean ocupada;
     /** Lista ligada que contiene a los comensales */
    private LinkedList<Comensal> comensales;
     /** int que indica el numero de personas en la mesa. */
    private int numPersonas; 
     /** boolean que indica si terminar la ejecución. */
    private boolean salir = false;

    /**
     * Genera una mesa sin comensales, asignandole un numero de mesa.
     * @param int id que guarda el numero de la mesa.
     */
    public Mesa(int id){
        this.id = id;
        ocupada = false;
        comensales = new LinkedList<>();
        this.numPersonas = 0;
    }
    
    //***GETTERS Y SETTERS***
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return id;
    }
    
    public void setComensales(int n){
        for(int i = 0; i < n; i++){
            comensales.add(new Comensal(id));
        }
        this.numPersonas = n;
        this.ocupada = true;
    }
    public LinkedList<Comensal> getComensales(){
        return comensales;
    }

    public void setNumPersonas(){
        this.numPersonas = comensales.size();
    }
    public int getNumPersonas(){
        return numPersonas;
    }

    public void setOcupada(boolean b){
        this.ocupada = b;
    }
    public boolean getOcupada(){
        return ocupada;
    }
    public void setSalir(boolean b){
        this.salir = b;
    }
    
    
    /**
     * Método que hace que todos los comensales de la mesa coman. Si el comensal está satisfecho, se imprime que terminó de comer.
     * @param Ninguno
     * @return Nada
     * @throws InterruptedException En caso de que sleep interrumpa las acciones del programa.
     */
    public void comerMesa() throws InterruptedException{
        for(Comensal cliente : comensales){
            cliente.comer();
            if(cliente.getSatisfecho())
                System.out.println("Ha terminado de comer.");
        }
        
    }
    
    /**
     * Metodo que hace que, en el momento de que todos los comensales de una mesa estén satisfechos, se retiren. Asi, la mesa se vuelve disponible de nuevo.
     * @param Ninguno
     * @return Nada
     * @throws InterruptedException 
     */
    public void retirarse() throws InterruptedException{
        boolean todos = true;
        for(Comensal cliente : comensales){
            if(!cliente.getSatisfecho())
                todos = false;
        }
        if(todos){
            comensales.removeAll(comensales);
            ocupada = false;
        }
    }
    
    /**
     * Metodo que sobreescribe el metodo default toString, permitiendo imprimir la mesa.
     * @param Ninguno
     * @return String que indica el numero de comensales en la mesa, o en caso de que no tenga ninguno, indica que está vacía.
     */
    @Override
    public String toString(){
        if(this.ocupada)
            return "Tiene: " + numPersonas + " comensales\n" + comensales;
        else
            return "Esta vacia\n";
    }
    
    /**
     * Metodo que sobreescribe a run() de Thread, permitiendo la ejecucion del hilo mesa.
     * @param Ninguno
     * @return Nada
     * @throws InterruptedException En caso de que sleep interrumpa las acciones del programa.
     */
    @Override
    public void run(){
        try{ 
            while(!salir){
                comerMesa();
                sleep(5000);
                retirarse();
            }
        } 
        catch(InterruptedException e){ 
            System.out.println("Se interrumpieron las actividades de los comensales.");
        }   
    }

}
