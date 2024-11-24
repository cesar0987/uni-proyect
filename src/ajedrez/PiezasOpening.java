package ajedrez;

import java.awt.Color;
import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Triangle;

/**
 * La clase PiezasOpening se encarga de crear y mostrar las piezas de ajedrez 
 * (rey y torre) en el centro de la ventana.
 */
public class PiezasOpening {
    private Rectangle torreBase;
    private Rectangle torreCuerpo;
    private Rectangle torreAlmenaIzq;
    private Rectangle torreAlmenaCentro;
    private Rectangle torreAlmenaDer;
    private Rectangle reyCuerpo;
    private Oval reyCabeza;
    private Triangle reyCorona;
    private Rectangle piso; // Rectángulo para el piso

    // Coordenadas iniciales de las piezas
    private int torreX, torreY, reyX, reyY;

    public PiezasOpening(GWindow ventana) {
        int anchoVentana = ventana.getWindowWidth();
        int altoVentana = ventana.getWindowHeight();

        // Coordenadas iniciales para centrar las piezas
        torreX = anchoVentana / 2 - 150;
        torreY = altoVentana / 2 - 100;
        reyX = anchoVentana / 2 + 50;
        reyY = altoVentana / 2 - 100;

        // Crear el piso
        piso = new Rectangle(0, altoVentana - 200, anchoVentana, 200, Color.DARK_GRAY, true);

        // Crear la torre
        torreBase = new Rectangle(torreX, torreY + 120, 100, 40, Color.GRAY, true);
        torreCuerpo = new Rectangle(torreX + 20, torreY, 60, 120, Color.GRAY, true);
        torreAlmenaIzq = new Rectangle(torreX, torreY - 20, 20, 20, Color.GRAY, true);
        torreAlmenaCentro = new Rectangle(torreX + 40, torreY - 20, 20, 20, Color.GRAY, true);
        torreAlmenaDer = new Rectangle(torreX + 80, torreY - 20, 20, 20, Color.GRAY, true);

        // Crear el rey
        reyCuerpo = new Rectangle(reyX, reyY + 40, 60, 120, Color.YELLOW, true);
        reyCabeza = new Oval(reyX - 10, reyY, 80, 80, Color.YELLOW, true);

        // Crear la corona (un triángulo)
        int coronaBaseIzqX = reyX + 20;  // Punto izquierdo de la base del triángulo
        int coronaBaseIzqY = reyY - 10; // Altura base del triángulo
        int coronaBaseDerX = reyX + 60; // Punto derecho de la base
        int coronaBaseDerY = reyY - 10;
        int coronaPicoX = reyX + 40;    // Pico del triángulo (centro superior)
        int coronaPicoY = reyY - 40;

        reyCorona = new Triangle(coronaBaseIzqX, coronaBaseIzqY, 
                                 coronaBaseDerX, coronaBaseDerY, 
                                 coronaPicoX, coronaPicoY, Color.ORANGE, true);
    }

    /**
     * Muestra las piezas (rey y torre) en la ventana y las mueve hacia los lados.
     * 
     * @param ventana Ventana donde se dibujarán las piezas
     */
    public void mostrarPiezas(GWindow ventana) throws InterruptedException {
        if (ventana != null) {
            // Dibujar el piso primero
            piso.addTo(ventana);

            // Dibujar la torre
            torreBase.addTo(ventana);
            torreCuerpo.addTo(ventana);
            torreAlmenaIzq.addTo(ventana);
            torreAlmenaCentro.addTo(ventana);
            torreAlmenaDer.addTo(ventana);

            // Dibujar el rey
            reyCuerpo.addTo(ventana);
            reyCabeza.addTo(ventana);
            reyCorona.addTo(ventana);

            // Animación para mover las piezas hacia los lados
            int torreDestinoX = 50;  // Posición final de la torre (izquierda)
            int reyDestinoX = ventana.getWindowWidth() - 150;  // Posición final del rey (derecha)

            while (torreX > torreDestinoX || reyX < reyDestinoX) {
                if (torreX > torreDestinoX) {
                    moverTorre(-5);  // Mover la torre hacia la izquierda
                }
                if (reyX < reyDestinoX) {
                    moverRey(5);  // Mover el rey hacia la derecha
                }
                ventana.doRepaint();  // Actualizar la ventana
                Thread.sleep(50);  // Pausa para suavizar la animación
            }
        } else {
            System.out.println("Error: La ventana es nula.");
        }
    }

    /**
     * Mueve la torre una distancia específica en el eje X.
     * 
     * @param dx Desplazamiento en el eje X
     */
    private void moverTorre(int dx) {
        torreX += dx;
        torreBase.moveBy(dx, 0);
        torreCuerpo.moveBy(dx, 0);
        torreAlmenaIzq.moveBy(dx, 0);
        torreAlmenaCentro.moveBy(dx, 0);
        torreAlmenaDer.moveBy(dx, 0);
    }

    /**
     * Mueve el rey una distancia específica en el eje X.
     * 
     * @param dx Desplazamiento en el eje X
     */
    private void moverRey(int dx) {
        reyX += dx;
        reyCuerpo.moveBy(dx, 0);
        reyCabeza.moveBy(dx, 0);
        reyCorona.moveBy(dx, 0);
    }
}
