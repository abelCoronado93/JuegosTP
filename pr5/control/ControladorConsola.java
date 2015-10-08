package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TipoJuego;

/**
 * Controlador Consola
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class ControladorConsola {

	/**
	 * La partida del juego
	 */
	private Partida partida;

	/**
	 * Input del user
	 */
	private Scanner in;

	/**
	 * Factoria actual
	 */
	private FactoriaTipoJuego f;

	/**
	 * Blancas
	 */
	private Jugador j1;

	/**
	 * Negras
	 */
	private Jugador j2;

	/**
	 * Controladora del juego
	 * 
	 * @param f
	 *            - factoria actual
	 * @param p
	 *            - partida
	 * @param in
	 *            - input
	 */
	public ControladorConsola(FactoriaTipoJuego f, Partida p,
			java.util.Scanner in) {

		this.f = f;
		this.partida = p;
		this.in = in;
		this.j1 = f.creaJugadorHumanoConsola(this.in);
		this.j2 = f.creaJugadorHumanoConsola(this.in);
	}

	/**
	 * Añade Observer al ArrayList de Partida
	 * 
	 * @param o
	 */
	public void addObserver(Observer o) {

		partida.addObserver(o);
	}

	/**
	 * Elimina Observer del ArrayList de Partida
	 * 
	 * @param o
	 */
	public void removeObservador(Observer o) {

		partida.removeObserver(o);
	}

	/**
	 * Administra el desarrollo del juego
	 */
	public void run() {

		String opcion = "";

		estadoInicial();

		do {
			opcion = in.nextLine().toLowerCase();

			try {
				ejecutarOpcion(opcion);

			} catch (MovimientoInvalido e) {
				System.err.println(e.getMessage());
				this.partida.comandoIncorrecto();
			}

		} while (!this.partida.isTerminada());
	}

	/**
	 * Estado inicial de la Partida
	 */
	private void estadoInicial() {

		System.out.println(this.partida.dameTablero());
		System.out.println(printTurno());
		System.out.print("Qué quieres hacer? ");
	}

	/**
	 * Administra la opcion introducida por el user
	 * 
	 * @param opcion
	 * @throws MovimientoInvalido
	 */
	private void ejecutarOpcion(String opcion) throws MovimientoInvalido {

		if (opcion.equalsIgnoreCase("salir"))
			salir();
		else if (opcion.equalsIgnoreCase("poner"))
			poner();
		else if (opcion.indexOf("jugador") == 0)
			jugador(opcion);
		else if (opcion.equalsIgnoreCase("ayuda"))
			ayuda();
		else if (opcion.equalsIgnoreCase("deshacer"))
			deshacer();
		else if (opcion.equalsIgnoreCase("reiniciar"))
			reiniciar();
		else if (opcion.indexOf("jugar") == 0)
			parseoJugar(opcion);
		else
			throw new MovimientoInvalido("No te entiendo.");
	}

	/**
	 * Parseo del comando jugar
	 * 
	 * @param comando
	 * @throws MovimientoInvalido
	 */
	private void parseoJugar(String comando) throws MovimientoInvalido {

		String[] aux = comando.split(" ");

		if (aux[0].equalsIgnoreCase("jugar") && aux.length == 1)
			throw new MovimientoInvalido("No te entiendo.");

		if (aux[1].equalsIgnoreCase("co") && (aux.length == 2))
			jugar("co");

		else if (aux[1].equalsIgnoreCase("c4") && (aux.length == 2))
			jugar("c4");

		else if (aux[1].equalsIgnoreCase("rv") && (aux.length == 2))
			jugar("rv");

		else if (aux[1].equalsIgnoreCase("gr") && (aux.length == 4))
			jugar(aux[1] + " " + aux[2] + " " + aux[3]);

		else
			throw new MovimientoInvalido("No te entiendo.");
	}

	/**
	 * Muestra la ayuda
	 */
	public void ayuda() {

		this.partida.ayuda();
	}

	/**
	 * Parsea el jugador y actualiza según el comando
	 * 
	 * @param opcion
	 * @throws MovimientoInvalido
	 */
	public void jugador(String opcion) throws MovimientoInvalido {

		String[] aux = opcion.split(" ");

		if (aux.length == 3) {
			if ((aux[1].equals("blancas")) && (aux[2].equals("aleatorio")))
				this.j1 = this.f.creaJugadorAleatorio();

			else if ((aux[1].equals("blancas")) && (aux[2].equals("humano")))
				this.j1 = this.f.creaJugadorHumanoConsola(this.in);

			else if ((aux[1].equals("negras")) && (aux[2].equals("aleatorio")))
				this.j2 = this.f.creaJugadorAleatorio();

			else if ((aux[1].equals("negras")) && (aux[2].equals("humano")))
				this.j2 = this.f.creaJugadorHumanoConsola(this.in);

			else
				throw new MovimientoInvalido("Jugador inválido");

			this.partida.cambioJugador();
		}

		else
			throw new MovimientoInvalido("Jugador inválido");
	}

	/**
	 * Imprime el turno actual
	 * 
	 * @return cadena con el turno actual
	 */
	private String printTurno() {

		if (this.partida.getTurno() == Ficha.BLANCA)
			return "Juegan blancas";

		return "Juegan negras";
	}

	/**
	 * Ejecuta movimiento si es posible
	 * 
	 * @throws MovimientoInvalido
	 */
	private void poner() {

		if (this.partida.getTurno() == Ficha.BLANCA)
			this.partida.dameMovimiento(j1);

		else
			this.partida.dameMovimiento(j2);
	}

	/**
	 * Reinicia la partida
	 */
	public void reiniciar() {

		this.partida.reset(this.f.creaReglas());
		this.j1 = f.creaJugadorHumanoConsola(this.in);
		this.j2 = f.creaJugadorHumanoConsola(this.in);
	}

	/**
	 * Acaba la partida
	 */
	public void salir() {
		
		this.partida.partidaAcabada();
	}

	/**
	 * Deshace el último movimiento
	 */
	public void deshacer() {

		this.partida.undo();
	}

	/**
	 * Permite elegir el juego que el user desea ejecutar
	 */
	public void jugar(String juego) {

		try {
			this.f = TipoJuego.fromParam(juego);
			this.j1 = f.creaJugadorHumanoConsola(in);
			this.j2 = f.creaJugadorHumanoConsola(in);
			this.partida.cambioJuego(f.creaReglas(), null);

		} catch (MovimientoInvalido e) {
			System.err.println(e.getMessage());
		}
	}
}
