package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.ReglasComplica;
import tp.pr5.logica.ReglasJuego;

/**
 * Clase Factoria Complica
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class FactoriaComplica implements FactoriaTipoJuego {
	
	public FactoriaComplica() {

	}

	@Override
	public ReglasJuego creaReglas() {
		
		return new ReglasComplica();
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		
		return new MovimientoComplica(col, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in) {
		
		return new JugadorHumanoComplica(this, in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		
		return new JugadorAleatorioComplica(this);
	}

}
