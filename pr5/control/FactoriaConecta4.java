package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.ReglasConecta4;
import tp.pr5.logica.ReglasJuego;

/**
 * Clase Factoria Conecta 4
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class FactoriaConecta4 implements FactoriaTipoJuego {

	public FactoriaConecta4() {

	}

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoConecta4(col, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in) {
		return new JugadorHumanoConecta4(this, in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4(this);
	}

}
