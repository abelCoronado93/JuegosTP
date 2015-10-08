package tp.pr5.vistas;

import tp.pr5.control.ControladorConsola;
import tp.pr5.control.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/**
 * 
 * Clase Vista Consola
 * 
 * @author Abel Coronado y María Castañeda
 *
 */
public class VistaConsola implements Observer {

	/**
	 * Controlador Consola
	 */
	private ControladorConsola controlador;

	/**
	 * Constructor Vista Consola
	 * 
	 * @param c
	 */
	public VistaConsola(ControladorConsola c) {

		this.controlador = c;

		controlador.addObserver(this);
	}

	/**
	 * Estado inicial de la vista
	 * 
	 * @param tab
	 * @param turno
	 */
	private void estadoInicial(TableroInmutable tab, Ficha turno) {

		System.out.println(tab);

		if (turno == Ficha.BLANCA)
			System.out.println("Juegan blancas");

		else
			System.out.println("Juegan negras");

		System.out.print("Qué quieres hacer? ");
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

		System.out.println("Partida reiniciada.");

		estadoInicial(tab, turno);
	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas,
			TipoJuego tipo) {

		estadoInicial(tab, turno);
	}

	@Override
	public void onUndoNotPosible(TableroInmutable tab, Ficha turno) {

		System.err.println("Imposible deshacer.");

		estadoInicial(tab, turno);
	}

	@Override
	public void onPartidaTerminada(final TableroInmutable tab,
			final Ficha ganador, final TipoJuego tipo, final Ficha turno) {

		System.out.println(tab);

		if (ganador == Ficha.BLANCA)
			System.out.println("Ganan las blancas");

		else if (ganador == Ficha.NEGRA)
			System.out.println("Ganan las negras");

		else
			System.out.println("Partida terminada en tablas.");

		// this.controlador.salir();
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno,
			TipoJuego tipo) {

		estadoInicial(tab, turno);
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException,
			TableroInmutable tab, Ficha turno) {

		System.err.println(movimientoException.getMessage());

		estadoInicial(tab, turno);
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

		System.out.println("Partida reiniciada.");

		estadoInicial(tab, turno);
	}

	@Override
	public void onSalir() {

	}

	@Override
	public void onAyuda(TableroInmutable tab, Ficha turno) {

		System.out.println("Los comandos disponibles son:");
		System.out.println();
		System.out.println("PONER: utilízalo para poner la siguiente ficha.");
		System.out
				.println("DESHACER: deshace el último movimiento hecho en la partida.");
		System.out.println("REINICIAR: reinicia la partida.");
		System.out
				.println("JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego.");
		System.out
				.println("JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.");
		System.out.println("SALIR: termina la aplicación.");
		System.out.println("AYUDA: muestra esta ayuda.");
		System.out.println();

		estadoInicial(tab, turno);
	}

	@Override
	public void onCambioJugador(TableroInmutable tab, Ficha turno) {

		estadoInicial(tab, turno);
	}

	@Override
	public void onComandoIncorrecto(TableroInmutable tab, Ficha turno) {

		estadoInicial(tab, turno);
	}

	@Override
	public void onNumberFormatException(TableroInmutable tablero, Ficha turno) {

		estadoInicial(tablero, turno);
	}

	@Override
	public void onMovimientoStart(final TableroInmutable tab,
			final Ficha turno, final TipoJuego tipo) {
	}

	@Override
	public void onLog(String s) {

	}
}
