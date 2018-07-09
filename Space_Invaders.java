/**
 * SPACE INVADERS
 * 
 * Clase principal del juego
 * 
 * @author Fco Carlos López Porcel 
 * @version 0.01
 */
public class Space_Invaders
{

    /**
     * Constructor de la clase SpaceInvaders
     */
    public Space_Invaders()
    {
        // Constructor vacio
    }
    
    /**
     * Método main
     * Crea una instancia de Juego y llama al método inicio
     */
    public static void main (String[] args)
    {
        Juego juego = new Juego();
        juego.ejecuta();
    }
}
