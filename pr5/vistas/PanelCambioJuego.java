package tp.pr5.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/**
 * 
 * Clase Panel de Juego
 * 
 * @author Abel Coronado y María Castañeda
 *
 */
public class PanelCambioJuego extends JPanel implements ItemListener, Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Componentes gráficos
	 */
	private JComboBox<String> _tipoJuego;
	private JPanel _dimGravity;
	private JTextField _dimFilas, _dimColumnas;
	private JButton _bCambiar;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;

	/**
	 * Tipo de juego
	 */
	private TipoJuego tipoJuego;

	/**
	 * Constructor Panel cambio de juego
	 * 
	 * @param control
	 */
	public PanelCambioJuego(ControladorGui control, TipoJuego tipo) {

		this.tipoJuego = tipo;

		this.c = control;
		initGUI();

		c.addObserver(this);

		confEventos();
	}

	/**
	 * Configuración del ActionListener
	 */
	private void confEventos() {

		this._bCambiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String juego = (String) _tipoJuego.getSelectedItem();

				if (juego.equals("Conecta 4") || juego.equals("Complica")
						|| juego.equals("Reversi"))
					c.cambioJuego(juego, 0, 0);

				else if (juego.equals("Gravity")) {

					String fila = _dimFilas.getText();
					String col = _dimColumnas.getText();

					try {
						isNumeric(col);
						isNumeric(fila);
						c.cambioJuego(juego, Integer.parseInt(col),
								Integer.parseInt(fila));
					} catch (MovimientoInvalido e) {
						JOptionPane.showMessageDialog(_dimGravity,
								e.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	/**
	 * Comprobación numérica
	 * 
	 * @param cadena
	 * @throws MovimientoInvalido
	 */
	private static void isNumeric(String cadena) throws MovimientoInvalido {

		try {
			Integer.parseInt(cadena);

		} catch (NumberFormatException nfe) {
			throw new MovimientoInvalido("Parámetros no enteros");
		}
	}

	/**
	 * Inicialización de los componenetes del JPanel
	 */
	private void initGUI() {

		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Cambio de juego"));
		this.setSize(100, 100);

		// ---------Lista de juegos disponibles
		String[] listaJuegos = { "Conecta 4", "Complica", "Gravity", "Reversi" };
		this._tipoJuego = new JComboBox<String>(listaJuegos);

		this._tipoJuego.addItemListener(this);

		// ---------Boton Aceptar
		this._bCambiar = new JButton("Cambiar");
		Icon aceptic = new ImageIcon(getClass().getResource(
				"resources/Done.png"));
		_bCambiar.setIcon(aceptic);
		_bCambiar.setMargin(new Insets(10, 25, 10, 25));

		// ---------Panel Aceptar
		JPanel _aceptar = new JPanel();
		_aceptar.add(_bCambiar);

		// ---------Texto Dimensiones
		this._dimFilas = new JTextField(3);
		this._dimColumnas = new JTextField(3);

		// ---------Labels Dimensiones
		JLabel _eFila = new JLabel("Filas");
		JLabel _eCol = new JLabel("Columnas");

		// ---------Panel Dimensiones Gravity
		this._dimGravity = new JPanel();
		this._dimGravity.setLayout(new FlowLayout());
		this._dimGravity.add(_eFila);
		this._dimGravity.add(_dimFilas);
		this._dimGravity.add(_eCol);
		this._dimGravity.add(_dimColumnas);
		this._dimGravity.setVisible(false);

		cambiarItemCombo();

		// ---------Adds
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(2, 1));
		jp.add(_tipoJuego);
		jp.add(_dimGravity);
		this.add(jp, BorderLayout.NORTH);
		this.add(_aceptar, BorderLayout.CENTER);
	}

	private void cambiarItemCombo() {

		if (this.tipoJuego == TipoJuego.C4)
			this._tipoJuego.setSelectedItem("Conecta 4");

		else if (this.tipoJuego == TipoJuego.RV)
			this._tipoJuego.setSelectedItem("Reversi");

		else if (this.tipoJuego == TipoJuego.CO)
			this._tipoJuego.setSelectedItem("Complica");

		else if (this.tipoJuego == TipoJuego.GR) {
			this._tipoJuego.setSelectedItem("Gravity");
			this._dimGravity.setVisible(true);
		}
	}

	/**
	 * Muestra los JTextField del Gravity [Filas / Columnas]
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {

		if (e.getSource() == _tipoJuego)

			cambiarVisibilidadDimGR();
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego tipo) {

		this.tipoJuego = tipo;

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_bCambiar.setEnabled(true);
				_tipoJuego.setEnabled(true);
				cambiarItemCombo();
				cambiarVisibilidadDimGR();
			}
		});
	}

	private void cambiarVisibilidadDimGR() {

		String c = (String) _tipoJuego.getSelectedItem();

		if (c.equalsIgnoreCase("Gravity"))
			this._dimGravity.setVisible(true);

		else
			this._dimGravity.setVisible(false);
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

		this.tipoJuego = tipo;
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				_bCambiar.setEnabled(false);
				_tipoJuego.setEnabled(false);
				_dimGravity.setVisible(false);
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
