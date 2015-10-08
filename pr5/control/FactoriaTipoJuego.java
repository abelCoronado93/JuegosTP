package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.ReglasJuego;

/**
 * Interface Factoria
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public interface FactoriaTipoJuego {

	/**
	 * Construye las reglas del juego concreto
	 * 
	 * return - objeto que implementa las reglas de juego
	 **/
	public abstract ReglasJuego creaReglas();

	/**
	 * Construye un Movimiento para el juego completo (es posible que no utilice
	 * todos los atributos
	 * 
	 * return - Movimiento
	 **/
	public abstract Movimiento creaMovimiento(int col, int fila, Ficha color);

	/**
	 * Construye un Jugador que se encarga de preguntar al user el siguiente
	 * movimiento
	 * 
	 * return - Jugador
	 **/
	public abstract Jugador creaJugadorHumanoConsola(java.util.Scanner in);

	/**
	 * Construye un Jugador aleatorio
	 * 
	 * return - Jugador
	 **/
	public abstract Jugador creaJugadorAleatorio();

}
