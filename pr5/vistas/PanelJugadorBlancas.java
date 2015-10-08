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
 * Panel ComboBox para gestionar jugador de blancas
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class PanelJugadorBlancas extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Componentes gráficos
	 */
	private JComboBox<String> _blancas;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;

	public PanelJugadorBlancas(ControladorGui control) {

		this.c = control;

		initGUI();

		c.addObserver(this);

		confEventos();

	}

	private void confEventos() {

		this._blancas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String command = (String) _blancas.getSelectedItem();

				if (command.equalsIgnoreCase("Automático"))
					c.cambiarJugador(Ficha.BLANCA, TipoTurno.AUTOMATICO);

				else if (command.equalsIgnoreCase("Humano"))
					c.cambiarJugador(Ficha.BLANCA, TipoTurno.HUMANO);
			}
		});
	}

	private void initGUI() {

		String[] listaJugadores = { "Humano", "Automático" };

		this._blancas = new JComboBox<String>(listaJugadores);

		JLabel _eBlancas = new JLabel("Jugador de blancas ");

		this.setLayout(new GridLayout(1, 2));

		this.add(_eBlancas);
		this.add(this._blancas);
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_blancas.setEnabled(true);
				_blancas.setSelectedItem("Humano");
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
				_blancas.setEnabled(false);
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
				_blancas.setEnabled(true);
				_blancas.setSelectedItem("Humano");
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
