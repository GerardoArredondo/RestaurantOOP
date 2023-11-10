package modelo;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 * <h1>Clase Mesero</h1>
 * <p>Esta clase es un hilo que se encarga de las siguientes actividades:</p>
 * <ul>
 * <li>Verificar si los comensales ya desean ordenar y tomar su orden.</li>
 * <li>Entregar las ordenes a la cocina para que puedan preparar los alimentos</li>
 * <li>Recoger los platillos listos de la cocina y entregarlos al comensal correspondiente.</li>
 * </ul>
 * @author Equipo 2
 */
public class Mesero extends Thread{
    public int cuenta = 0;
    /** Lista ligada que guarda los platillos que se entregarán */
    private LinkedList<Pedido> platillos = new LinkedList<>();
    /** Lista ligada que guarda los pedidos de los comensales */
    private LinkedList<Pedido> pedidos = new LinkedList<>();
    /** int que indica cuantos platillos puede cargar a la vez */
    private final int capacidadPlatillos = 4;
    /** int que indica cuantos pedidos puede traer a la vez */
    private final int capacidadPedidos = 7;
    
    
    //***GETTERS Y SETTERS***
    public  LinkedList<Pedido> getPlatillos(){
        return platillos;
    }
    public void setPlatillos(LinkedList<Pedido> pedidosCocina){
        this.platillos = pedidosCocina;
    }
    
    public LinkedList<Pedido> getPedidos(){
        return pedidos;
    }
    public void setPedidos(LinkedList<Pedido> platillosListos){
        this.pedidos = platillosListos;
    }
    
    public int getCapacidadPlatillos(){
        return capacidadPlatillos;
    }
    public int getCapacidadPedidos(){
        return capacidadPedidos;
    }

    /**
     * Metodo que se encarga de que el mesero revise todas las mesas y le tome la orden a los comensales.
     * @param Hashtable<Integer, Mesa> mesas Lista de mesas en el restaurante
     * @return Nada
     * @throws InterruptedException En caso de que sleep interrumpa las acciones del programa.
     */
    public void tomarPedido(Hashtable<Integer, Mesa> mesas) throws InterruptedException{
        LinkedList<Comensal> comensales;
        for(int i = 0; i < mesas.size(); i++){
            sleep(2000);
            if(mesas.get(i).getOcupada()){
                System.out.println("La mesa esta ocupada");
                comensales = mesas.get(i).getComensales();
                for(Comensal cliente : comensales){
                    if(cliente.getPedido() && pedidos.size() < capacidadPedidos){
                        if(cliente.getOrden()!= null){
                            pedidos.add(cliente.getOrden());
                            System.out.println("Se registró la orden");
                            cliente.setOrden(null);
                        }else
                            System.out.println("Ya le tomaron la orden, pero aun no le dan su platillo.");
                    }
                    else if(pedidos.size() == capacidadPedidos)
                        System.out.println("Ya se lleno de pedidos");
                    else
                        System.out.println("El cliente aun no desea ordenar.");
                }
            }else
                System.out.println("Mesa disponible");
        }
    }
    
    /**
     * Método que hace que entregue a la cocina los pedidos recogidos. En cuanto los entrega, los elimina de su lista de pedidos.
     * @param LinkedList<Pedido> pedidosCocina Lista de la cocina en la que se guardan los pedidos a cocinar.
     * @param int capacidadCocina Entero que indica cuantos pedidos puede entregar, para evitar sobrecargarla.
     * @return Nada
     */
    public void entregarPedido(LinkedList<Pedido> pedidosCocina, int capacidadCocina){
        while(pedidosCocina.size() < capacidadCocina){
            if(!this.pedidos.isEmpty()){
                System.out.println("Se entrego el pedido a la cocina" + pedidos.element());
                pedidosCocina.add(pedidos.poll());
            }
            else
                break;
        }
    }
    
