package tp.pr5.vistas;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.TipoTurno;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.UtilidadesReversi;

/**
 * 
 * Clase Botón
 * 
 * @author Abel Coronado y María Castañeda
 *
 */
public class CasillaBtn extends JButton {

	private static final long serialVersionUID = 1L;

	/**
	 * Columna
	 */
	private int x;

	/**
	 * Fila
	 */
	private int y;

	/**
	 * Controlador GUI
	 */
	private ControladorGui c;

	/**
	 * Constructor de JButton
	 * 
	 * @param x
	 * @param y
	 * @param control
	 */
	public CasillaBtn(int x, int y, ControladorGui control) {

		this.x = x;
		this.y = y;

		this.c = control;

		initGUI();

		confEventos();
	}

	/**
	 * Inicializacion del componente JButton
	 */
	private void initGUI() {

		this.setBackground(Color.ORANGE);

		this.setMargin(new Insets(10, 10, 10, 10));
	}

	/**
	 * Devuelve el valor fila [y] de un botón concreto
	 * 
	 * @return fila
	 */
	public int getFila() {

		return this.y;
	}

	/**
	 * Devuelve el valor columna [x] de un botón concreto
	 * 
	 * @return columna
	 */
	public int getColumna() {

		return this.x;
	}

	/**
	 * Configuración del ActionListener
	 */
	private void confEventos() {

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				c.poner(getColumna() + 1, getFila() + 1);
			}
		});
	}

	/**
	 * Actualiza el Tablero del GUI
	 * 
	 * @param tab
	 * @param turno
	 */
	public void actTab(TableroInmutable tab, TipoJuego tipo, Ficha turno) {

		if (tab.getCasilla(this.x + 1, this.y + 1) == Ficha.BLANCA)
			this.setBackground(Color.WHITE);

		else if (tab.getCasilla(this.x + 1, this.y + 1) == Ficha.NEGRA)
			this.setBackground(Color.BLACK);

		else if (tipo == TipoJuego.RV && turno.getTipoTurno() == TipoTurno.HUMANO) {

			MovimientoReversi m = new MovimientoReversi(this.x + 1, this.y + 1,
					turno);

			if (UtilidadesReversi.posicionValida(tab, m, this.y + 1))
				this.setBackground(Color.BLUE);
			
			else 
				this.setBackground(Color.ORANGE);
				
		}
			
		else
			this.setBackground(Color.ORANGE);
	}

}
