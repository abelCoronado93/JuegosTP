package tp.pr5.vistas;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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
 * Clase Panel Izquierdo
 * 
 * @author Abel Coronado y María Castañeda
 *
 */
public class PanelIzq extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Componentes gráficos
	 */
	private JPanel _panelTablero;
	private JButton _bAleatorio;
	private JFrame _principal;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;

	/**
	 * Constructor del panel izquierdo
	 * 
	 * @param control
	 */
	public PanelIzq(ControladorGui control, JFrame principal) {

		this.c = control;

		this._principal = principal;

		initGUI();

		c.addObserver(this);

		confEventos();
	}

	/**
	 * Configuración del ActionListener
	 */
	private void confEventos() {

		this._bAleatorio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				c.ponerAleatorio();
			}
		});
	}

	/**
	 * Inicialización de componentes
	 */
	private void initGUI() {

		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this._panelTablero = new PanelTablero(c, this._principal);

		// ------Panel Aleatorio
		JPanel _aleatorio = new JPanel();

		// ------Boton Random
		_bAleatorio = new JButton("Poner Aleatorio");

		Icon aleic = new ImageIcon(getClass().getResource(
				"resources/Random.png"));
		_bAleatorio.setIcon(aleic);
		_bAleatorio.setMargin(new Insets(10, 25, 10, 25));

		_aleatorio.add(_bAleatorio);

		// ------Adds
		this.add(this._panelTablero, BorderLayout.CENTER);
		this.add(_aleatorio, BorderLayout.SOUTH);
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_bAleatorio.setEnabled(true);
				_principal.pack();
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
				_bAleatorio.setEnabled(false);
			}
		});
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno,
			TipoJuego tipo) {

	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException,
			TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

		this._principal.pack();
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
					_bAleatorio.setEnabled(false);

				else
					_bAleatorio.setEnabled(true);

			}
		});
	}

	@Override
	public void onLog(String s) {

	}
}
