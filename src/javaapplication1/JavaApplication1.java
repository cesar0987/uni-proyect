/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author fernando
 */
import ajedrez.*;
import java.awt.Color;
import java.io.IOException;
import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;

public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       //Se crea la ventana y se le pasa las dimensiones por parametro
        GWindow ventana = new GWindow(1024, 1000);
        

        //Partida de Magnus Carlsen vs Kasparov
        Partida partida = new Partida(ventana);
    }
}
