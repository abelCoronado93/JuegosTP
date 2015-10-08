package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/**
 * Observador
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public interface Observer {

	/**
	 * Modelo notifica a sus observadores que se reinicia la Partida
	 * 
	 * @param tab
	 * @param turno
	 */
	public abstract void onReset(TableroInmutable tab, Ficha turno,
			TipoJuego tipo);

	/**
	 * Modelo notifica a sus observadores que se ha deshecho un Movimiento
	 * 
	 * @param tab
	 * @param turno
	 * @param hayMas
	 */
	public abstract void onUndo(TableroInmutable tab, Ficha turno,
			boolean hayMas, TipoJuego tipo);

	/**
	 * Modelo notifica a sus observadores que no se ha podido deshacer
	 * 
	 * @param tab
	 * @param turno
	 */
	public abstract void onUndoNotPosible(TableroInmutable tab, Ficha turno);

	/**
	 * Modelo notifica a sus observadores que la Partida ha terminado
	 * 
	 * @param tab
	 * @param ganador
	 */
	public void onPartidaTerminada(final TableroInmutable tab,
			final Ficha ganador, final TipoJuego tipo, final Ficha turno);

	/**
	 * Modelo notifica a sus observadores que se ha terminado de realizar un
	 * Movimiento
	 * 
	 * @param tab
	 * @param turno
	 */
	public abstract void onMovimientoEnd(TableroInmutable tab, Ficha turno,
			TipoJuego tipo);

	/**
	 * Modelo notifica a sus observadores que se ha cambiado de juego
	 * 
	 * @param tab
	 * @param turno
	 */
	public abstract void onCambioJuego(TableroInmutable tab, Ficha turno,
			TipoJuego tipo);

	/**
	 * Modelo notifica a sus observadores que se ha introducido la opcion ayuda
	 * 
	 * @param tab
	 * @param turno
	 */
	public abstract void onAyuda(TableroInmutable tab, Ficha turno);

	/**
	 * Modelo notifica a sus obervadores que se ha pulsado salir
	 */
	public abstract void onSalir();

	/**
	 * Modelo notifica el cambio de Jugador
	 * 
	 * @param tab
	 * @param turno
	 */
	public abstract void onCambioJugador(TableroInmutable tab, Ficha turno);

	/**
	 * Modelo notifica comando incorrecto
	 * 
	 * @param tab
	 * @param turno
	 */
	public abstract void onComandoIncorrecto(TableroInmutable tab, Ficha turno);

	/**
	 * Modelo notifica movimiento incorrecto
	 * 
	 * @param movimientoException
	 * @param tab
	 * @param turno
	 */
	public abstract void onMovimientoIncorrecto(
			MovimientoInvalido movimientoException, TableroInmutable tab,
			Ficha turno);

	/**
	 * Modelo notifica error de parseo
	 * 
	 * @param tablero
	 * @param turno
	 */
	public abstract void onNumberFormatException(TableroInmutable tablero,
			Ficha turno);

	/**
	 * Modelo notifica que ha empezado el movimiento
	 * 
	 * @param tab
	 * @param turno
	 */
	public void onMovimientoStart(final TableroInmutable tab,
			final Ficha turno, final TipoJuego tipo);

	/**
	 * 
	 * @param s
	 */
	public abstract void onLog(String s);
}
