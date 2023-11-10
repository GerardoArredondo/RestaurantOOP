package modelo;

/**
 * <h1>Clase FachadaRestaurante</h1>
 * <p>Esta clase permite tener en un solo lugar al restaurante, cocina y mesero, permitiendo la ejecución fluida del programa. Además, permite tener una ejecución más limpia, ya que esto no es visible para el usuario.</p>
 * 
 * @author Equipo 2
 */
public class FachadaRestaurante {
    /** El restaurante que contiene las mesas y por lo tanto, los comensales */
    private Restaurante restaurante;
    /** La cocina que contiene el menu y que produce los platillos  */
    private Cocina cocina;
    /** El mesero que atiende a los comensales, entrega pedidos a cocina y recoge platillos de esta.  */
    private Mesero mesero;
    /**  Booleano que indica si salir del programa o no   */
    private boolean salir = false;
    
    //***GETTERS Y SETTERS***//
    public Restaurante getRestaurante(){
        return restaurante;
    }
    
    public Cocina getCocina(){
        return cocina;
    }
    
    public Mesero getMesero(){
        return mesero;
    }
    
    public void setSalir(boolean salir){
        this.salir = salir;
    }
    public boolean getSalir(){
        return salir;
    }
      
    /**
     * Crea una fachada, con un restaurante con 8 mesas, una cocina y un mesero. A cocina y mesero, respectivamente, se crean los metodos de run para indicar como se va a ejecutar.
     * @param Nada.
     */
    public FachadaRestaurante(){
        
        this.restaurante = new Restaurante(8);
        
        this.cocina = new Cocina(){
            /**
             * Metodo que sobreescribe a run() de Thread, permitiendo la ejecucion del hilo cocina.
             * @param Ninguno
             * @return Nada
             */
            @Override
            public void run(){ 
                try{ 
                    while(!salir)
                        this.produccionPlatillos(); 
                } 
                catch(InterruptedException e){ 
                    System.out.println("Se interrumpio la produccion de platillos");
                }        
            }
        };
        
        this.mesero = new Mesero(){
            /**
             * Metodo que sobreescribe a run() de Thread, permitiendo la ejecucion del hilo mesero.
             * @param Ninguno
             * @return Nada
             */
            @Override
            public void run(){
                try{
                    synchronized(this){
                        while(!salir){
                            recoleccionPedidos(restaurante, cocina);
                            recoleccionPlatillos(restaurante, cocina);
                            this.wait(3000);
                        } 
                    } 
                }catch(InterruptedException e){ 
                    System.out.println("Se interrumpio al mesero");
                }catch(IllegalThreadStateException e){
                    System.out.println("Adios :)");
                }
            }
        }; 
    }

    /**
     * Metodo que hace que los hilos mesero, cocina y todas las mesas empiecen a ejecutarse.
     * @param Ninguno.
     * @return Nada.
     */
    public void running(){
        mesero.start();
        cocina.start();
        restaurante.getMesas().get(0).start();
        restaurante.getMesas().get(1).start();
        restaurante.getMesas().get(2).start();
        restaurante.getMesas().get(3).start();
    }
    
    /**
     * Metodo que termina la ejecucion de los hilos mesero, cocina y todas las mesas.
     * @param Ninguno.
     * @return Nada.
     */
    public void terminarHilos(){
        salir = true;
        for(int i = 0; i < 8; i++)
            restaurante.getMesas().get(i).setSalir(true);
    }
}
