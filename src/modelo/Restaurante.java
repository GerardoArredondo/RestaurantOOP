package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * <h1>Clase Restaurante</h1>
 * <p>Esta clase permite navegar por las mesas y guardar las ordenes en un ticket. </p>
 * 
 * @author Equipo 2
 */
public class Restaurante {
    /** HashTable que guarda las mesas del restaurante, permitiendo navegarlas.*/
    private Hashtable<Integer, Mesa> mesas = new Hashtable<>();
    /** Lista ligada de pedidos, la cual sirve como el ticket de todas las ordenes. */
    private LinkedList<Pedido> ticket = new LinkedList<>();
    
    /**
     * Inicializa un restaurante con n mesas, las cuales se inicializan mediante un for.
     * @param int numMesas Numero de mesas.
     */
    public Restaurante(int numMesas){
        for(int i = 0; i < numMesas; i++)
            mesas.put(i, new Mesa(i));
    }
    
    
    //***GETTERS Y SETTERS***
    
    public  Hashtable<Integer, Mesa> getMesas(){
        return mesas;
    }
    
    public LinkedList<Pedido> getTicket(){
        return ticket;
    }
    public void setPedidos(LinkedList<Pedido> ticket){
        this.ticket = ticket;
    }
    
    
    /**
     * Metodo que retorna una mesa buscada con su numero identificador.
     * @param int n Identificador de la mesa.
     * @return Mesa buscada.
     */
    public Mesa buscarMesa(int n){
        if(mesas.containsKey(n))
            return mesas.get(n);
        else
            return null;
    }
 
    /**
     * Metodo que retorna el total a pagar de las ordenes generadas.
     * @param Ninguno
     * @return float total a pagar.
     */
    public float calcularTotal(){
        float total = 0;
        for(Pedido orden : ticket){
            total+=orden.getPrecio();
        }
        return total;
    }
    
    /**
     * Metodo que obtiene el ticket y lo imprime en un .txt, incluyendo el total a pagar.
     * @param Ninguno
     * @return Nada
     */
    public void imprimirTicket(){
        String ruta = "Ticket.txt";
        float total = this.calcularTotal();
        try{
            PrintWriter imprimir = new PrintWriter(new FileWriter(ruta, false));
            imprimir.print("********* TICKET ********* ");
            for(Pedido pedido : ticket){
                imprimir.println(pedido.toString());
            }
            imprimir.print("Total a pagar: $" + total);
            imprimir.close();
        }catch(IOException e){
            System.out.println("Falla en el sistema. No se ha podido imprimir el Ticket");
        }
    }

}
