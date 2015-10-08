package tp.pr5.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
 * Clase Panel Tablero
 * 
 * @author Abel Coronado y María Castañeda
 *
 */
public class PanelTablero extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Componentes gráficos
	 */
	private JPanel _tablero;
	private CasillaBtn[][] tablero;
	private JLabel _jugador;
	private JFrame _principal;
	private JPanel _marcadorPanel;
	private JLabel _fichasBlancas;
	private JLabel _fichasNegras;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;
	
	/**
	 * Contador fichas para RV
	 */
	private int numBlanc, numNegr;

	/**
	 * Constructor panel tablero
	 * 
	 * @param control
	 */
	public PanelTablero(ControladorGui control, JFrame principal) {

		this.c = control;

		this._principal = principal;

		this.numBlanc = 2;

		this.numNegr = 2;

		c.addObserver(this);

		initGUI();
	}

	/**
	 * Inicialización del Tablero GUI
	 * 
	 * @param col
	 * @param fil
	 */
	private void initTablero(TableroInmutable tab, int col, int fil,
			TipoJuego tipo, Ficha turno) {

		for (int i = 0; i < fil; i++)

			for (int j = 0; j < col; j++) {

				this.tablero[j][i] = new CasillaBtn(j, i, c);
				this.tablero[j][i].actTab(tab, tipo, turno);
				this.tablero[j][i].setEnabled(true);
				this._tablero.add(tablero[j][i]);
			}
	}

	/**
	 * Inicialización de componentes
	 */
	private void initGUI() {

		this.setLayout(new BorderLayout());

		this._tablero = new JPanel();

		// ----------Etiqueta Turno
		this._jugador = new JLabel("Juegan BLANCAS");
		_jugador.setForeground(Color.BLACK);
		_jugador.setHorizontalAlignment(SwingConstants.CENTER);
		_jugador.setBackground(Color.WHITE);
		_jugador.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));

		// ----------Panel Turno
		JPanel _jugadorPanel = new JPanel();
		_jugadorPanel.add(_jugador);
		_jugadorPanel.setBounds(0, 0, 14, 14);

		// ----------Etiqueta blancas
		this._fichasBlancas = new JLabel("Blancas: " + this.numBlanc);
		this._fichasBlancas.setForeground(Color.WHITE);
		this._fichasBlancas.setHorizontalAlignment(SwingConstants.CENTER);
		this._fichasBlancas.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));

		// ----------Etiqueta negras
		this._fichasNegras = new JLabel("Negras: " + this.numNegr);
		this._fichasNegras.setForeground(Color.BLACK);
		this._fichasNegras.setHorizontalAlignment(SwingConstants.CENTER);
		this._fichasNegras.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));

		// ----------Panel Marcador
		this._marcadorPanel = new JPanel();
		this._marcadorPanel.setLayout(new GridLayout(1, 2));

		this._marcadorPanel.add(_fichasBlancas);
		this._marcadorPanel.add(_fichasNegras);
		this._marcadorPanel.setBackground(Color.BLUE);
		this._marcadorPanel.setVisible(false);

		// ---------Adds
		this.add(this._marcadorPanel, BorderLayout.NORTH);
		this.add(this._tablero, BorderLayout.CENTER);
		this.add(_jugadorPanel, BorderLayout.SOUTH);
	}

	@Override
	public void onReset(final TableroInmutable tab, final Ficha turno,
			final TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				numBlanc = 2;
				numNegr = 2;

				// ---------Grid Tablero
				_tablero.setLayout(new GridLayout(tab.getAlto(), tab.getAncho()));

				// Inicializacion tablero
				tablero = new CasillaBtn[tab.getAncho()][tab.getAlto()];

				_tablero.removeAll();
				initTablero(tab, tab.getAncho(), tab.getAlto(), tipo, turno);

				printTurnoLabel(turno);

				if (tipo == TipoJuego.RV)
					_marcadorPanel.setVisible(true);

				else
					_marcadorPanel.setVisible(false);

				_principal.pack();
				_tablero.revalidate();
				actPanelNum();
			}
		});
	}

	@Override
	public void onUndo(final TableroInmutable tab, final Ficha turno,
			boolean hayMas, final TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				numBlanc = 0;
				numNegr = 0;

				for (int i = 0; i < tab.getAncho(); i++) {

					for (int j = 0; j < tab.getAlto(); j++) {

						tablero[i][j].actTab(tab, tipo, turno);

						if (tab.getCasilla(i + 1, j + 1) == Ficha.BLANCA)
							numBlanc++;

						else if (tab.getCasilla(i + 1, j + 1) == Ficha.NEGRA)
							numNegr++;
					}
				}

				printTurnoLabel(turno);
				actPanelNum();
			}
		});
	}

	public void printTurnoLabel(Ficha turno) {

		if (turno == Ficha.BLANCA)
			this._jugador.setText("Juegan BLANCAS");

		else
			this._jugador.setText("Juegan NEGRAS");
	}

	@Override
	public void onUndoNotPosible(TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onPartidaTerminada(final TableroInmutable tab,
			final Ficha ganador, final TipoJuego tipo, final Ficha turno) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				deshabilitarTablero(tab, turno, tipo);

				if (ganador == Ficha.VACIA)
					_jugador.setText("Partida en TABLAS");

				else if (ganador == Ficha.BLANCA)
					_jugador.setText("Ganan las BLANCAS");

				else
					_jugador.setText("Ganan las NEGRAS");

				actPanelNum();
			}
		});
	}

	@Override
	public void onMovimientoEnd(final TableroInmutable tab, final Ficha turno,
			final TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				numBlanc = 0;
				numNegr = 0;
				
				for (int i = 0; i < tab.getAncho(); i++) {
					
					for (int j = 0; j < tab.getAlto(); j++) {
						
						tablero[i][j].actTab(tab, tipo, turno);
						
						if (tab.getCasilla(i + 1, j + 1) == Ficha.BLANCA)
							numBlanc++;

						else if (tab.getCasilla(i + 1, j + 1) == Ficha.NEGRA)
							numNegr++;
					}
				}
				printTurnoLabel(turno);
				actPanelNum();
			}
		});
	}

	private void actPanelNum() {

		this._fichasBlancas.setText("Blancas: " + this.numBlanc);
		this._fichasNegras.setText("Negras: " + this.numNegr);
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException,
			TableroInmutable tab, Ficha turno) {

	}

	@Override
	public void onCambioJuego(final TableroInmutable tab, final Ficha turno,
			final TipoJuego tipo) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				numBlanc = 2;
				numNegr = 2;

				setVisible(false);
				_tablero.removeAll();
				_tablero.setLayout(new GridLayout(tab.getAlto(), tab.getAncho()));

				tablero = new CasillaBtn[tab.getAncho()][tab.getAlto()];

				initTablero(tab, tab.getAncho(), tab.getAlto(), tipo, turno);

				add(_tablero);
				_tablero.revalidate();
				setVisible(true);
				_principal.pack();
				printTurnoLabel(turno);

				if (tipo == TipoJuego.RV)
					_marcadorPanel.setVisible(true);

				else
					_marcadorPanel.setVisible(false);

				actPanelNum();
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
					deshabilitarTablero(tab, turno, tipo);

				else
					habilitarTablero(tab, turno, tipo);
			}
		});
	}

	private void deshabilitarTablero(TableroInmutable tab, Ficha turno,
			TipoJuego tipo) {

		this.numBlanc = 0;
		this.numNegr = 0;

		for (int i = 0; i < tab.getAncho(); i++)

			for (int j = 0; j < tab.getAlto(); j++) {

				tablero[i][j].actTab(tab, tipo, turno);
				tablero[i][j].setEnabled(false);

				if (tab.getCasilla(i + 1, j + 1) == Ficha.BLANCA)
					numBlanc++;

				else if (tab.getCasilla(i + 1, j + 1) == Ficha.NEGRA)
					numNegr++;
			}
		_tablero.setEnabled(false);
	}

	private void habilitarTablero(TableroInmutable tab, Ficha turno,
			TipoJuego tipo) {

		this.numBlanc = 0;
		this.numNegr = 0;

		for (int i = 0; i < tab.getAncho(); i++)

			for (int j = 0; j < tab.getAlto(); j++) {

				tablero[i][j].actTab(tab, tipo, turno);
				tablero[i][j].setEnabled(true);

				if (tab.getCasilla(i + 1, j + 1) == Ficha.BLANCA)
					numBlanc++;

				else if (tab.getCasilla(i + 1, j + 1) == Ficha.NEGRA)
					numNegr++;
			}
		_tablero.setEnabled(true);
	}

	@Override
	public void onLog(String s) {

	}
}
