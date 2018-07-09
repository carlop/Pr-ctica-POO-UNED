import java.awt.*;
/**
 * Clase para definir naves Ufo.
 * 
 * @author Fco Carlos López Porcel 
 * @version 0.01
 */
public class Ufo extends Objeto
{
    // Ancho de la ventana
    private int anchoVentana;
    
    // Juego en el que modificamos el movimiento
    private Juego juego;

    // Cuenta la distancia vertical cuando se mueve en vertical el Ufo
    private static int distanciaV = 0;

    // Detecta para donde debe moverse el Ufo
    private Boolean moverDerecha = true;    // Activa el movimiento a la derecha
    private Boolean moverAbajo = false;     // Activa el movimiento hacia abajo
    private Boolean moverIzquierda = false; // Activa el movimiento a la izquierda

    /**
     * Constructor para objetos de la clase Ufo
     */
    public Ufo(int posicionX, int posicionY, int anchoVentana, Juego juego)
    {
        // Llama al constructor de Objeto
        super(50, 25, posicionX, posicionY);

        // Establece el ancho de la ventana
        this.anchoVentana = anchoVentana;
        
        // Establecemos el juego
        this.juego = juego;
    }

    /**
     * Mueve la nave hacia abajo
     */
    private void moverAbajo()
    {
        moverVertical(1);
    }

    /**
     * Mueve la nave a la derecha
     */
    private void moverDerecha()
    {
        moverHorizontal(1);
    }

    /**
     * Mueve la nave a la izquierda
     */
    private void moverIzquierda()
    {
        moverHorizontal(-1);
    }

    /**
     * Genera el movimiento de las naves Ufo
     */
    public void moverUfo()
    {  
        // Mueve hacia abajo
        if (moverAbajo == true) {
            moverAbajo();
            distanciaV++;
            // Cuando ha bajado 10 pixeles cambiamos dirección
            if (distanciaV == 1000) {
                distanciaV = 0;
                juego.cambiaUfos();
            }
        }
        // Mueve a la derecha
        else if (moverDerecha == true) {
            moverDerecha();
            // Cuando llega al borde de la ventana cambiamos dirección
            if (getPosicionX() == anchoVentana - getAncho() && getVisible()) {
                juego.cambiaUfos();
            }
        }      
        // Mueve a la izquierda
        else if (moverIzquierda == true) {
            moverIzquierda();
            // Cuando llega a la posición inicial cambia de dirección
            if (getPosicionX() == 0 && getVisible()) {
                juego.cambiaUfos();
            }
        }
    }
    
    /**
     * Cambia la dirección de movimiento de las naves Ufo
     */
    public void cambiaMoverUfo()
    {
        if (moverAbajo == true) {
            moverAbajo = false;
        }
        else if (moverDerecha == true) {
            moverDerecha = false;
            moverAbajo = true;
            moverIzquierda = true;
        }
        else if (moverIzquierda == true) {
            moverIzquierda = false;
            moverAbajo = true;
            moverDerecha = true;
        }
    }

    /**
     * Genera el poligono que representa la nave Ufo
     * @return Polygon con la forma del cuerpo del Ufo
     */
    public Polygon cuerpo()
    {
        // Número de puntos del poligono
        int n = 14;
        // Coordenadas X
        int[]x = new int[n];
        // Coordenadas Y
        int[]y = new int[n];

        // LLenamos las coordenadas X
        x[0] = getPosicionX();
        x[1] = getPosicionX() + (getAncho() / 4);
        x[2] = getPosicionX() + (getAncho() / 2);
        x[3] = getPosicionX() + ((getAncho() * 3) / 4);
        x[4] = getPosicionX() + getAncho();
        x[5] = getPosicionX() + getAncho();
        x[6] = getPosicionX() + ((getAncho() * 2) / 3);
        x[7] = getPosicionX() + getAncho();
        x[8] = getPosicionX() + ((getAncho() * 2) / 3);
        x[9] = getPosicionX() + (getAncho() / 2);
        x[10] = getPosicionX() + (getAncho() / 3);
        x[11] = getPosicionX();
        x[12] = getPosicionX() + (getAncho() / 3);
        x[13] = getPosicionX();

        // LLenamos las coordenadas Y
        y[0] = getPosicionY() + 10;
        y[1] = getPosicionY() + 3;
        y[2] = getPosicionY();
        y[3] = getPosicionY() + 3;
        y[4] = getPosicionY() + 10;
        y[5] = getPosicionY() + 20;
        y[6] = getPosicionY() + 20;
        y[7] = getPosicionY() + 35;
        y[8] = getPosicionY() + 35;
        y[9] = getPosicionY() + 20;
        y[10] = getPosicionY() + 35;
        y[11] = getPosicionY() + 35;
        y[12] = getPosicionY() + 20;
        y[13] = getPosicionY() + 20;

        // Generamos el poligono
        Polygon cuerpo = new Polygon(x, y, n);

        return cuerpo;
    }
}
