package tp.pr5.vistas;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

public class PanelLog extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Componentes gráficos
	 */
	private JList<String> lista;
	private DefaultListModel<String> modelo;

	/**
	 * ControladorGui
	 * 
	 * @param c
	 */
	private ControladorGui control;

	public PanelLog(ControladorGui c) {

		this.control = c;

		control.addObserver(this);

		initGUI();
	}

	private void initGUI() {

		this.setLayout(new GridLayout(1, 1));

		// ---------ListaLog
		this.modelo = new DefaultListModel<String>();
		this.lista = new JList<String>(this.modelo);

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(this.lista);

		// ---------Adds
		this.add(scroll);
	}

	public void limpiaLog(){
		
		this.modelo.clear();
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

		this.modelo.add(0, s);
	}
}
