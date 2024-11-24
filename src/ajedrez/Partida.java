package ajedrez;

import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;
import java.awt.Color;
import java.io.IOException;

public class Partida {
    private final Imagenes[][] piezas = new Imagenes[8][8]; // Matriz de piezas en el tablero
    private final int anchoCelda;
    private final int altoCelda;

    public Partida(GWindow ventana) throws IOException {
        this.anchoCelda = 800 / 8;
        this.altoCelda = 800 / 8;

        // Inicializar tablero y piezas
        inicializarTablero(ventana);
        colocarPiezasIniciales(ventana);
    }
/**
 * Mueve una pieza en el tablero de ajedrez de acuerdo a un movimiento dado.
 *
 * @param movimiento Cadena que representa el movimiento en formato algebraico (ej. "e2e4").
 *                    Si el movimiento es "++", se reproduce un sonido de jaque mate y termina.
 * @param ventana Ventana gráfica donde se actualiza el tablero.
 * @throws InterruptedException Si el hilo es interrumpido durante el retraso.
 * 
 * El método realiza las siguientes acciones:
 * - Valida el formato y las coordenadas del movimiento.
 * - Reproduce sonidos para movimientos normales, capturas, y jaque mate.
 * - Actualiza las posiciones de las piezas en el tablero.
 * - Maneja la captura de piezas y actualiza la interfaz gráfica.
 * - Introduce un retraso entre movimientos para visualización.
 */

 public void moverPieza(String movimiento, GWindow ventana) throws InterruptedException {
    if (movimiento.equals("++")) {
        // Sonido especial para jaque mate
        Musica sonidoMate = new Musica("mate.wav");
        sonidoMate.star();
        Thread.sleep(10000); // Tiempo para terminar la partida
        System.out.println("¡Jaque mate!");
        return;
    }

    if (movimiento.length() != 4) {
        System.out.println("Movimiento inválido: " + movimiento);
        return;
    }

    // Convertir las posiciones inicial y final
    int filaInicial = 8 - Character.getNumericValue(movimiento.charAt(1));
    int colInicial = movimiento.charAt(0) - 'a';
    int filaFinal = 8 - Character.getNumericValue(movimiento.charAt(3));
    int colFinal = movimiento.charAt(2) - 'a';

    // Validar coordenadas
    if (!coordenadasValidas(filaInicial, colInicial) || !coordenadasValidas(filaFinal, colFinal)) {
        System.out.println("Coordenadas fuera de rango: " + movimiento);
        return;
    }

    // Validar que haya una pieza en la posición inicial
    if (piezas[filaInicial][colInicial] == null) {
        System.out.println("No hay pieza en la posición inicial: " + movimiento.substring(0, 2));
        return;
    }

    // Verificar si hay una pieza en la posición final (captura)
    if (piezas[filaFinal][colFinal] != null) {
        // Eliminar la pieza capturada
        piezas[filaFinal][colFinal].eliminarImagen(ventana);
        piezas[filaFinal][colFinal] = null;
        System.out.println("Pieza capturada en " + movimiento.substring(2, 4));
        
        // Reproducir sonido de captura
        Musica sonidoCaptura = new Musica("capture.wav");
        sonidoCaptura.star();
    } else {
        // Sonido para movimiento normal
        Musica sonidoMovimiento = new Musica("move-self.wav");
        sonidoMovimiento.star();
    }

    // Mover la pieza a la posición final
    Imagenes pieza = piezas[filaInicial][colInicial];
    pieza.mover((colFinal - colInicial) * anchoCelda, (filaFinal - filaInicial) * altoCelda);
    pieza.ponerImagen(ventana);

    // Actualizar el tablero
    piezas[filaFinal][colFinal] = pieza;
    piezas[filaInicial][colInicial] = null;

    System.out.println("Pieza movida de " + movimiento.substring(0, 2) + " a " + movimiento.substring(2, 4));
    
    // Agregar un retraso de 2000 ms (puedes ajustarlo a tu gusto)
    Thread.sleep(2000); // 2000 ms de retraso entre cada movimiento
}


    /**
     * Realiza una secuencia de movimientos en el tablero de ajedrez.
     *
     * @param movimientos Secuencia de movimientos en notación de ajedrez (e.g. "e2e4", "e7e5")
     * @param ventana Ventana gráfica donde se mostrará el tablero de ajedrez.
     * @throws InterruptedException Si se produce un error al realizar los movimientos.
     */
    public void recorrerMovimientos(String[] movimientos, GWindow ventana) throws InterruptedException {
        for (String movimiento : movimientos) {
            moverPieza(movimiento, ventana);
        }
    }

    /**
     * Verificar si las coordenadas dadas son válidas en el tablero de ajedrez.
     *
     * @param fila Fila de la coordenada (0-7).
     * @param col Columna de la coordenada (0-7).
     * @return True si las coordenadas son válidas, false de lo contrario.
     */
    private boolean coordenadasValidas(int fila, int col) {
        return fila >= 0 && fila < 8 && col >= 0 && col < 8;
    }


/**
 * Inicializa el tablero de ajedrez en la ventana gráfica.
 * 
 * @param ventana Ventana gráfica donde se dibujará el tablero.
 *                Cada celda del tablero se representa como un rectángulo
 *                con un color alternante para simular el patrón de un tablero
 *                de ajedrez.
 */
    private void inicializarTablero(GWindow ventana) {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Color colorCelda = (fila + col) % 2 == 0 ? Color.WHITE : new Color(184, 164, 151);
                Rectangle celda = new Rectangle(
                        col * anchoCelda,
                        fila * altoCelda,
                        anchoCelda,
                        altoCelda,
                        colorCelda,
                        true
                );
                ventana.add(celda);
            }
        }
    }

    /**
     * Coloca las piezas iniciales en el tablero de ajedrez.
     * 
     * @param ventana Ventana gráfica donde se mostrará el tablero de ajedrez.
     * @throws IOException Si se produce un error al leer las imágenes de las piezas.
     */

    private void colocarPiezasIniciales(GWindow ventana) throws IOException {
        String[] piezasPrincipalesBlancas = {"rook", "knight", "bishop", "queen", "king", "bishop", "knight", "rook"};
        for (int col = 0; col < 8; col++) {
            piezas[7][col] = new Imagenes(col * anchoCelda, 7 * altoCelda, "white-" + piezasPrincipalesBlancas[col] + ".png", anchoCelda, altoCelda);
            piezas[6][col] = new Imagenes(col * anchoCelda, 6 * altoCelda, "white-pawn.png", anchoCelda, altoCelda);
            piezas[7][col].ponerImagen(ventana);
            piezas[6][col].ponerImagen(ventana);
        }

        String[] piezasPrincipalesNegras = {"rook", "knight", "bishop", "queen", "king", "bishop", "knight", "rook"};
        for (int col = 0; col < 8; col++) {
            piezas[0][col] = new Imagenes(col * anchoCelda, 0 * altoCelda, "black-" + piezasPrincipalesNegras[col] + ".png", anchoCelda, altoCelda);
            piezas[1][col] = new Imagenes(col * anchoCelda, 1 * altoCelda, "black-pawn.png", anchoCelda, altoCelda);
            piezas[0][col].ponerImagen(ventana);
            piezas[1][col].ponerImagen(ventana);
        }
    }
}
