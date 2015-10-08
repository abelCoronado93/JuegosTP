package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TipoJuego;

/**
 * Controlador Gráfico
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class ControladorGui {

	/**
	 * Partida actual
	 */
	private Partida p;

	/**
	 * Factoria actual
	 */
	private FactoriaTipoJuego f;

	/**
	 * Tipo de juego
	 */
	private TipoJuego tipoJuego;

	/**
	 * Constructor del ControladorGUI
	 * 
	 * @param p
	 * @param f
	 */
	public ControladorGui(Partida p, FactoriaTipoJuego f, TipoJuego tipo) {

		this.p = p;
		this.f = f;
		this.tipoJuego = tipo;
		initModoJuego();
	}

	/**
	 * Inicializa el Modo de Juego
	 */
	private void initModoJuego() {

		Ficha.BLANCA.setModo(new ModoHumano());
		Ficha.NEGRA.setModo(new ModoHumano());

		Ficha.BLANCA.setTipoTurno(TipoTurno.HUMANO);
		Ficha.NEGRA.setTipoTurno(TipoTurno.HUMANO);
	}

	/**
	 * Empieza la Partida
	 */
	public void start() {

		p.start(this.tipoJuego);
	}

	/**
	 * Añade Observer al ArrayList de Partida
	 * 
	 * @param o
	 */
	public void addObserver(Observer o) {

		p.addObserver(o);
	}

	/**
	 * Elimina Observer del ArrayList de Partida
	 * 
	 * @param o
	 */
	public void removeObservador(Observer o) {

		p.removeObserver(o);
	}

	/**
	 * Resetea la Partida
	 */
	public void reiniciar() {

		this.p.detenerPartida();
		initModoJuego();
		this.p.reset(this.f.creaReglas());
		this.p.continuarPartida();
	}

	/**
	 * Deshace el último Movimiento
	 */
	public void deshacer() {

		this.p.detenerPartida();
		this.p.undo();
		this.p.continuarPartida();
	}

	/**
	 * Acaba la Partida
	 */
	public void salir() {

		this.p.partidaAcabada();
	}

	/**
	 * Ejecuta Movimiento del Jugador
	 * 
	 * @param x
	 * @param y
	 */
	public void poner(int x, int y) {

		this.p.ejecutaMovimiento(f.creaMovimiento(x, y, p.getTurno()));
	}

	/**
	 * Cambia el juego actual
	 * 
	 * @param juego
	 * @param col
	 * @param fila
	 */
	public void cambioJuego(String juego, int col, int fila) {

		if (juego.equals("Conecta 4")) {
			this.f = new FactoriaConecta4();
			this.tipoJuego = TipoJuego.C4;
		}

		else if (juego.equals("Complica")) {
			this.f = new FactoriaComplica();
			this.tipoJuego = TipoJuego.CO;
		}

		else if (juego.equals("Reversi")) {
			this.f = new FactoriaReversi();
			this.tipoJuego = TipoJuego.RV;
		}

		else {
			this.f = new FactoriaGravity(col, fila);
			this.tipoJuego = TipoJuego.GR;
		}

		this.p.detenerPartida();
		this.p.cambioJuego(f.creaReglas(), this.tipoJuego);
		this.p.continuarPartida();
	}

	/**
	 * Cambia el Modo del jugador
	 * 
	 * @param f
	 * @param t
	 */
	public void cambiarJugador(Ficha f, TipoTurno t) {

		this.p.detenerPartida();

		if (t == TipoTurno.AUTOMATICO) {

			f.setModo(new ModoAutomatico(this));
			f.setTipoTurno(TipoTurno.AUTOMATICO);

		} else {

			f.setModo(new ModoHumano());
			f.setTipoTurno(TipoTurno.HUMANO);
		}
		
		String s = f + " ha cambiado el tipo de jugador a " + t;
		
		this.p.actualizarLog(s);

		this.p.continuarPartida();
	}

	/**
	 * Ejecuta un Movimiento aleatorio
	 */
	public void ponerAleatorio() {

		this.p.ejecutaMovimientoAleatorio(f.creaJugadorAleatorio());
	}
}
