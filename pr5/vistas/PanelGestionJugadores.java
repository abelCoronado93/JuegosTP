package tp.pr5.vistas;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/**
 * Panel para cambiar el modo de los jugadores
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class PanelGestionJugadores extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;

	public PanelGestionJugadores(ControladorGui control) {

		this.c = control;

		initGUI();

		c.addObserver(this);
	}

	private void initGUI() {

		this.setLayout(new GridLayout(2, 1));
		this.setBorder(BorderFactory.createTitledBorder("Gestión de jugadores"));

		JPanel _panelBlancas = new PanelJugadorBlancas(this.c);
		JPanel _panelNegras = new PanelJugadorNegras(this.c);

		// --------Adds
		this.add(_panelBlancas);
		this.add(_panelNegras);

	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas,
			TipoJuego tipo) {

	}

	@Override
	public void onUndoNotPosible(TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onPartidaTerminada(final TableroInmutable tab,
			final Ficha ganador, final TipoJuego tipo, final Ficha turno) {

	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno,
			TipoJuego tipo) {

	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

	}

	@Override
	public void onAyuda(TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onSalir() {

	}

	@Override
	public void onCambioJugador(TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onComandoIncorrecto(TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException,
			TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onNumberFormatException(TableroInmutable tablero, Ficha turno) {

	}

	@Override
	public void onMovimientoStart(final TableroInmutable tab,
			final Ficha turno, final TipoJuego tipo) {
		
	}

	@Override
	public void onLog(String s) {

	}

}
