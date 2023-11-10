package vista;

/**
 * <h1>Clase ImpresionesMenu</h1>
 * <p>Clase abstracta encargada de impresiones del menu de usuario.</p>
 * 
 * @author Equipo 2
 */
public abstract class ImpresionesMenu {
    
    /**
     * Metodo que imprime los 10 platillos disponibles para la eleccion del usuario, con su precio.
     * @param Ninguno
     * @return Nada
     */
    public static void impresionPlatillos(){
        System.out.println("1 - Milanesa Tradicional $139.99");
        System.out.println("2 - Enchiladas suizas $139.90");
        System.out.println("3 - Bistec empanizado $149.99");
        System.out.println("4 - Pechuga plancha a las finas hierbas $165.50");
        System.out.println("5 - Enchiladas de mole $135");
        System.out.println("6 - Tostadas de pollo $127.70");
        System.out.println("7 - Molletes tradicionales $105");
        System.out.println("8 - Hamburguesa $169");
        System.out.println("9 - Dona de Azucar $26");
        System.out.println("10 - Crepas de cajeta $104.50");
    }
    
    /**
     * Metodo que imprime las 5 acciones disponibles en el menu principal: asignar comensales, asignar platillos, mostrar el estado de las mesas, iniciar los hilos, y salir del programa.
     * @param Ninguno
     * @return Nada
     */
    public static void impresionPrincipal(){
        System.out.println("NOTA: PUEDE SELECCIONAR LA OPCION EN CUALQUIER MOMENTO");
        System.out.println("Escoja una opcion:");
        System.out.println("1 - Asignar comensales a una mesa");
        System.out.println("2 - Asignar platillos a una mesa");
        System.out.println("3 - Mostrar mesas del restaurante");
        System.out.println("4 - Iniciar");
        System.out.println("5 - Salir");
        
    }
    
}
