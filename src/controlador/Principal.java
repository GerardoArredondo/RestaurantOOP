package controlador;

import java.util.Scanner;
import modelo.FachadaRestaurante;

/**
 * <h1>Clase Principal</h1>
 * <p>Clase estatica que ejecuta al programa.</p>
 * 
 * @author Equipo 2
 */
public class Principal {
    /**
     * Metodo principal que ejecuta el programa.
     * @param String[] args No utilizado
     */
    public static void main(String[] args) {  
        System.out.println("BIENVENIDO AL PROGRAMA.");
        Scanner scan = new Scanner(System.in);    
        FachadaRestaurante restauranteFuncionando = new FachadaRestaurante();
        Menu.menuPrincipal(scan, restauranteFuncionando);
        System.out.println("Gracias por su visita. Vuelva pronto!");
    }
    
}
