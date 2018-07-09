import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * La clase Juego
 *
 * @author Fco Carlos López Porcel 
 * @version 0.01
 */
public class Juego extends Canvas
{
    // Campos generales del ventana
    private JFrame ventana;
    private int anchoVentana = 700;        // ancho del ventana
    private int altoVentana  = 700;        // alto del ventana
    private JPanel panel;                  // panel principal del ventana

    // Nave guardián
    private Guardian guardian;

    // UFOs
    private static final int NUMEROUFOS = 35;   // cantidad de Ufos que tiene el juego
    private List<Ufo> ufos;                     // lista de todos los Ufos
    private Boolean cambiaUfos = false;         // detecta si hay que cambiar el movimiento

    // Disparos
    private DisparoGuardian disparoGuardian;    // disparo de la nave Guardian
    private DisparoUfo disparoUfo;         // disparo de las naves Ufo

    // Imagen en la que dibujamos los elementos del juego
    Image im;
    Graphics buffer;

    // Indican si se han pulsado los botones de movimiento y disparo
    private Boolean moverDerecha = false;
    private Boolean moverIzquierda = false;
    private Boolean disparar = false;

    // Lleva la cuenta de Ufos muertos
    private int ufosMuertos = 0;

    // El juego se ejecuta
    private Boolean juego = true;

    /**
     * Constructor para la clase Juego
     */
    public Juego()
    {
        // crea la ventana principal del juego
        generaVentana();
        
        // genera las naves y los disparos del juego
        generaNaves();
        
        // responde a los eventos de pulsar las teclas de movimiento y disparo
        addKeyListener(new Eventos());
    }

