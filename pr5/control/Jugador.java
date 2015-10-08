package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

/**
 * Interface Jugador
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public interface Jugador {

	/**
	 * Crea un Movimiento
	 * 
	 * @param tab
	 * @param color
	 * 
	 * @return Movimiento del jugador (humano o aleatorio)
	 */
	public abstract Movimiento getMovimiento(Tablero tab, Ficha color);
}
