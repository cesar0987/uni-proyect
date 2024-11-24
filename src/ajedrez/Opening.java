package ajedrez;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import uwcse.graphics.GWindow;
import uwcse.graphics.TextShape;

/**
 * La clase Opening muestra una introducción con un rey y una torre
 * en el centro de la pantalla antes de iniciar el juego.
 */
public class Opening {
    // Se instancian las variables para los gráficos
    private PiezasOpening piezas;

    private TextShape textoBienvenida;

    public Opening(GWindow ventana)throws InterruptedException,IOException {
        int anchoVentana = ventana.getWindowWidth();
        int altoVentana = ventana.getWindowHeight();
        
        Musica sonidoFondo = new Musica("background-sound.wav");
        sonidoFondo.star();
        // Crear las piezas (rey y torre)
        piezas = new PiezasOpening(ventana);

        //Agregar Fondo con imagen jpg
        Imagenes fondo = new Imagenes(0, 0, "background.jpg", anchoVentana, altoVentana);
        fondo.ponerImagen(ventana);
        
        // Crear el texto de bienvenida con una fuente más grande
        String mensaje = "Ajedrez Automatico:Defensa Owen";
        textoBienvenida = new TextShape(mensaje, anchoVentana / 2 - (mensaje.length() * 10), 200, Color.WHITE);

        
        // Establecer una fuente más grande para el texto
        Font fuente = new Font("Arial", Font.BOLD, 40);  // Fuente Arial, negrita, tamaño 40
        textoBienvenida.setFont(fuente);
    }

    /**
     * Muestra la introducción con el rey y la torre en el centro de la pantalla.
     * 
     * @param ventana Ventana donde se dibujarán los objetos
     */
    public void mostrarIntroduccion(GWindow ventana)throws IOException, InterruptedException {
        // Agregar las piezas a la ventana
        piezas.mostrarPiezas(ventana);

        

       
        // Agregar el texto de bienvenida
        textoBienvenida.addTo(ventana);

        // Pausa para mostrar la introducción durante algunos segundos
        Thread.sleep(5000);

        // Limpiar la ventana después de la introducción
        ventana.erase();
    }
}