package tp.pr5.vistas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/**
 * Clase Vista Gráfica
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class VistaGUI extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Componentes gráficos
	 */
	private Container _panelPrincipal;
	private PanelDrch _panelDr;
	private JPanel _panelIz;
	private PanelLog _panelLog;
	private JFrame logFrame;
	private Container logPrincipal;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;

	/**
	 * Tipo de juego
	 */
	private TipoJuego tipoJuego;

	/**
	 * Constructor de la vista GUI
	 * 
	 * @param control
	 */
	public VistaGUI(ControladorGui control, TipoJuego tipo) {

		super("Práctica 4 - TP");

		this.tipoJuego = tipo;

		this.c = control;

		initGUI();

		c.start();

		c.addObserver(this);
	}

	/**
	 * Inicialización de componentes
	 */
	public void initGUI() {

		this._panelLog = new PanelLog(c);
		this._panelPrincipal = this.getContentPane();

		this._panelPrincipal.setLayout(new GridLayout(1, 2));

		this._panelIz = new PanelIzq(c, this);
		this._panelDr = new PanelDrch(c, this, tipoJuego);
		this._panelPrincipal.add(this._panelIz);
		this._panelPrincipal.add(this._panelDr);

		JMenuBar menuBar = new JMenuBar();
		JMenu logMenu = new JMenu("Log");
		JMenuItem verLog = new JMenuItem("Ver log");
		JMenuItem limpiaLog = new JMenuItem("Limpiar log");
		logMenu.add(verLog);
		logMenu.add(limpiaLog);
		menuBar.add(logMenu);
		this.setJMenuBar(menuBar);
		
		//-------LOG
		this.logFrame = new JFrame("Log");
		this.logPrincipal = logFrame.getContentPane();
		this.logPrincipal.setLayout(new BorderLayout());
		this.logPrincipal.add(_panelLog, BorderLayout.CENTER);
		logFrame.setVisible(false);
		logFrame.setLocation(750, 100);
		logFrame.setSize(250, 400);

		verLog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				logFrame.setVisible(true);
			}
		});
		
		limpiaLog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				_panelLog.limpiaLog();
			}
		});
		

		this.setLocation(100, 100);
		this.setSize(850, 550);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
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

		String win = "";

		if (ganador == Ficha.BLANCA)
			win = "Han ganado las blancas";

		else if (ganador == Ficha.NEGRA)
			win = "Han ganado las negras";

		else
			win = "Partida en tablas";

		JOptionPane.showMessageDialog(_panelPrincipal, win, "Enhorabuena",
				JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno,
			TipoJuego tipo) {

	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException,
			TableroInmutable tab, Ficha turno) {

		JOptionPane.showMessageDialog(_panelPrincipal,
				movimientoException.getMessage(), "Movimiento Incorrecto",
				JOptionPane.ERROR_MESSAGE);
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
	public void onCambioJugador(TableroInmutable tab, Ficha turno) {

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
