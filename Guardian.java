import java.awt.*;
/**
 * Clase para definir naves Guardian.
 *
 * @author Fco Carlos López Porcel
 * @version 0.01
 */
public class Guardian extends Objeto
{
    // Ancho de la ventana
    private int anchoVentana;

    /**
     * Constructor para objetos de la clase Guardian
     */
    public Guardian(int anchoVentana, int altoVentana)
    {
        // Llama el constructor de la clase Objeto
        super(50, 25, anchoVentana / 2, altoVentana - 25);

        // Establece el ancho de la Ventana
        this.anchoVentana = anchoVentana;
    }

    /**
     * Mueve a la derecha
     */
    public void moverDerecha()
    {
        // Movemos si la nave está dentro de la ventana
        if (getPosicionX() < anchoVentana - getAncho()) {
            moverHorizontal(4);
        }
    }

    /**
     * Mueve a la izquierda
     */
    public void moverIzquierda()
    {
        // Movemos si la nave está dentro de la ventana
        if (getPosicionX() > 0) {
            moverHorizontal(-4);
        }
    }

    /**
     * Genera el poligono que representa la nave Guardian
     * @return Polygon con la forma del cuerpo de la nave Guardian
     */
    public Polygon cuerpo()
    {
        // Número de puntos del poligono
        int n = 12;
        // Coordenadas X
        int[]x = new int[n];
        // Coordenadas Y
        int[]y = new int[n];

        // LLenamos las coordenadas X
        x[0] = getPosicionX();
        x[1] = getPosicionX() + 2;
        x[2] = getPosicionX() + 20;
        x[3] = getPosicionX() + 20;
        x[4] = getPosicionX() + 22;
        x[5] = getPosicionX() + 28;
        x[6] = getPosicionX() + 30;
        x[7] = getPosicionX() + 30;
        x[8] = getPosicionX() + 48;
        x[9] = getPosicionX() + 50;
        x[10] = getPosicionX() + 50;
        x[11] = getPosicionX();

        // LLenamos las coordenadas Y
        y[0] = getPosicionY() + 2;
        y[1] = getPosicionY();
        y[2] = getPosicionY();
        y[3] = getPosicionY() - 8;
        y[4] = getPosicionY() - 10;
        y[5] = getPosicionY() - 10;
        y[6] = getPosicionY() - 8;
        y[7] = getPosicionY();
        y[8] = getPosicionY();
        y[9] = getPosicionY() + 2;
        y[10] = getPosicionY() + 15;
        y[11] = getPosicionY() + 15;

        // Generamos el poligono
        Polygon cuerpo = new Polygon(x, y, n);

        return cuerpo;
    }
}
