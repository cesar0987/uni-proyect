package javaapplication1;

import ajedrez.*;
import java.io.IOException;
import uwcse.graphics.GWindow;

public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // Crear la ventana
        GWindow ventana = new GWindow("Partida de Ajedrez", 850, 850);
        
        //Musica de Fondo
       

        // Crear la instancia de Opening para dibujar las figuras
        Opening opening = new Opening(ventana);
        opening.mostrarIntroduccion(ventana);

        // Crear una instancia de la clase Partida
    Partida partida = new Partida(ventana);

        // Declarar los movimientos en notación de ajedrez
        String[] movimientos =  {"e2e4", "b7b6", "d2d4", "c8b7", "f1d3", "f7f5", "e4f5", "b7g2", "d1h5", "g7g6", "f5g6", "g8f6", "g6h7", "f6h5", "d3g6", "++"};

        // Realizar los movimientos
        partida.recorrerMovimientos(movimientos, ventana);  
    }
}
