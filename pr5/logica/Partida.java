package tp.pr5.logica;

import java.util.ArrayList;
import java.util.Stack;

import tp.pr5.control.Jugador;
import tp.pr5.control.Modo;
import tp.pr5.control.Observer;

/**
 * 
 * Esta clase lleva el control de la partida en curso.
 * 
 * @author Abel Coronado y María Castañeda
 *
 */
public class Partida {

	/**
	 * ArrayList de Observers
	 */
	private ArrayList<Observer> obs;

	/**
	 * El Tablero de la Partida
	 */
	private Tablero tablero;

	/**
	 * Más movimientos en la pila
	 */
	private boolean hayMas;

	/**
	 * Guarda el color del turno actual
	 */
	private Ficha turno;

	/**
	 * Indica si ha acabado la partida
	 */
	private boolean terminada;

	/**
	 * Guarda el color del ganador (inicialmente VACIA)
	 */
	private Ficha ganador;

	/**
	 * Stack donde se almacenan los movimientos válidos
	 */
	private Stack<Movimiento> undoStack;

	/**
	 * Reglas de la partida
	 */
	private ReglasJuego reglas;

	/**
	 * Tipo de juego
	 */
	private TipoJuego tipoJuego = null;

	/**
	 * Array con strings del log
	 */
	private ArrayList<String> logger;

	/**
	 * Constructor de la partida con unas reglas determinadas
	 * 
	 * @param reglas
	 *            - ReglasJuego
	 */
	public Partida(ReglasJuego reglas) {

		this.logger = new ArrayList<String>();
		this.reglas = reglas;
		this.turno = this.reglas.jugadorInicial();
		this.tablero = this.reglas.iniciaTablero();
		this.undoStack = new Stack<Movimiento>();
		this.ganador = Ficha.VACIA;
		this.terminada = false;
		this.obs = new ArrayList<Observer>();
	}

	/**
	 * Introduce una Ficha en una movimiento dado: Comprueba si el mov es
	 * valido, si la columna está llena y si la partida no ha terminado. Si
	 * cumple los requisitos de entrada, coloca la Ficha donde corresponde.
	 * Comprueba si la Ficha introducida a formado grupos de 4
	 * 
	 * @param mov
	 */
	public void ejecutaMovimiento(Movimiento mov) {

		try {
			movimientoValido(mov);

			mov.ejecutaMovimiento(this.tablero);

			this.undoStack.push(mov);

			this.ganador = this.reglas.hayGanador(mov, this.tablero);

			if (this.ganador != Ficha.VACIA
					|| this.reglas.tablas(mov.color, this.tablero))

				haAcabado();

			else {

				String s = this.turno + " ha ejecutado movimiento";
				this.logger.add(s);
				
				this.turno = reglas.siguienteTurno(this.turno, this.tablero);

				for (Observer o : obs) {
					o.onMovimientoEnd(this.tablero, this.turno, this.tipoJuego);
					o.onLog(s);
				}

				this.continuarPartida();
			}

		} catch (MovimientoInvalido m) {

			String s = this.turno + ": error en movimiento";
			logger.add(s);

			for (Observer o : obs) {
				o.onMovimientoIncorrecto(m, this.tablero, this.turno);
				o.onLog(s);
			}
		}
	}

	/**
	 * Resetea la partida actual (esta opción no puede deshacerse)
	 * 
	 * @param reglas
	 *            - ReglasJuego
	 */
	public void reset(ReglasJuego reglas) {

		initReset(reglas);
		String s = this.turno + " ha reseteado la partida";
		logger.add(s);

		for (Observer o : obs) {
			o.onReset(this.tablero, this.turno, this.tipoJuego);
			o.onLog(s);
		}
	}

	/**
	 * Inicialización del reset
	 * 
	 * @param reglas
	 */
	private void initReset(ReglasJuego reglas) {

		this.reglas = reglas;
		this.tablero = this.reglas.iniciaTablero();
		this.turno = this.reglas.jugadorInicial();
		this.ganador = Ficha.VACIA;
		this.terminada = false;
		this.undoStack.clear();
	}

	/**
	 * Cambia el juego actual
	 * 
	 * @param reglas
	 *            - ReglasJuego
	 */
	public void cambioJuego(ReglasJuego reglas, TipoJuego tipo) {

		this.tipoJuego = tipo;

		String s = this.turno + " ha cambiado el juego";
		logger.add(s);
		initReset(reglas);

		for (Observer o : obs) {
			o.onCambioJuego(this.tablero, this.turno, tipo);
			o.onLog(s);
		}
	}

	/**
	 * Comando introducido en consola incorrecto
	 */
	public void comandoIncorrecto() {

		String s = this.turno + ": comando incorrecto";
		logger.add(s);

		for (Observer o : obs) {
			o.onComandoIncorrecto(this.tablero, this.turno);
			o.onLog(s);
		}
	}

	/**
	 * Cambio de Jugador (aleatorio)
	 */
	public void cambioJugador() {

		String s = this.turno + " ha cambiado el tipo de jugador";
		logger.add(s);

		for (Observer o : obs) {
			o.onCambioJugador(this.tablero, this.turno);
			o.onLog(s);
		}
	}

