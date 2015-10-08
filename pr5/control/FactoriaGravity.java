package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.ReglasGravity;
import tp.pr5.logica.ReglasJuego;

/**
 * Clase Factoria Gravity
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class FactoriaGravity implements FactoriaTipoJuego {

	private int ancho;
	private int alto;

	public FactoriaGravity(int ancho, int alto) {
		
		this.ancho = ancho;
		this.alto = alto;
	}

	public FactoriaGravity() {

	}

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasGravity(this.ancho, this.alto);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in) {
		return new JugadorHumanoGravity(this, in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity(this);
	}

}
