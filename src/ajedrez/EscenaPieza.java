package ajedrez;

import java.io.IOException;
import uwcse.graphics.GWindow;

/**
 * Clase general para mostrar una pieza de ajedrez en el tablero.
 */
public class EscenaPieza {
    private Imagenes pieza; // La imagen de la pieza

    /**
     * Constructor para inicializar la pieza de ajedrez.
     *
     * @param x       Coordenada X inicial de la pieza.
     * @param y       Coordenada Y inicial de la pieza.
     * @param imagen  Nombre del archivo de la imagen de la pieza.
     * @param width   Ancho deseado de la pieza.
     * @param height  Altura deseada de la pieza.
     * @throws IOException Si ocurre un error al cargar la imagen.
     */
    public EscenaPieza(int x, int y, String imagen, int width, int height) throws IOException {
        // Crear la imagen de la pieza con tamaño ajustado
        pieza = new Imagenes(x, y, imagen, width, height);
    }

    /**
     * Mostrar la imagen de la pieza en la ventana.
     *
     * @param ventana Ventana gráfica donde se mostrará la pieza.
     */
    public void mostrarPieza(GWindow ventana) {
        pieza.ponerImagen(ventana);
    }

    /**
     * Mover la pieza en el tablero.
     *
     * @param dx Movimiento en el eje X.
     * @param dy Movimiento en el eje Y.
     */
    public void moverPieza(int dx, int dy) {
        pieza.mover(dx, dy);
    }
}
