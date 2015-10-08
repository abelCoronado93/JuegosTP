package tp.pr5.vistas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/**
 * 
 * Clase Panel Derecho
 * 
 * @author Abel Coronado y María Castañeda
 *
 */
public class PanelDrch extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Componentes gráficos
	 */
	private JButton _bSalir;
	private JPanel _panelPartida;
	private JPanel _panelCambio;
	private JPanel _gestionJugadores;
	private Container _panelPrincipal;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;

	/**
	 * Tipo de juego
	 */
	private TipoJuego tipoJuego;

	/**
	 * Constructor del panel derecho
	 * 
	 * @param control
	 */
	public PanelDrch(ControladorGui control, Container _panelP, TipoJuego tipo) {

		this.tipoJuego = tipo;

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

		this._bSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int confirmado = JOptionPane.showConfirmDialog(_panelPrincipal,
						"¿De verdad desea salir?", "Aviso",
						JOptionPane.YES_NO_OPTION);

				if (JOptionPane.YES_OPTION == confirmado)
					c.salir();
			}
		});
	}

	/**
	 * Inicialización de componentes
	 */
	private void initGUI() {
		this.setLayout(new BorderLayout());

		JPanel _controlador = new JPanel();
		_controlador.setLayout(new BorderLayout());

		// ------Panel Salir
		JPanel _salir = new JPanel();
		this._panelPartida = new PanelPartida(c, this._panelPrincipal);

		// ------Boton Salir
		this._bSalir = new JButton("Salir");
		Icon salic = new ImageIcon(getClass()
				.getResource("resources/Power.png"));
		_bSalir.setIcon(salic);
		_bSalir.setMargin(new Insets(10, 35, 10, 35));

		_salir.add(_bSalir);

		_controlador.add(this._panelPartida, BorderLayout.NORTH);

		this._gestionJugadores = new PanelGestionJugadores(c);
		_controlador.add(this._gestionJugadores, BorderLayout.CENTER);

		this._panelCambio = new PanelCambioJuego(c, this.tipoJuego);
		_controlador.add(this._panelCambio, BorderLayout.SOUTH);

		// ------Adds
		this.add(_controlador, BorderLayout.NORTH);

		this.add(_salir, BorderLayout.SOUTH);
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
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException,
			TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

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
	}

	@Override
	public void onLog(String s) {

	}
}
