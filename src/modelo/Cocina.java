package modelo;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 * <h1>Clase Cocina</h1>
 * <p>Esta clase es un hilo, encargado de 2 acciones</p>
 * <ul>
 * <li>Tener guardado el menú de platillos en 2 hashtables. Esto es estático y funciona como base de datos.</li>
 * <li>Recibir los pedidos del mesero y producir los platillos. </li>
 * </ul>
 * 
 * @author Equipo 2
 */
public class Cocina extends Thread{

    /** Hashtable que guarda el ID como llave y el nombre del platillo como valor. */
    private final Hashtable<Integer, String> cocinaMenu = new Hashtable<>();
    /** Hashtable que guarda el ID como llave y el precio del platillo como valor. */
    private final Hashtable<String, Float> cocinaPrecio = new Hashtable<>();
    /** Lista ligada que guarda los pedidos de los comensales, entregados por el mesero. */
    private LinkedList<Pedido> pedidosCocina = new LinkedList<>();
    /** Lista ligada que guarda los platillos cocinados, listos para ser recogidos. */
    private LinkedList<Pedido> platillosListos = new LinkedList<>();
    /** La capacidad de platillos y pedidos de la cocina. */
    private final int capacidad = 5;
    
    /**
     * Crea una cocina, guardando en las 2 hashtables cocinaMenu y cocinaPrecio 10 platos distintos.
     * @param Ninguno.
     */
    public Cocina(){
        Comida plato1 = new Comida(139.99f, "Milanesa Tradicional", 0120);
        Comida plato2 = new Comida(139.90f, "Enchiladas suizas", 0110);
        Comida plato3 = new Comida(149.99f, "Bistec empanizado", 0121);
        Comida plato4 = new Comida(165.50f, "Pechuga plancha a las finas hierbas", 1122);
        Comida plato5 = new Comida(135, "Enchiladas de mole", 0111);
        Comida plato6 = new Comida(127.70f, "Tostadas de pollo", 0140);
        Comida plato7 = new Comida(105, "Molletes tradicionales", 0130);
        Comida plato8 = new Comida(169, "Hamburguesa", 0131);
        Comida plato9 = new Comida(26, "Dona de Azucar", 0210);
        Comida plato10 = new Comida(104.50f, "Crepas de cajeta", 0211);

        cocinaMenu.put(plato1.getCodigo(), plato1.getNombre());
        cocinaMenu.put(plato2.getCodigo(), plato2.getNombre());
        cocinaMenu.put(plato3.getCodigo(), plato3.getNombre());
        cocinaMenu.put(plato4.getCodigo(), plato4.getNombre());
        cocinaMenu.put(plato5.getCodigo(), plato5.getNombre());
        cocinaMenu.put(plato6.getCodigo(), plato6.getNombre());
        cocinaMenu.put(plato7.getCodigo(), plato7.getNombre());
        cocinaMenu.put(plato8.getCodigo(), plato8.getNombre());
        cocinaMenu.put(plato9.getCodigo(), plato9.getNombre());
        cocinaMenu.put(plato10.getCodigo(), plato10.getNombre());
        
        cocinaPrecio.put(plato1.getNombre(), plato1.getPrecio());
        cocinaPrecio.put(plato2.getNombre(), plato2.getPrecio());
        cocinaPrecio.put(plato3.getNombre(), plato3.getPrecio());
        cocinaPrecio.put(plato4.getNombre(), plato4.getPrecio());
        cocinaPrecio.put(plato5.getNombre(), plato5.getPrecio());
        cocinaPrecio.put(plato6.getNombre(), plato6.getPrecio());
        cocinaPrecio.put(plato7.getNombre(), plato7.getPrecio());
        cocinaPrecio.put(plato8.getNombre(), plato8.getPrecio());
        cocinaPrecio.put(plato9.getNombre(), plato9.getPrecio());
        cocinaPrecio.put(plato10.getNombre(), plato10.getPrecio());
    }
  
    
    //***GETTERS Y SETTERS***

    public Hashtable<Integer, String> getCocinaMenu(){
        return cocinaMenu;
    }
    public Hashtable<String, Float> getCocinaPrecio(){
        return cocinaPrecio;
    }
    
    public  LinkedList<Pedido> getPedidosCocina(){
        return pedidosCocina;
    }
    public void setPedidosCocina(LinkedList<Pedido> pedidosCocina){
        this.pedidosCocina = pedidosCocina;
    }
    
    public LinkedList<Pedido> getPlatillosListos(){
        return platillosListos;
    }
    public void setPlatillosListos(LinkedList<Pedido> platillosListos){
        this.platillosListos = platillosListos;
    }
    
    public int getCapacidad(){
        return capacidad;
    }
    
    /**
     * Método protegido que recibe el código de un platillo y crea un nuevo platillo con el nombre y precio del platillo correspondiente. Además, para que no se pierda el comensal al que le toca el platillo, se guarda un String que sirve como ID del comensal.
     * @param int codigo  El ID del platillo
     * @param String mesa El ID del comensal
     * @return El platillo cocinado.
     */
    public Pedido cocinarPedido(int codigo, String mesa){
        Pedido pedido = new Pedido();
        pedido.setNombre(cocinaMenu.get(codigo));
        pedido.setClienteCorresp(mesa);
        pedido.setPrecio(cocinaPrecio.get(pedido.getNombre()));
        
        return pedido;
    }
    
    /**
     * Metodo que hace que el hilo cocina se espere por 6 segundos y luego cocina el platillo correspondiente, obteniendo los valores requeridos para hacerlo de la lista pedidosCocina. Una vez creado, agrega el platillo a la lista platillosListos.
     * 
     * @param Ninguno
     * @return Nada
     * @throws InterruptedException En caso de que sleep interrumpa las acciones del programa.
     */
    public void cocinando() throws InterruptedException{
        System.out.println("Se esta cocinando: " + pedidosCocina.element());
        sleep(6000);
        Pedido cocinado = cocinarPedido(pedidosCocina.element().getCodigo(), pedidosCocina.poll().getClienteCorresp());
        platillosListos.add(cocinado);
    }   
    
    /**
     * Método que le permite a la cocina tomar 3 posibles acciones, dependiendo de los platillos pendientes y los listos:
     * <ul>
     * <li>Si se tiene espacio disponible en platillosListos y tiene elementos en pedidosCocina, se pone a cocinar.</li>
     * <li>Si platillosListos y pedidosCocina están vacíos, la cocina no puede hacer nada. Por lo que espera más pedidos por 3 segundos. </li>
     * <li>Si platillosListos se llenó, la cocina no puede cocinar más. Por lo que espera al mesero por 3 segundos. </li>
     * <li>Si platillosListos tiene elementos, se imprimen. </li>
     * </ul>
     * Después de haber tomado una o más acciones, se avisa y se espera por otros 3 segundos.
     * 
     * @param Ninguno
     * @return nada
     * @throws InterruptedException En caso de que sleep interrumpa las acciones del programa.
     */
    public void produccionPlatillos() throws InterruptedException{ 
        synchronized (this){ 
            while(platillosListos.size() < capacidad && !pedidosCocina.isEmpty()){
                this.cocinando();
            }   

            if(pedidosCocina.isEmpty() && platillosListos.isEmpty()){
                System.out.println("La cocina espera pedidos...");
                this.wait(3000); 
            }

            if(platillosListos.size() == capacidad){
                System.out.println("La cocina espera al mesero...");
                this.wait(3000); 
            }

            if(!platillosListos.isEmpty())
                System.out.println("Platillos preparados: " + platillosListos);

            notify(); 
            this.wait(3000); 
        } 
    } 
}