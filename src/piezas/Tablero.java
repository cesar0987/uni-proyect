    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import java.awt.Color;
import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;


public class Tablero {
    private final Rectangle fondo;
    private final Rectangle[][] celdas;

    public Tablero(GWindow ventana) {
        int anchoVentana = 850;
        int altoVentana = 850;
        int anchoCelda =800 / 8;
        int altoCelda = 800 / 8;

        // Dibuja el fondo del tablero
        fondo = new Rectangle(0, 0, anchoVentana, altoVentana, Color.BLACK, true);
        fondo.addTo(ventana);
        

        // Crear el tablero con celdas alternas
        celdas = new Rectangle[8][8];
        
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                // Determina el color alterno
                Color colorCelda = (fila + col) % 2 == 0 ? Color.WHITE : Color.BLACK;
                
                // Crear la celda en la posición correspondiente
                celdas[fila][col] = new Rectangle(
                    col * anchoCelda, // posición x
                    fila * altoCelda, // posición y
                    anchoCelda,       // ancho de la celda
                    altoCelda,        // alto de la celda
                    colorCelda,       // color
                    true              // relleno
                );
                celdas[fila][col].addTo(ventana);
            }
        }
    }
}
