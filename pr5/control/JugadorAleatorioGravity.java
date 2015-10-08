package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

/**
 * Clase Jugador Aleatorio Gravity
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class JugadorAleatorioGravity implements Jugador {

	private FactoriaTipoJuego f;
	
	public JugadorAleatorioGravity(FactoriaTipoJuego f){
		
		this.f = f;
	}
	
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
	
		int col, fila;
		col = randInt(1, tab.getAncho());
		fila = randInt(1, tab.getAlto());

		while (tab.getCasilla(col, fila) != Ficha.VACIA) {
			col = randInt(1, tab.getAncho());
			fila = randInt(1, tab.getAlto());
		}
		return f.creaMovimiento(col, fila, color);

	}

	/**
	 * Random Movimiento gravity
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
