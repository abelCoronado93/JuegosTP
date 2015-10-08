package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

/**
 * Clase Jugador Aleatorio Conecta 4
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class JugadorAleatorioConecta4 implements Jugador {

	private FactoriaTipoJuego f;

	public JugadorAleatorioConecta4(FactoriaTipoJuego f) {
		
		this.f = f;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		int col = randInt(1, tab.getAncho());

		while (columnaLlena(col, tab))
			col = randInt(1, tab.getAncho());
		
		return f.creaMovimiento(col, 0, color);
	}

	/**
	 * Random Movimiento conecta 4
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
	
	/**
	 * Comprueba si la columna esta llena
	 * 
	 * @param col
	 * @return true: si esta llena / false: coc
	 */
	public boolean columnaLlena(int col, Tablero tab) {

		if (tab.getCasilla(col, 1) != Ficha.VACIA)
			return true;
		
		return false;
	}

}
