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
     * Método para mover una pieza en el tablero usando notación algebraica.
     *
     * @param movimiento Movimiento en formato "e2e4".
     * @param ventana    Ventana gráfica.
     */
    public void moverPieza(String movimiento, GWindow ventana) {
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

        // Mover la pieza a la posición final
        Imagenes pieza = piezas[filaInicial][colInicial];
        pieza.mover((colFinal - colInicial) * anchoCelda, (filaFinal - filaInicial) * altoCelda);
        pieza.ponerImagen(ventana);

        // Actualizar el tablero
        piezas[filaFinal][colFinal] = pieza;
        piezas[filaInicial][colInicial] = null;

        System.out.println("Pieza movida de " + movimiento.substring(0, 2) + " a " + movimiento.substring(2, 4));
    }

    // Valida si las coordenadas están dentro del rango del tablero
    private boolean coordenadasValidas(int fila, int col) {
        return fila >= 0 && fila < 8 && col >= 0 && col < 8;
    }

    // Métodos de inicialización del tablero
    private void inicializarTablero(GWindow ventana) {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Color colorCelda = (fila + col) % 2 == 0 ? Color.WHITE : Color.BLACK;
                Rectangle celda = new Rectangle(
                        col * anchoCelda, // posición x
                        fila * altoCelda, // posición y
                        anchoCelda,       // ancho de la celda
                        altoCelda,        // alto de la celda
                        colorCelda,       // color
                        true              // relleno
                );
                ventana.add(celda);
            }
        }
    }

    private void colocarPiezasIniciales(GWindow ventana) throws IOException {
        // Piezas blancas
        String[] piezasPrincipalesBlancas = {"rook", "knight", "bishop", "queen", "king", "bishop", "knight", "rook"};
        for (int col = 0; col < 8; col++) {
            piezas[7][col] = new Imagenes(col * anchoCelda, 7 * altoCelda, "white-" + piezasPrincipalesBlancas[col] + ".png", anchoCelda, altoCelda);
            piezas[6][col] = new Imagenes(col * anchoCelda, 6 * altoCelda, "white-pawn.png", anchoCelda, altoCelda);
            piezas[7][col].ponerImagen(ventana);
            piezas[6][col].ponerImagen(ventana);
        }

        // Piezas negras
        String[] piezasPrincipalesNegras = {"rook", "knight", "bishop", "queen", "king", "bishop", "knight", "rook"};
        for (int col = 0; col < 8; col++) {
            piezas[0][col] = new Imagenes(col * anchoCelda, 0 * altoCelda, "black-" + piezasPrincipalesNegras[col] + ".png", anchoCelda, altoCelda);
            piezas[1][col] = new Imagenes(col * anchoCelda, 1 * altoCelda, "black-pawn.png", anchoCelda, altoCelda);
            piezas[0][col].ponerImagen(ventana);
            piezas[1][col].ponerImagen(ventana);
        }
    }

    public static void main(String[] args) throws IOException {
        GWindow ventana = new GWindow("Partida de Ajedrez", 850, 850);
        Partida partida = new Partida(ventana);

        String[] movimientos = {"e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "d7d6"};
        for (String movimiento : movimientos) {
            partida.moverPieza(movimiento, ventana);
            try {
                Thread.sleep(1000); // Pausa entre movimientos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
