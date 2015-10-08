package tp.pr5.logica;

/**
 * Esta clase lleva las reglas del juego
 * 
 * @author Abel Coronado y María Castañeda
 *
 */
public interface ReglasJuego {

	/**
	 * Construye el tablero que hay que utilizar para la partida, según las
	 * reglas del juego
	 * 
	 * @return Tablero a utilizar
	 */
	public abstract Tablero iniciaTablero();

	/**
	 * Devuelve el color del jugador que comienza la partida
	 * 
	 * @return Ficha del primer jugador
	 */
	public abstract Ficha jugadorInicial();

	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no. Devuelve
	 * el color del ganador (si lo hay)
	 * 
	 * @param ultimoMovimiento
	 * 			-último Movimiento realizado
	 * @param t
	 * 			-estado del Tablero
	 * 
	 * @return color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA
	 */
	public abstract Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);

	/**
	 * Devuelve true si, con el estado del tablero dado, la partida ha terminado
	 * en tablas
	 * 
	 * @param ultimoEnPoner
	 * 			-jugador que acaba de poner Ficha
	 * @param t
	 * 			-estado del Tablero
	 * 
	 * @return true: si la partida ha terminado sin ganador
	 */
	public abstract boolean tablas(Ficha ultimoEnPoner, Tablero t);

	/**
	 * Devuelve el color del jugador al que le toca poner
	 * 
	 * @param ultimoEnPoner
	 * 			-último jugador en poner Ficha
	 * @param t
	 * 			-estado del Tablero
	 * 
	 * @return siguiente jugador que debe poner Ficha
	 */
	public abstract Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);
}
