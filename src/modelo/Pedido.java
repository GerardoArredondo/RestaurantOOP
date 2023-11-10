package modelo;

/**
 * <h1>Clase Pedido</h1>
 * <p>Esta clase hereda de Comida, manteniendo sus caracteristicas pero con un string que permite identificar de quien es la orden. Esta clase representa tanto las ordenes en ticket como los platillos cocinados.:</p>
 * 
 * @author Equipo 2
 */
public class Pedido extends Comida{
    /** String que permite identificar a quien le corresponde la orden. Sigue el formato numMesa-numComensal */
    private String clienteCorresp; 
    
    /**
     * Se crea un pedido vacío.
     */
    public Pedido(){
    }
    
    /**
     * Crea un pedido con el codigo (que identifica que platillo es), el numero de mesa y el numero de cliente para crear el String clienteCorresp.
     * @param int codigo Guarda el ID que identifica el platillo.
     * @param int mesa Numero de mesa.
     * @param int cliente Numero de cliente, obtenido con su posicion en la lista ligada de la mesa.
     */
    public Pedido(int codigo, int mesa, int cliente){
        super(codigo);
        this.clienteCorresp = mesa + "-" + cliente;
    }
    
    //***GETTERS Y SETTERS***
    
    public void setClienteCorresp(String clienteCorresp){
        this.clienteCorresp = clienteCorresp;
    }
    public String getClienteCorresp(){
        return clienteCorresp;
    }
    
    /**
     * Metodo que sobreescribe el metodo default toString, permitiendo imprimir el pedido.
     * @param Ninguno.
     * @return String que imprime el codigo del pedido, y el cliente que le corresponde.
     */
    @Override
    public String toString(){
        if((getPrecio() == 0)){
           if(!(getNombre() == null))
                return "\nPedido: " + getNombre() + "\nCliente: " + getClienteCorresp();
            else
                return "\nPedido: " + getCodigo() + "\nCliente: " + getClienteCorresp(); 
        }else
            return "\n" + getNombre() + " ..... $" + getPrecio() +"\nCliente: " + getClienteCorresp();
        
    }
    
}
