/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

/**
 *
 * @author fernando
 */
import java.awt.Color;
import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Triangle;

/**
 * Clase para dibujar un caballo de ajedrez.
 */
public class CaballoAjedrez {
    private Rectangle cabeza;
    private Rectangle hocico;
    private Triangle oreja1;
    private Triangle oreja2;
    private Oval ojo;
    private Rectangle cuerpo;

    /**
     * Constructor del caballo.
     *
     * @param ventana La ventana en la que se dibuja el caballo.
     */
    public CaballoAjedrez(GWindow ventana) {
        int anchoVentana = ventana.getWindowWidth();
        int altoVentana = ventana.getWindowHeight();

        // Crear cabeza del caballo
        cabeza = new Rectangle();

       cabeza.addTo(ventana);
    }
}

