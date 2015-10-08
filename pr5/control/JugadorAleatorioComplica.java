package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

/**
 * Clase Jugador Aleatorio Complica
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class JugadorAleatorioComplica implements Jugador {

	private FactoriaTipoJuego f;

	public JugadorAleatorioComplica(FactoriaTipoJuego f) {
		
		this.f = f;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		int col = randInt(1, tab.getAncho());
		return f.creaMovimiento(col, 0, color);
	}

	/**
	 * Random Movimiento complica
	 * 
	 * @param min
	 * @param max
	 * 
	 * @return columna entre 1 y getAncho()
	 */
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
