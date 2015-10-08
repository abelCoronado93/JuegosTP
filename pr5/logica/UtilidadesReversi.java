package tp.pr5.logica;

/**
 * Clase para la lógica del Reversi
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class UtilidadesReversi {

	/**
	 * Comprueba si hay ganador
	 * 
	 * @param t
	 * @param ultimoMovimiento
	 * @return Ficha
	 */
	public static Ficha hayGanador(Tablero t, Movimiento ultimoMovimiento) {

		int negras = cuentaFichas(t, Ficha.NEGRA);
		int blancas = cuentaFichas(t, Ficha.BLANCA);

		if (negras == 0)
			return Ficha.BLANCA;

		else if (blancas == 0)
			return Ficha.NEGRA;

		else if (blancas + negras == t.getAlto() * t.getAncho() ||
				(!puedeMover(t, Ficha.BLANCA) && !puedeMover(t, Ficha.NEGRA))){

			if (blancas < negras)
				return Ficha.NEGRA;

			else if (blancas > negras)
				return Ficha.BLANCA;

			else
				return Ficha.VACIA;

		}
		return Ficha.VACIA;
	}

	/**
	 * Cuenta las Fichas de cada color
	 * 
	 * @param t
	 * @param color
	 * @return número de Fichas
	 */
	public static int cuentaFichas(Tablero t, Ficha color) {

		int numFichas = 0;

		for (int i = 1; i <= t.getAncho(); ++i)
			for (int j = 1; j <= t.getAlto(); ++j)
				if (t.getCasilla(i, j) == color)
					numFichas++;

		return numFichas;
	}

	/**
	 * Comprueba si hay tablas
	 * 
	 * @param t
	 * @return true/false
	 */
	public static boolean hayTablas(Tablero t) {

		return cuentaFichas(t, Ficha.NEGRA) == (t.getAlto() * t.getAncho()) / 2
				&& cuentaFichas(t, Ficha.BLANCA) == (t.getAlto() * t.getAncho()) / 2;
	}

	/**
	 * Comprueba si un Movimiento es válido
	 * 
	 * @param tab
	 * @param mov
	 * @param fila
	 * @return true/false
	 */
	public static boolean posicionValida(TableroInmutable tab, MovimientoReversi mov,
			int fila) {

		Ficha rival = colorRival(mov);

		if (tab.getCasilla(mov.donde, fila) == Ficha.VACIA) {

			if (horizontalDerecha(tab, mov, fila, rival)
					|| horizontalIzquierda(tab, mov, fila, rival)
					|| verticalArriba(tab, mov, fila, rival)
					|| verticalAbajo(tab, mov, fila, rival)
					|| diagonalArribaDerecha(tab, mov, fila, rival)
					|| diagonalArribaIzquierda(tab, mov, fila, rival)
					|| diagonalAbajoDerecha(tab, mov, fila, rival)
					|| diagonalAbajoIzquierda(tab, mov, fila, rival))

				return true;
		}

		return false;
	}

	public static boolean horizontalDerecha(TableroInmutable tab, MovimientoReversi mov,
			int fila, Ficha rival) {

		int col = mov.donde;

		col++;
		if (col > tab.getAncho() || tab.getCasilla(col, fila) != rival)
			return false;

		else {
			col++;
			while (col <= tab.getAncho()
					&& tab.getCasilla(col, fila) != Ficha.VACIA) {

				if (tab.getCasilla(col, fila) == mov.color)
					return true;

				else
					col++;
			}
		}
		return false;
	}

	public static boolean horizontalIzquierda(TableroInmutable tab,
			MovimientoReversi mov, int fila, Ficha rival) {

		int col = mov.donde;

		col--;
		if (col < 1 || tab.getCasilla(col, fila) != rival)
			return false;

		else {
			col--;
			while (col >= 1 && tab.getCasilla(col, fila) != Ficha.VACIA) {

				if (tab.getCasilla(col, fila) == mov.color)
					return true;

				else
					col--;
			}
		}
		return false;
	}

	public static boolean verticalArriba(TableroInmutable tab, MovimientoReversi mov,
			int fila, Ficha rival) {

		int fil = fila;

		fil--;
		if (fil < 1 || tab.getCasilla(mov.donde, fil) != rival)
			return false;

		else {
			fil--;
			while (fil >= 1 && tab.getCasilla(mov.donde, fil) != Ficha.VACIA) {

				if (tab.getCasilla(mov.donde, fil) == mov.color)
					return true;

				else
					fil--;
			}
		}

		return false;
	}

	public static boolean verticalAbajo(TableroInmutable tab, MovimientoReversi mov,
			int fila, Ficha rival) {

		int fil = fila;

		fil++;
		if (fil > tab.getAlto() || tab.getCasilla(mov.donde, fil) != rival)
			return false;

		else {
			fil++;
			while (fil <= tab.getAlto()
					&& tab.getCasilla(mov.donde, fil) != Ficha.VACIA) {

				if (tab.getCasilla(mov.donde, fil) == mov.color)
					return true;

				else
					fil++;
			}
		}
		return false;
	}

	public static boolean diagonalArribaDerecha(TableroInmutable tab,
			MovimientoReversi mov, int fila, Ficha rival) {

		int fil = fila;
		int col = mov.donde;

		col++;
		fil--;
		if (fil < 1 || col > tab.getAncho()
				|| tab.getCasilla(col, fil) != rival)
			return false;

		else {
			col++;
			fil--;
			while (fil >= 1 && col <= tab.getAncho()
					&& tab.getCasilla(col, fil) != Ficha.VACIA) {

				if (tab.getCasilla(col, fil) == mov.color)
					return true;

				else {
					fil--;
					col++;
				}
			}
		}
		return false;
	}

	public static boolean diagonalArribaIzquierda(TableroInmutable tab,
			MovimientoReversi mov, int fila, Ficha rival) {

		int fil = fila;
		int col = mov.donde;

		col--;
		fil--;
		if (fil < 1 || col < 1 || tab.getCasilla(col, fil) != rival)
			return false;

		else {
			col--;
			fil--;
			while (fil >= 1 && col >= 1
					&& tab.getCasilla(col, fil) != Ficha.VACIA) {

				if (tab.getCasilla(col, fil) == mov.color)
					return true;

				else {
					fil--;
					col--;
				}
			}
		}
		return false;
	}

	public static boolean diagonalAbajoDerecha(TableroInmutable tab,
			MovimientoReversi mov, int fila, Ficha rival) {

		int fil = fila;
		int col = mov.donde;

		col++;
		fil++;
		if (fil > tab.getAlto() || col > tab.getAncho()
				|| tab.getCasilla(col, fil) != rival)
			return false;

		else {
			col++;
			fil++;
			while (fil <= tab.getAlto() && col <= tab.getAncho()
					&& tab.getCasilla(col, fil) != Ficha.VACIA) {

				if (tab.getCasilla(col, fil) == mov.color)
					return true;

				else {
					fil++;
					col++;
				}
			}
		}
		return false;
	}

	public static boolean diagonalAbajoIzquierda(TableroInmutable tab,
			MovimientoReversi mov, int fila, Ficha rival) {

		int fil = fila;
		int col = mov.donde;

		col--;
		fil++;
		if (fil > tab.getAlto() || col < 1 || tab.getCasilla(col, fil) != rival)
			return false;

		else {
			col--;
			fil++;
			while (fil <= tab.getAlto() && col >= 1
					&& tab.getCasilla(col, fil) != Ficha.VACIA) {

				if (tab.getCasilla(col, fil) == mov.color)
					return true;

				else {
					fil++;
					col--;
				}
			}
		}
		return false;
	}

	public static Ficha colorRival(MovimientoReversi mov) {

		if (mov.color == Ficha.BLANCA)
			return Ficha.NEGRA;

		else
			return Ficha.BLANCA;
	}

	public static boolean puedeMover(Tablero t, Ficha color) {

		boolean puede = false;

		for (int i = 1; i <= t.getAncho() && !puede; ++i)

			for (int j = 1; j <= t.getAlto() && !puede; ++j)

				puede = UtilidadesReversi.posicionValida(t,
						new MovimientoReversi(i, j, color), j);

		return puede;
	}
}
