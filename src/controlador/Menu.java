package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.Comensal;
import modelo.FachadaRestaurante;
import modelo.Mesa;
import modelo.Pedido;
import vista.ImpresionesMenu;


/**
 * <h1>Clase ImpresionesMenu</h1>
 * <p>Clase abstracta encargada de recibir las indicaciones del usuario y controlar el programa.</p>
 * 
 * @author Equipo 2
 */
public abstract class Menu {

    /**
     * Método que le permite al usuario asignar n comensales en cualquier mesa existente. Si la mesa ya esta ocupada o no existe, no le permite asignar comensales.
     * @param Scanner scan Scanner que permite que el usuario ingrese el numero de comensales y el numero de mesa.
     * @param FachadaRestaurante fachada Fachada que se utilizará en el programa.
     * @return Booleano que indica si se repetirá la acción o no.
     * @throws IndexOutOfBoundsException En caso de que se escoja una mesa no existente.
     */
    public static boolean asignarComensales(Scanner scan, FachadaRestaurante fachada) throws IndexOutOfBoundsException{
        System.out.println("Cuantos comensales visitaron?");
        int n = scan.nextInt();
        System.out.println("Que mesa desea asignarles?");
        int lugar = scan.nextInt();
        Mesa mesa = fachada.getRestaurante().buscarMesa(lugar);
        if(!mesa.getOcupada())
            mesa.setComensales(n);
        else
            System.out.println("La mesa ya esta ocupada.\n");
        
        System.out.println("Desea asignar mas comensales? \n1 - Si 2 - No");
        int terminar = scan.nextInt();
        if(terminar == 1)
            return false;
        else if(terminar == 2)
            return true;
        return false;
    }

    /**
     * Método que le permite al usuario asignarle una orden a cualquier comensal existente. Como tal, se asigna el platillo al primer comensal sin orden de la mesa seleccionada. Si la mesa no existe, si está vacía, o si ya todos tienen orden, no se permite asignarle ordenes a esa mesa.
     * @param Scanner scan Scanner que permite que el usuario ingrese el numero de comensales y el numero de mesa.
     * @param FachadaRestaurante fachada Fachada que se utilizará en el programa.
     * @return Booleano que indica si se repetirá la acción o no.
     * @throws IndexOutOfBoundsException En caso de que se escoja una mesa no existente.
     */
    public static boolean asignarOrdenes(Scanner scan, FachadaRestaurante fachada) throws IndexOutOfBoundsException{
        System.out.println("A que mesa desea asignar una orden?");
        int lugar = scan.nextInt();
        Mesa mesa = fachada.getRestaurante().buscarMesa(lugar);
        boolean asignado = false;
        int numCliente = 0;
        if(mesa.getOcupada()){
            for(Comensal cliente : mesa.getComensales()){ 
                if(!cliente.getPedido()){
                    asignado = true;
                    break;
                }else
                    numCliente++;
            }if(asignado){
                System.out.println("Seleccione el platillo deseado.");
                ImpresionesMenu.impresionPlatillos();
                int opc = scan.nextInt();
                int codigo = seleccionarPlatillo(opc);
                mesa.getComensales().get(numCliente).setOrden(new Pedido(codigo, lugar, numCliente));
            }
            else
                System.out.println("Todos en la mesa ya ordenaron.");
        }else
            System.out.println("La mesa esta vacia.");
        System.out.println("Desea asignar mas ordenes? \n1 - Si 2 - No");
        int terminar = scan.nextInt();
        if(terminar == 1)
            return false;
        else if(terminar == 2)
            return true;
        
        return true;
    }
    
    /**
     * Método que, dependiendo de la opcion seleccionada en ImpresionesMenu.impresionPlatillos, retorna el codigo identificador correspondiente del platillo.
     * @param int opc Opción seleccionada. Solo puede tener valores el 1 al 10.
     * @return int Codigo identificador del platillo elegido.
     */
    public static int seleccionarPlatillo(int opc){
        switch(opc){
            case 1:
                return 0120;
            case 2:
                return 0110;
            case 3:
                return 0121;
            case 4:
                return 1122;
            case 5:
                return 0111;
            case 6:
                return 0140;
            case 7:
                return 0130;
            case 8:
                return 0131;
            case 9:
                return 0210;
            case 10:
                return 0211;
            default:
                System.out.println("Opcion incorrecta.");
                return 0;
        }
    }
    
    /**
     * Método que se encarga de permitirle al usuario escoger entre sus opciones: asignar comensales, asignar platillos, mostrar el estado de las mesas, iniciar los hilos, y salir del programa.
     * @param Scanner scan Scanner que permite que el usuario ingrese el numero de comensales y el numero de mesa.
     * @param FachadaRestaurante fachada Fachada que se utilizará en el programa.
     * @return Nada
     */
    public static void menuPrincipal(Scanner scan, FachadaRestaurante fachada){
        try{
           boolean repetirPrin = false, repetirUno, repetirDos, ejecutandose = false;
           do{
               ImpresionesMenu.impresionPrincipal();
               int opc = scan.nextInt();
                switch(opc){
                    case 1:
                        do{
                            repetirUno = asignarComensales(scan, fachada);
                        }while(!repetirUno);
                        break;
                    case 2:
                        do{
                            repetirDos = asignarOrdenes(scan, fachada);
                        }while(!repetirDos);
                        break;
                    case 3:
                        System.out.println(fachada.getRestaurante().getMesas());
                        
                        break;
                    case 4:
                        if(!ejecutandose){
                            fachada.running();
                            ejecutandose = true;
                        }else
                            System.out.println("!! No puede volver a iniciar la ejecucion. !!");
                        
                        break;
                    case 5:
                        System.out.println("El programa se esta apagando...");
                        fachada.terminarHilos();
                        fachada.getRestaurante().imprimirTicket();
                        repetirPrin = true;
                        break;
                    default:
                        System.out.println("Opcion incorrecta. Intente de nuevo");
                        break;
               }
            }while(!repetirPrin);
        }catch(InputMismatchException e){
            System.out.println("Error de usuario: ingreso un tipo de dato invalido.");
        }catch(NullPointerException e){
            System.out.println("Error de usuario: la mesa no existe.");
        }
        
    }
    
}
