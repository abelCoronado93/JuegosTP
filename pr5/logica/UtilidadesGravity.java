package tp.pr5.logica;

/**
 * Clase para la lógica del Gravity
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class UtilidadesGravity {

	/**
	 * Constante que indica el numero de fichas consecutivas necesarias para
	 * ganar
	 */
	public final static int CONECTA = 4;

	/**
	 * Comprueba el tablero para el juego GRAVITY
	 * 
	 * @param tableroAux
	 * @return color del ganador si lo hay
	 */
	public static Ficha gravityAll(Tablero tableroAux, Movimiento mov) {

		if (gravityWinner(tableroAux, mov.color))
			return mov.color;
		return Ficha.VACIA;
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" en todo el Tablero
	 * 
	 * @param tablero
	 * @return true: si se han formado grupos de "CONECTA" / false: si no
	 */
	public static boolean gravityWinner(Tablero tablero, Ficha color) {

		if (gravityCol(tablero, color) || gravityFila(tablero, color)
				|| gravityDiagonales(tablero, color))
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
	public static boolean gravityCol(Tablero tablero, Ficha turno) {

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
	public static boolean gravityFila(Tablero tablero, Ficha turno) {

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
	public static boolean gravityDiagonales(Tablero tablero, Ficha turno) {

		if (gravityDiagonal1(tablero, turno)
				|| gravityDiagonal2(tablero, turno))
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
	public static boolean gravityDiagonal1(Tablero tablero, Ficha turno) {

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
		int col;
		for (int i = 2; i <= tablero.getAncho() && contador < CONECTA; i++) {
			contador = 0;
			col = i;
			for (int j = tablero.getAlto(); col <= tablero.getAncho() && j > 0
					&& contador < CONECTA; j--) {
				if (tablero.getCasilla(col, j) == turno)
					contador++;
				else
					contador = 0;
				col++;
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
	public static boolean gravityDiagonal2(Tablero tablero, Ficha turno) {

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
		int col;
		for (int i = tablero.getAncho() - 1; i > 0 && contador < CONECTA; i--) {
			contador = 0;
			col = i;
			for (int j = tablero.getAlto(); col > 0 && j > 0
					&& contador < CONECTA; j--) {
				if (tablero.getCasilla(col, j) == turno)
					contador++;
				else
					contador = 0;
				col--;
			}
		}
		return (contador == CONECTA);
	}
}
