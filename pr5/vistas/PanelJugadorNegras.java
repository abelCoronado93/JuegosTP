package tp.pr5.vistas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observer;
import tp.pr5.control.TipoTurno;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/**
 * Panel ComboBox para gestionar jugador de negras
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class PanelJugadorNegras extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Componentes gráficos
	 */
	private JComboBox<String> _negras;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;

	public PanelJugadorNegras(ControladorGui control) {

		this.c = control;

		initGUI();

		c.addObserver(this);

		confEventos();
	}

	private void initGUI() {

		String[] listaJugadores = { "Humano", "Automático" };

		this._negras = new JComboBox<String>(listaJugadores);

		JLabel _eNegras = new JLabel("Jugador de negras ");

		this.setLayout(new GridLayout(1, 2));

		this.add(_eNegras);
		this.add(this._negras);
	}

	private void confEventos() {

		this._negras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String command = (String) _negras.getSelectedItem();

				if (command.equalsIgnoreCase("Automático"))
					c.cambiarJugador(Ficha.NEGRA, TipoTurno.AUTOMATICO);

				else if (command.equalsIgnoreCase("Humano"))
					c.cambiarJugador(Ficha.NEGRA, TipoTurno.HUMANO);
			}

		});
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_negras.setEnabled(true);
				_negras.setSelectedItem("Humano");
			}
		});
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

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_negras.setEnabled(false);
			}
		});
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno,
			TipoJuego tipo) {

	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego tipo) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_negras.setEnabled(true);
				_negras.setSelectedItem("Humano");
			}
		});
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
