package piezas;

import java.io.IOException;
import uwcse.graphics.GWindow;

/**
 * Clase Pieza para manejar las piezas de ajedrez.
 */
public class Pieza {

    private Imagenes imagen; // Objeto para manejar la imagen de la pieza.

    /**
     * Constructor de la clase Pieza.
     * 
     * @param x          Coordenada X.
     * @param y          Coordenada Y.
     * @param rutaImagen Ruta de la imagen de la pieza.
     * @throws IOException Si ocurre un error al cargar la imagen.
     */
    public Pieza(int x, int y, String rutaImagen) throws IOException {
        if (rutaImagen == null || rutaImagen.isEmpty()) {
            throw new IllegalArgumentException("La ruta de la imagen no puede ser nula o vacía.");
        }
        // Ajustar según el constructor de Imagenes
        this.imagen = new Imagenes(x, y, rutaImagen); // Orden de parámetros ajustado.
    }
}