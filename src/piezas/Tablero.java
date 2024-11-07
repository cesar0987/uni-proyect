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


public class Tablero {
    private Rectangle fondo;
    private Rectangle celdas;
    
    


public  Tablero(GWindow ventana){
    int anchoVentana = ventana.getWindowWidth();
    int altoVentana = ventana.getWindowHeight();
    
   
    
   fondo = new Rectangle(0, 0, anchoVentana, altoVentana, Color.BLACK, true);
    
    fondo.addTo(ventana);
}

}