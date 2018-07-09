import java.awt.*;
/**
 * Clase para definir disparos de la nave Guardian
 * 
 * @author Fco Carlos López Porcel 
 * @version 0.01
 */
public class DisparoGuardian extends Objeto
{
    /**
     * Constructor para objetos de la clase DisparoGuardian
     */
    public DisparoGuardian()
    {
        // Llama al constructor de Objeto
        super(10,12,0,0);

        // Vuelve invisible el disparo
        setVisible(false);
    }

    /**
     * Mueve el disparo hacia arriba
     */
    public void moverArriba()
    {
        moverVertical(-10);
    }

    /**
     * Genera el cuerpo de un disparo
     * @return Polygon con el cuerpo del disparo
     */
    public Polygon cuerpo() {
        // Número de puntos del poligono
        int n = 4;
        // Coordenadas X
        int[]x = new int[n];
        // Coordenadas Y
        int[]y = new int[n];

        // LLenamos las coordenadas X
        x[0]=getPosicionX() + getAncho() / 4;
        x[1]=getPosicionX() + (getAncho() * 3) / 4;
        x[2]=getPosicionX() + (getAncho() * 3) / 4;
        x[3]=getPosicionX() + getAncho() / 4;
        // LLenamos las coordenadas Y
        y[0]=getPosicionY();
        y[1]=getPosicionY();
        y[2]=getPosicionY() + getAncho();
        y[3]=getPosicionY() + getAncho();

        Polygon cuerpo = new Polygon(x, y, n);
        return cuerpo;
    }
}
