import java.awt.*;
/**
 * Clase para definir objetos que se dibujan y se mueven en la pantalla.
 * 
 * @author Fco Carlos López Porcel
 * @version 0.01
 */
public abstract class Objeto
{
    // Ancho del objeto
    private int ancho;

    // Alto del objeto
    private int alto;

    // Posición X del objeto
    private int posicionX;

    // Posición Y del objeto
    private int posicionY;

    // Visibilidad del objeto
    private boolean esVisible;

    /**
     * Constructor para objetos de la clase Objeto
     * @param ancho Ancho del objeto
     * @param alto  Alto del objeto
     * @param posicionX Posicion X del objeto
     * @param posicionY Posicion Y del objeto
     */
    public Objeto(int ancho, int alto, int posicionX, int posicionY)
    {
        this.ancho = ancho;
        this.alto = alto;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        esVisible = true;
    }

    /**
     * Mueve el objeto en horizontal
     * @param distancia Distancia que se mueve el objeto
     */
    protected void moverHorizontal(int distancia)
    {
        posicionX += distancia;
    }

    /**
     * Mueve el objeto en vertical
     * @param distancia Distancia que se mueve el objeto
     */
    protected void moverVertical(int distancia)
    {
        posicionY += distancia;
    }

    /**
     * Establece la coordenada X
     * @param posicionX coordenada X de la posición del objeto
     */
    public void setPosicionX(int posicionX)
    {
        this.posicionX = posicionX;
    }

    /**
     * Establece la coordenada Y
     * @param posicionY coordenada Y de la posición del objeto
     */
    public void setPosicionY(int posicionY)
    {
        this.posicionY = posicionY;
    }

    /**
     * Establece las coordenadas
     * @param posicionX coordenada X de la posición del objeto
     * @param posicionY coordenada Y de la posición del objeto
     */
    public void setPosicion(int posicionX, int posicionY)
    {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    /**
     * Devuelve la coordenada X
     * @return Coordenada X de la posición del objeto
     */
    public int getPosicionX()
    {
        return posicionX;
    }

    /**
     * Devuelve la coordenada Y
     * @return Coordenada Y de la posición del objeto
     */
    public int getPosicionY()
    {
        return posicionY;
    }

    /**
     * Devuelve el ancho
     * @return Ancho del objeto
     */
    public int getAncho()
    {
        return ancho;
    }

    /**
     * Devuelve el alto
     * @return Alto del objeto
     */
    public int getAlto()
    {
        return alto;
    }

    /**
     * Establece la visibilidad del objeto
     */
    public void setVisible(Boolean esVisible)
    {
        this.esVisible = esVisible;
    }

    /**
     * Devuelve la visibilidad del objeto
     * @return Devuelve true si el objeto esta visible
     */
    public Boolean getVisible()
    {
        return esVisible;
    }

    /**
     * Comprueba si el objeto pasado como variable colisiona con nuestro objeto
     * @return Devuelve true si los objetos colisionan
     */
    public Boolean colisionaCon(Objeto col)
    {
        return this.cuerpo().intersects((double)col.getPosicionX(), (double)col.getPosicionY(), (double)col.getAncho(), (double)col.getAlto());
    }

    /**
     * Dibuja el cuerpo del objeto
     * A implementar por las clases hijas
     */
    public abstract Polygon cuerpo();
}
