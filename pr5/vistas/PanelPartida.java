package tp.pr5.vistas;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
 * 
 * Clase Panel Partida
 * 
 * @author Abel Coronado y María Castañeda
 *
 */
public class PanelPartida extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Componentes gráficos
	 */
	private JButton _bUndo;
	private JButton _bReset;
	private Container _panelPrincipal;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;

	/**
	 * Constructor del panel partida
	 * 
	 * @param control
	 */
	public PanelPartida(ControladorGui control, Container _panelP) {

		this.c = control;

		this._panelPrincipal = _panelP;

		initGUI();

		c.addObserver(this);

		confEventos();
	}

	/**
	 * Configuración del ActionListener
	 */
	private void confEventos() {

		this._bUndo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				c.deshacer();
			}
		});

		this._bReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int confirmado = JOptionPane.showConfirmDialog(_panelPrincipal,
						"¿De verdad desea reiniciar?", "Aviso",
						JOptionPane.YES_NO_OPTION);

				if (JOptionPane.YES_OPTION == confirmado)
					c.reiniciar();
			}
		});
	}

	/**
	 * Inicialización de componentes
	 */
	private void initGUI() {

		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder("Partida"));

		// ------Boton Undo
		this._bUndo = new JButton("Deshacer");
		Icon undoic = new ImageIcon(getClass()
				.getResource("resources/Undo.png"));
		this._bUndo.setIcon(undoic);
		this._bUndo.setMargin(new Insets(10, 25, 10, 25));
		this._bUndo.setEnabled(false);

		// ------Boton Reset
		this._bReset = new JButton("Reiniciar");
		Icon desic = new ImageIcon(getClass()
				.getResource("resources/Reset.png"));
		this._bReset.setIcon(desic);
		this._bReset.setMargin(new Insets(10, 25, 10, 25));

		// ------Adds
		this.add(_bUndo);
		this.add(_bReset);
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_bUndo.setEnabled(false);
			}
		});
	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, final boolean hayMas,
			TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_bUndo.setEnabled(hayMas);
			}
		});
	}

	@Override
	public void onUndoNotPosible(TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onPartidaTerminada(final TableroInmutable tab,
			final Ficha ganador, final TipoJuego tipo, final Ficha turno) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_bUndo.setEnabled(false);
			}
		});
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, final Ficha turno,
			TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				if (turno.getTipoTurno() == TipoTurno.HUMANO)
					_bUndo.setEnabled(true);
			}
		});
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException,
			TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_bUndo.setEnabled(false);
			}
		});
	}

	@Override
	public void onSalir() {

	}

	@Override
	public void onAyuda(TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onCambioJugador(TableroInmutable tablero, Ficha turno) {

	}

	@Override
	public void onComandoIncorrecto(TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onNumberFormatException(TableroInmutable tablero, Ficha turno) {

	}

	@Override
	public void onMovimientoStart(final TableroInmutable tab,
			final Ficha turno, final TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				if (turno.getTipoTurno() == TipoTurno.AUTOMATICO)
					_bUndo.setEnabled(false);
			}
		});
	}

	@Override
	public void onLog(String s) {

	}
}
