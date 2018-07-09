import java.awt.*;
/**
 * Clase para definir disparos de las naves Ufo
 * 
 * @author Fco Carlos López Porcel 
 * @version 0.01
 */
public class DisparoUfo extends Objeto
{
    /**
     * Constructor para objetos de la clase DisparoUfo
     */
    public DisparoUfo()
    {
        // Llama al constructor de Objeto
        super(10,12,0,0);

        // Vuelve invisible el disparo
        setVisible(false); 
    }

    /**
     * Mueve el disparo hacia abajo
     */
    public void moverAbajo()
    {
        moverVertical(3);
    }

    /**
     * Genera el cuerpo de un disparo
     * @return Polygon con el cuerpo del disparo
     */
    public Polygon cuerpo() {
        // Número de puntos del poligono
        int n = 8;
        // Coordenadas X
        int[]x = new int[n];
        // Coordenadas Y
        int[]y = new int[n];

        // LLenamos las coordenadas X
        x[0]=getPosicionX() + getAncho() / 2;
        x[1]=getPosicionX() + getAncho();
        x[2]=getPosicionX() + getAncho() / 2;
        x[3]=getPosicionX() + getAncho();
        x[4]=getPosicionX() + getAncho() / 2;
        x[5]=getPosicionX();
        x[6]=getPosicionX() + getAncho() / 2;
        x[7]=getPosicionX();
        // LLenamos las coordenadas Y
        y[0]=getPosicionY();
        y[1]=getPosicionY();
        y[2]=getPosicionY() + (getAlto() / 2) -1;
        y[3]=getPosicionY() + (getAlto() / 2) -1;
        y[4]=getPosicionY() + getAlto();
        y[5]=getPosicionY() + getAlto();
        y[6]=getPosicionY() + (getAlto() / 2) +1;
        y[7]=getPosicionY() + (getAlto() / 2) +1;

        Polygon cuerpo = new Polygon(x, y, n);
        return cuerpo;
    }
}
