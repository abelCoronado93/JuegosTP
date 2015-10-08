package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.UtilidadesReversi;

/**
 * Clase Jugador Aleatorio Reversi
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class JugadorAleatorioReversi implements Jugador {

	private FactoriaTipoJuego f;

	public JugadorAleatorioReversi(FactoriaTipoJuego f) {

		this.f = f;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		int col, fila;
		col = randInt(1, tab.getAncho());
		fila = randInt(1, tab.getAlto());

		while (!esValido(tab, color, col, fila)) {

			col = randInt(1, tab.getAncho());
			fila = randInt(1, tab.getAlto());
		}

		return f.creaMovimiento(col, fila, color);
	}
	
	/**
	 * Comprueba si el movimiento es válido
	 * 
	 * @param tab
	 * @param color
	 * @param col
	 * @param fila
	 * @return true/false
	 */
	private boolean esValido(Tablero tab, Ficha color, int col, int fila) {

		if (tab.getCasilla(col, fila) == Ficha.VACIA
				&& UtilidadesReversi.posicionValida(tab, new MovimientoReversi(
						col, fila, color), fila))
			return true;

		return false;
	}

	/**
	 * Random Movimiento reversi
	 * 
	 * @param min
	 * @param max
	 * 
	 * @return columna o fila (entre 1 y getAncho() o entre 1 y getAlto())
	 */
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
