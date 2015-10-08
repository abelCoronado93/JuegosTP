package tp.pr5.logica;

/**
 * Clase para la lógica del Conecta4
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class UtilidadesConecta4 {
	
	/**
	 * Constante que indica el numero de fichas consecutivas necesarias para
	 * ganar
	 */
	public final static int CONECTA = 4;

	/**
	 * Comprueba el tablero
	 * 
	 * @param mov
	 * @param tableroAux
	 * @return true: si se han formado grupos de "CONECTA" en el tablero /
	 *         false: si no
	 */
	public static boolean conectAll(Movimiento mov, Tablero tableroAux) {

		int fila = numFila(mov.donde, tableroAux);
		if (conectaColumna(mov.donde, tableroAux, mov.color)
				|| conectaFila(fila, mov.donde, tableroAux, mov.color)
				|| conectaDiagonales(fila, mov.donde, tableroAux, mov.color))

			return true;

		return false;
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en la columna
	 * introducida
	 * 
	 * @param col
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en la columna /
	 *         false: si no
	 */
	public static boolean conectaColumna(int col, Tablero tablero, Ficha turno) {

		int contador = 0, fila = 1;
		while (contador < CONECTA && fila <= tablero.getAlto()) {
			if (tablero.getCasilla(col, fila) == turno)
				contador++;
			else
				contador = 0;
			fila++;
		}
		return (contador == CONECTA);
	}

	/**
	 * Devuelve la fila que hay que comprobar
	 * 
	 * @param col
	 * @param tablero
	 * @return fila
	 */
	public static int numFila(int col, Tablero tablero) {

		int filaCheck = 1;
		while ((filaCheck <= tablero.getAlto())
				&& (tablero.getCasilla(col, filaCheck) == Ficha.VACIA))
			filaCheck++;

		return filaCheck;
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en fila
	 * 
	 * @param fila
	 * @param col
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en la fila / false:
	 *         si no
	 */
	public static boolean conectaFila(int fila, int col, Tablero tablero,
			Ficha turno) {

		int contador = 0, i = 1;
		while (contador < CONECTA && i <= tablero.getAncho()) {
			if (tablero.getCasilla(i, fila) == turno)
				contador++;
			else
				contador = 0;
			i++;
		}
		return (contador == CONECTA);
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en las diagonales
	 * 
	 * @param fila
	 * @param col
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en las diagonales /
	 *         false: si no
	 */
	public static boolean conectaDiagonales(int fila, int col, Tablero tablero,
			Ficha turno) {

		if (checkDiagonal1(fila, col, tablero, turno)
				|| checkDiagonal2(fila, col, tablero, turno))
			return true;

		return false;
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en la diagonal "/"
	 * 
	 * @param fila
	 * @param col
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en la diagonal "/"
	 *         false: si no
	 */
	public static boolean checkDiagonal1(int fila, int col, Tablero tablero,
			Ficha turno) {

		int contador = 0, j = fila, i = col;

		// Sacamos la coordenada para comprobar TODA la diagonal
		while ((i >= 1) && (j <= tablero.getAlto())) {
			i--;
			j++;
		}
		if (i < 1 || j > tablero.getAlto()) {
			i++;
			j--;
		}
		/*-----------------------------------------------------*/
		while ((i <= tablero.getAncho()) && (j >= 1) && (contador < CONECTA)) {
			if (tablero.getCasilla(i, j) == turno)
				contador++;
			else
				contador = 0;
			i++;
			j--;
		}
		return (contador == CONECTA);
	}

	/**
	 * Comprueba si se han formado grupos de "CONECTA" Fichas en la diagonal "\"
	 * 
	 * @param fila
	 * @param col
	 * @param tablero
	 * @param turno
	 * @return true: si se han formado grupos de "CONECTA" en la diagonal "\"
	 *         false: si no
	 */
	public static boolean checkDiagonal2(int fila, int col, Tablero tablero,
			Ficha turno) {

		int contador = 0, j = fila, i = col;

		/* Sacamos la coordenada para comprobar TODA la diagonal */
		while (i >= 1 && j >= 1) {
			i--;
			j--;
		}
		if (i < 1 || j <= tablero.getAlto()) {
			i++;
			j++;
		}
		/*-----------------------------------------------------*/
		while (i <= tablero.getAncho() && j <= tablero.getAlto()
				&& (contador < CONECTA)) {
			if (tablero.getCasilla(i, j) == turno)
				contador++;
			else
				contador = 0;
			i++;
			j++;
		}
		return (contador == CONECTA);
	}
}
