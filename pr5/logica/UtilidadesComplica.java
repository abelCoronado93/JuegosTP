package tp.pr5.logica;

/**
 * Clase para la lógica del Complica
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class UtilidadesComplica {

	/**
	 * Constante que indica el numero de fichas consecutivas necesarias para
	 * ganar
	 */
	public final static int CONECTA = 4;

	/**
	 * Comprueba el tablero para el juego COMPLICA
	 * 
	 * @param tableroAux
	 * @return color del ganador si lo hay
	 */
	public static Ficha complicAll(Tablero tableroAux, Movimiento mov) {

		boolean blancas = complicaWinner(tableroAux, Ficha.BLANCA);
		boolean negras = complicaWinner(tableroAux, Ficha.NEGRA);

		if (negras && !blancas)
			return Ficha.NEGRA;

		else if (!negras && blancas)
			return Ficha.BLANCA;

		return Ficha.VACIA;
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Negras en todo el Tablero
	 * 
	 * @param tablero
	 * @return true: si se han formado grupos de "CONECTA" Negras / false: si no
	 */
	public static boolean complicaWinner(Tablero tablero, Ficha color) {

		if (complicaCol(tablero, color) || complicaFila(tablero, color)
				|| complicaDiagonales(tablero, color))
			return true;
		return false;
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en todas las
	 * columnas del Tablero
	 * 
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en las columnas /
	 *         false: si no
	 */
	public static boolean complicaCol(Tablero tablero, Ficha turno) {

		int contador = 0;
		for (int i = 1; i <= tablero.getAncho() && contador < CONECTA; i++) {
			contador = 0;
			for (int j = tablero.getAlto(); j >= 1 && contador < CONECTA; j--) {
				if (tablero.getCasilla(i, j) == turno)
					contador++;
				else
					contador = 0;
			}
		}
		return (contador == CONECTA);
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en todas las filas
	 * del Tablero
	 * 
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en las filas / false:
	 *         si no
	 */
	public static boolean complicaFila(Tablero tablero, Ficha turno) {

		int contador = 0;
		for (int i = tablero.getAlto(); i >= 1 && contador < CONECTA; i--) {
			contador = 0;
			for (int j = 1; j <= tablero.getAncho() && contador < CONECTA; j++) {
				if (tablero.getCasilla(j, i) == turno)
					contador++;
				else
					contador = 0;
			}
		}
		return (contador == CONECTA);
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en las diagonales
	 * 
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en las diagonales
	 */

	/*
	 * El juego Complica al tener dimensiones fijas 4x7 sólo comprobamos las
	 * diagonales hasta la mitad del tablero. Al ser más alto que ancho, a
	 * partir de la mitad ya no se podrían formar grupos de CONECTA
	 */

	// Por eso las diagonales las comprobamos distinto en GR
	public static boolean complicaDiagonales(Tablero tablero, Ficha turno) {

		if (complicaDiagonal1(tablero, turno)
				|| complicaDiagonal2(tablero, turno))
			return true;
		return false;
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en las diagonales
	 * "/"
	 * 
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en las diagonales "/"
	 *         false: si no
	 */
	public static boolean complicaDiagonal1(Tablero tablero, Ficha turno) {

		int contador = 0, fila;
		for (int i = 1; i <= tablero.getAlto() && contador < CONECTA; i++) {
			contador = 0;
			fila = i;
			for (int j = 1; j <= tablero.getAncho() && fila >= 1
					&& contador < CONECTA; j++) {
				if (tablero.getCasilla(j, fila) == turno)
					contador++;
				else
					contador = 0;
				fila--;
			}
		}
		return (contador == CONECTA);
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en todas las
	 * diagonales "\"
	 * 
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en todas las
	 *         diagonales "\" false: si no
	 */
	public static boolean complicaDiagonal2(Tablero tablero, Ficha turno) {

		int contador = 0, fila;
		for (int i = 1; i <= tablero.getAlto() && contador < CONECTA; i++) {
			contador = 0;
			fila = i;
			for (int j = tablero.getAncho(); j >= 1 && fila >= 1
					&& contador < CONECTA; j--) {
				if (tablero.getCasilla(j, fila) == turno)
					contador++;
				else
					contador = 0;
				fila--;
			}
		}
		return (contador == CONECTA);
	}

}