    /**
     * Método que hace que recoja de la cocina los platillos listos. En cuanto los entrega, los elimina de la lista de platillos de la cocina.
     * @param LinkedList<Pedido> platillosCocina Lista de la cocina en la que se guardan los platillos terminados.
     * @param LinkedList<Pedido> ticket Lista en la que se guardan todos los platillos ordenados, como historial de ordenes.
     * @return Nada
     */
    public void tomarPlatillo(LinkedList<Pedido> platillosCocina, LinkedList<Pedido> ticket){
        while(platillos.size() < capacidadPlatillos && !platillosCocina.isEmpty()){
            System.out.println("Se tomo el platillo de la cocina " + platillosCocina.element().getNombre());
            ticket.add(platillosCocina.element());
            platillos.add(platillosCocina.poll());
        }
    }
    /**
     * Metodo que hace que el mesero entregue los platillos a los comensales correspondientes.
     * @param Hashtable<Integer, Mesa> mesas Coleccion de mesas que se recorre a una, verificando cada comensal para entregarle su platillo.
     * @param LinkedList<Pedido> platillos Lista de platilos que puede entregar.
     * @return Nada
     * @throws InterruptedException En caso de que sleep interrumpa las acciones del programa.
     */
    public void entregarPlatillo(Hashtable<Integer, Mesa> mesas, LinkedList<Pedido> platillos) throws InterruptedException{
        LinkedList<Comensal> comensales;
        for(int i = 0; i < mesas.size(); i++){
            sleep(2000);
            if(mesas.get(i).getOcupada()){
                System.out.println("La mesa esta ocupada");
                comensales = mesas.get(i).getComensales();
                int j = 0;
                for(Comensal cliente : comensales){
                    if(cliente.getPedido() && !platillos.isEmpty()){
                        if(cliente.getOrden()== null){
                            if(buscarPlatillo(cliente, platillos, j)){
                                System.out.println("Se entregó la orden");
                                platillos.pop();
                                cliente.setPlatillo(true);
                                cliente.setPedido(false);
                            }
                        }
                    }
                    else if(platillos.isEmpty())
                        System.out.println("Ya no tengo nada que entregar");
                    else
                        System.out.println("El cliente no tiene orden esperando.");
                    j++;
                }
            }else
                System.out.println("Mesa disponible");
        }
    }
    
    /**
     * Metodo que busca en una mesa especifica que haya una coincidencia entre posicion del comensal y el identificador del platillo, para poder entregarlo a quien le corresponda.
     * @param Comensal comensal A quien le entregará el platillo.
     * @param LinkedList<Pedido> platillos Lista de platillos que puede entregar, que usa para verificar.
     * @param int j Numero actual de comensal.
     * @return booleano que refleja si el ID del comensal y el ID en el pedido son el mismo.
     */
    public boolean buscarPlatillo(Comensal comensal, LinkedList<Pedido> platillos, int j){
        String idActual = comensal.getMesaAsignada() + "-" + j;
        String idBuscada = platillos.element().getClienteCorresp();
        return idActual.equals(idBuscada);
    }
    
    /**
     * Metodo que reune la "primera mitad" de las actividades del mesero: registrar las ordenes de los comensales y entregarlas a cocina, despues de lo cual avisa y espera durante 1 segundo.
     * @param Restaurante restaurante Donde se tienen las mesas que se recorren.
     * @param Cocina cocina Donde se tiene la lista de pedidos por cocinar.
     * @return Nada.
     * @throws InterruptedException En caso de que wait interrumpa las acciones del programa.
     */
    public void recoleccionPedidos(Restaurante restaurante, Cocina cocina) throws InterruptedException{ 
        synchronized (this){ 
            System.out.println("EL MESERO ESTA REGISTRANDO ORDENES");
            tomarPedido(restaurante.getMesas());

            System.out.println("ORDENES REGISTRADAS: " + pedidos);
            wait(1000);

            while(!pedidos.isEmpty() && cocina.getPedidosCocina().size() < cocina.getCapacidad()){
                System.out.println("ENTREGA DE ORDENES A COCINA");
                entregarPedido(cocina.getPedidosCocina(), cocina.getCapacidad());
            }
            notify(); 
            wait(1000); 
        } 
    } 
    
    /**
     * Metodo que ejecuta la "segunda mitad" de las actividades del mesero: Tomar los platillos de la cocina y entregarlos a quienes le correspondan.
     * @param Restaurante restaurante Donde se tienen las mesas que se recorren.
     * @param Cocina cocina Donde se tiene la lista de platillos listos.
     * @return Nada.
     * @throws InterruptedException En caso de que wait interrumpa las acciones del programa.
     */
    public void recoleccionPlatillos(Restaurante restaurante, Cocina cocina) throws InterruptedException{ 
        synchronized (this){ 
            System.out.println("EL MESERO ESTA TOMANDO LOS PLATILLOS DE LA COCINA");
            tomarPlatillo(cocina.getPlatillosListos(), restaurante.getTicket());

            System.out.println(platillos);
            wait(1000);

            while(!platillos.isEmpty()){
                System.out.println("EL MESERO VA A ENTREGAR LOS PLATILLOS A LOS COMENSALES");
                entregarPlatillo(restaurante.getMesas(), platillos);
            }

            notify(); 
            wait(1000); 
        } 
    } 
    
}