	/**
	 * Comprueba si el movimiento es válido
	 * 
	 * @param mov
	 * 
	 * @throws MovimientoInvalido
	 */
	private void movimientoValido(Movimiento mov) throws MovimientoInvalido {

		if (this.terminada || mov.color != this.turno)
			throw new MovimientoInvalido(
					"Columna incorrecta. Debe estar entre 1 y "
							+ this.tablero.getAncho() + ".");
	}

	/**
	 * Turno actual de la partida
	 * 
	 * @return Ficha actual
	 */
	public Ficha getTurno() {

		return this.turno;
	}

	/**
	 * Estado actual de la partida
	 * 
	 * @return true: si ha terminado la partida / false: coc
	 */
	public boolean isTerminada() {

		return this.terminada;
	}

	/**
	 * Deshace el último movimiento ejectutado
	 * 
	 * @return true: si se ha deshecho / false: coc
	 */
	public boolean undo() {

		String s;

		if (!this.undoStack.empty()) {
			Movimiento stackMov = this.undoStack.get(this.undoStack.size() - 1);
			this.undoStack.pop();
			stackMov.undo(this.tablero);
			
			if (this.undoStack.size() > 0)
				hayMas = true;
			else
				hayMas = false;

			s = this.turno + " ha deshecho movimiento";
			this.logger.add(s);
			
			this.turno = reglas.siguienteTurno(this.turno, this.tablero);

			for (Observer o : obs) {
				o.onUndo(this.tablero, turno, hayMas, this.tipoJuego);
				o.onLog(s);
			}

			return true;

		} else {
			
			s = this.turno + ": no pudo deshacer movimiento";
			this.logger.add(s);

			for (Observer o : obs) {
				o.onUndoNotPosible(this.tablero, turno);
				o.onLog(s);
			}
		}

		return false;
	}

	/**
	 * Obtiene Movimiento a ejecutar
	 * 
	 * @param Jugador
	 */
	public void dameMovimiento(Jugador j) {

		Movimiento mov = null;

		try {
			mov = j.getMovimiento(this.tablero, this.turno);

		} catch (NumberFormatException nfe) {

			System.err.print("Valor no numérico.");

			String s = this.turno + ": valor de entrada no numerico";
			logger.add(s);

			for (Observer o : obs) {
				o.onNumberFormatException(this.tablero, turno);
				o.onLog(s);
			}
		}

		if (mov != null)
			this.ejecutaMovimiento(mov);
	}

	/**
	 * Comando ayuda (imprime la ayuda en consola)
	 */
	public void ayuda() {

		String s = this.turno + " solicitó ayuda";
		logger.add(s);

		for (Observer o : obs) {
			o.onAyuda(this.tablero, this.turno);
			o.onLog(s);
		}
	}

	/**
	 * Obtiene cadena Tablero
	 * 
	 * @return
	 */
	public String dameTablero() {

		return this.tablero.toString();
	}

	/**
	 * Añade Observer al ArrayList
	 * 
	 * @param o
	 */
	public void addObserver(Observer o) {

		this.obs.add(o);
	}

	/**
	 * Elimina Observer del ArrayList (sin uso)
	 * 
	 * @param o
	 */
	public void removeObserver(Observer o) {

		this.obs.remove(o);
	}

	/**
	 * Estado inicial del GUI
	 */
	public void start(TipoJuego tipo) {

		this.tipoJuego = tipo;
		String s = "###-----Que comience el juego-----###";
		logger.add(s);

		for (Observer o : obs) {
			o.onReset(this.tablero, this.turno, this.tipoJuego);
			o.onLog(s);
		}
	}

	/**
	 * Pone la partida en terminada y actualiza las respectivas vistas
	 */
	public void haAcabado() {

		this.terminada = true;
		String s = "Partida finalizada";
		logger.add(s);

		for (Observer o : obs) {
			o.onPartidaTerminada(this.tablero, this.ganador, this.tipoJuego, this.turno);
			o.onLog(s);
		}
	}

	/**
	 * Crea Movimiento aleatorio
	 * 
	 * @param creaJugadorAleatorio
	 */
	public void ejecutaMovimientoAleatorio(Jugador creaJugadorAleatorio) {

		this.ejecutaMovimiento(creaJugadorAleatorio.getMovimiento(this.tablero,
				this.turno));
	}

	public void detenerPartida() {

		Modo modo = this.turno.getModo();
		modo.terminar();
	}

	public void continuarPartida() {

		if (!this.terminada) {
			for (Observer o : obs)
				o.onMovimientoStart(this.tablero, this.turno, this.tipoJuego);

			Modo modo = turno.getModo();
			modo.comenzar();
		}
	}

	public void partidaAcabada() {

		this.terminada = true;
		System.exit(0);
	}

	public void actualizarLog(String s) {
		
		logger.add(s);
		
		for (Observer o : obs)
			o.onLog(s);
	}
}
