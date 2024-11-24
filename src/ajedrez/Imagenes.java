package ajedrez;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import uwcse.graphics.GWindow;
import uwcse.graphics.ImageShape;

/**
 * Clase para manejar imágenes en el tablero.
 */
public class Imagenes {
    private ImageShape imagen; // La representación gráfica de la imagen.

    /**
     * Constructor para crear una imagen en las coordenadas dadas.
     *
     * @param x       Coordenada X.
     * @param y       Coordenada Y.
     * @param nombre  Nombre del archivo de la imagen (debe estar en la carpeta recursos).
     * @param width   Ancho deseado de la imagen.
     * @param height  Altura deseada de la imagen.
     * @throws IOException Si la imagen no se encuentra o no se puede cargar.
     */
    public Imagenes(int x, int y, String nombre, int width, int height) throws IOException {
        // Cargar la imagen desde los recursos
        var recurso = this.getClass().getClassLoader().getResource("resources/imagenes/" + nombre);
        if (recurso == null) {
            throw new IOException("No se encontró la imagen: " + nombre);
        }

        // Leer y redimensionar la imagen
        Image img = ImageIO.read(recurso);
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Crear el ImageShape en las coordenadas dadas
        this.imagen = new ImageShape(scaledImg, x, y);
    }

    /**
     * Agregar la imagen a la ventana.
     *
     * @param ventana Ventana gráfica donde se dibujará la imagen.
     */
    public void ponerImagen(GWindow ventana) {
        ventana.add(imagen);
    }

    /**
     * Mover la imagen en la ventana.
     *
     * @param dx Movimiento en X.
     * @param dy Movimiento en Y.
     */
    public void mover(int dx, int dy) {
        imagen.moveBy(dx, dy);
    }

    public void eliminarImagen(GWindow ventana) {
        ventana.remove(this.imagen);
    }
}