    /**
     * Ejecuta el juego
     */
    public void ejecuta()
    {
        // Game Loop
        while (juego) {

            // Mueve los objetos del juego (Naves y Disparos)
            mueveObjetos();

            // Comprueba las colisiones entre los elementos del juego
            compruebaColisiones();

            // Comprube si hemos matados a todos los Ufo
            compruebaUfosMuertos();

            // Actualiza los gráficos de los elementos del juego. Es una llamada a update().
            repaint();

            // Genera un pequeño retraso en la ejecución del siguiente ciclo
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                break;
            }
        }
    }
    
    /*
     * Pinta los gráficos de los elementos del juego
     */

    public void paint(Graphics g)
    {
        update(g);
    }
    
    /**
     * Pinta y actualiza los gráficos de los elementos del juego
     */
    public void update(Graphics g)
    {
        // Crea la imagen sobre la que dibujamos
        if (buffer == null) {
            im = createImage(anchoVentana, altoVentana);
            buffer = im.getGraphics();
        }

        // Establece el color de fondo de la imagen
        buffer.setColor(getBackground());
        buffer.fillRect(0,0,anchoVentana,altoVentana);

        // Dibuja la nave Guardian
        buffer.setColor(Color.green);
        buffer.fillPolygon(guardian.cuerpo());

        // Dibuja los disparos de la nave Guardian
        if (disparoGuardian.getVisible()) {
            buffer.fillPolygon(disparoGuardian.cuerpo());
        }

        // Dibuja las naves Ufo
        buffer.setColor(Color.white);
        for (Ufo ufo : ufos) {
            // Solo se dibuja si la nave está viva
            if (ufo.getVisible()) {
                buffer.fillPolygon(ufo.cuerpo());
            }
        }

        // Dibuja el disparo de los Ufo
        if (disparoUfo.getVisible()) {
            buffer.fillPolygon(disparoUfo.cuerpo());
        }

        // Dibuja todos los elementos
        g.drawImage(im,0,0,null);
        
        // Libera todos los recursos que pueda estar generando el dibujo de los elementos
        g.dispose();
    }

    /**
     * Crea la ventana principal del juego
     */
    private void generaVentana()
    {
        // Crea la ventana
        ventana = new JFrame("SpaceInvaders");
        panel = (JPanel) ventana.getContentPane();
        
        // Establece el tamaño del panel
        panel.setPreferredSize(new Dimension(anchoVentana, altoVentana));

        // Establece el layout por defecto
        panel.setLayout(null);

        // Establece el fondo para el panel
        panel.setBackground(Color.black);

        // Establece el tamaño de Canvas y lo añade a la ventana
        setBounds(0,0,altoVentana,anchoVentana);
        panel.add(this);

        // Establece que el programa se cerrará cuando la ventana se cierre
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establece el tamaño de la ventana
        ventana.pack();

        // La ventana no se cambia de tamaño
        ventana.setResizable(false);

        // Centra la ventana en la pantalla
        ventana.setLocationRelativeTo(null);

        // Muestra la ventana
        ventana.setVisible(true);

        // Mantine el foco en la ventana
        requestFocusInWindow();
    }

    /**
     * Genera las naves del juego tanto Guardián como Ufos, así como los disparos de estas
     */
    private void generaNaves()
    {
        // Iniciamos la nave guardián
        guardian = new Guardian(anchoVentana, altoVentana);

        // Iniciamos los UFOs
        ufos = new ArrayList<Ufo>(NUMEROUFOS);
        int inX = 0;
        int inY = 40;
        int gap = 30;
        int count = 0;
        for (int x = 0; x < NUMEROUFOS; x++) {
            Ufo ufo;
            ufos.add(ufo = new Ufo(0,inY, anchoVentana, this));
            ufo.setPosicionX(inX);
            inX += ufo.getAncho() + gap;
            if(count == 6) {
                inX = 0;
                inY += 40;
                count = 0;
            }
            else {
                count++;
            }
        }

        // Inicia el disparo de la nave Guardian, por defecto no visible ni en movimiento
        disparoGuardian = new DisparoGuardian();

        // Inicia el disparo de las naves Ufo, por defecto no visible ni en movimiento
        disparoUfo = new DisparoUfo();
    }

    /**
     * Mueve los objetos del juego (Naves y Disparos) por la pantalla
     */
    private void mueveObjetos()
    {
        // Mueve las naves Ufo
        for (Ufo ufo : ufos) {
            ufo.moverUfo();
        }
        
        // Cambia el movimiento de los Ufos
        if (cambiaUfos) {
            for (Ufo ufo : ufos) {
                ufo.cambiaMoverUfo();
            }
            cambiaUfos = false;
        }

        // Mueve los disparos y los elimina los disparos de la nave Guardian
        if (disparoGuardian.getVisible()) {
            disparoGuardian.moverArriba();
            if (disparoGuardian.getPosicionY() <= 0) {
                disparoGuardian.setVisible(false);
            }
        }

        // Dispara, mueve y elimina los disparos de las naves Ufo
        disparaUfo();
        if (disparoUfo.getVisible()) {
            disparoUfo.moverAbajo();
            if (disparoUfo.getPosicionY() >= altoVentana) {
                disparoUfo.setVisible(false);
            }
        }

        // Mueve la nave Guardian hacia la izquierda
        if (moverIzquierda) {
            guardian.moverIzquierda();
        }
        // Mueve la nave Guardian hacia la derecha
        if (moverDerecha) {
            guardian.moverDerecha();
        }
        // Hace que la nave Guardian dispare
        if (disparar) {
            disparaGuardian();
        }
    }
    
    /**
     * Establece si hay que cambiar el movimiento de las naves Ufo
     */
    public void cambiaUfos() {
        cambiaUfos = true;
    }

    /**
     * Comprueba las colisiones entre los elementos del juego
     */
    private void compruebaColisiones()
    {
        // Comprobamos las colisiones con los Ufo
        for (Ufo ufo : ufos) {
            // Las naves Ufo chocan con la nave Guardian
            if (ufo.colisionaCon(guardian) && ufo.getVisible()) {
                mensajeDialogo(0);
                juego = false;
            }
            // Las naves Ufo llegan abajo de la pantalla
            if ((ufo.getPosicionY() - ufo.getAlto() > altoVentana)) {
                mensajeDialogo(0);
                juego = false;
            }
            // El disparo de la nave Guardian mata a una nave Ufo
            if (ufo.colisionaCon(disparoGuardian) && ufo.getVisible()) {
                ufo.setVisible(false);
                disparoGuardian.setVisible(false);
                disparoGuardian.setPosicion(0, 0);
                ufosMuertos++;
            }
        }

        // El disparo de las naves Ufo mata a la nave Guardian
        if (guardian.colisionaCon(disparoUfo)) {
            disparoUfo.setVisible(false);
            mensajeDialogo(0);
            juego = false;
        }

        // Si el disparo Guardian colisiona con el disparo de los Ufo, se
        // eliminan ambos
        if (disparoGuardian.colisionaCon(disparoUfo)) {
            disparoGuardian.setVisible(false);
            disparoGuardian.setPosicion(0, 0);
            disparoUfo.setVisible(false);
        }
    }

    /**
     * Comprube si el número de Ufos muertos ha llegado al máximo, en ese caso
     * ganamos la partida
     */
    private void compruebaUfosMuertos()
    {
        // Ganamos la partida si todas las naves Ufo han sido matadas
        if (ufosMuertos == NUMEROUFOS) {
            mensajeDialogo(1);
            juego = false;
        }
    }

    /**
     * Crea un disparo de la nave guardian;
     */
    private void disparaGuardian()
    {
        // Solo dispara si no hay disparo activo
        if (!disparoGuardian.getVisible()) {
            disparoGuardian.setPosicion(guardian.getPosicionX() + ((guardian.getAncho() - disparoGuardian.getAncho()) / 2), guardian.getPosicionY() - guardian.getAlto());
            disparoGuardian.setVisible(true);
        }
    }

    /**
     * Crea un disparo de los Ufo
     */
    private void disparaUfo()
    {
        // Solo dispara si no hay disparo activo
        if (!disparoUfo.getVisible()) {
            // Generamos un número aleatorio para obtener el Ufo que va a disparar
            Random aleatorio = new Random();
            int ufoAleatorio;
            ufoAleatorio = aleatorio.nextInt(NUMEROUFOS);

            // Si el Ufo aleatorio NO está muerto dispara
            if (ufos.get(ufoAleatorio).getVisible()) {
                disparoUfo.setPosicion(ufos.get(ufoAleatorio).getPosicionX() + ((ufos.get(ufoAleatorio).getAncho() - disparoUfo.getAncho()) / 2), ufos.get(ufoAleatorio).getPosicionY() + ufos.get(ufoAleatorio).getAlto());
                disparoUfo.setVisible(true);
            }
        }
    }

    /**
     * Genera los mensajes de diálogo
     */
    private void mensajeDialogo(int tipo)
    {
        // Elige el tipo de mensaje a mostrar
        switch(tipo) {
            case 0:
                JOptionPane.showMessageDialog(ventana, "No has conseguido evitar la invasión alienígena.\nLA HUMANIDAD SERA DESTRUIDA", "GAME OVER", JOptionPane.ERROR_MESSAGE);
                break;
            case 1:
                JOptionPane.showMessageDialog(ventana, "Has evitado la invasión alienígena.\n LA HUMANIDAD ESTA SALVADA", "¡VICTORIA!", JOptionPane.PLAIN_MESSAGE);
                break;
            default:
                break;
        }
        
        // Cierra el juego una vez leido el mensaje
        System.exit(0);
    }

    /**
     * Gestiona la respuesta al pulsar las teclas de movimiento y disparo
     */
    private class Eventos extends KeyAdapter
    {
        /**
         * Capturamos cuando las teclas de movimiento y disparo son pulsadas
         */
        public void keyPressed(KeyEvent e)
        {
            // tecla O que mueve la nave Guardian hacia la izquierda
            if (e.getKeyCode() == KeyEvent.VK_O) {
                moverIzquierda = true;
            }
            // tecla P que mueve la nave Guardian hacia la derecha
            if (e.getKeyCode() == KeyEvent.VK_P) {
                moverDerecha = true;
            }
            // tecla Espacio dispara
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                disparar = true;
            }
        }

        /**
         * Capturamos cuando las teclas de movimiento y disparo dejan de
         * pulsarse
         */
        public void keyReleased(KeyEvent e)
        {
            // tecla O
            if (e.getKeyCode() == KeyEvent.VK_O) {
                moverIzquierda = false;
            }
            // tecla P
            if (e.getKeyCode() == KeyEvent.VK_P) {
                moverDerecha = false;
            }
            // tecla Espacio
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                disparar = false;
            }
        }
    }
}
