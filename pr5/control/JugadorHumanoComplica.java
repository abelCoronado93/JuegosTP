package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

/**
 * Clase Jugador Humano Complica
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class JugadorHumanoComplica implements Jugador {

	private Scanner in;
	private FactoriaTipoJuego f;

	public JugadorHumanoComplica(FactoriaTipoJuego f, java.util.Scanner in) {
		
		this.f = f;
		this.in = in;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) throws NumberFormatException {

		String columnaUser; int filaUser = 0;

		System.out.print("Introduce la columna: ");
		columnaUser = in.nextLine();
		int col = Integer.parseInt(columnaUser);

		return f.creaMovimiento(col, filaUser, color);
	}
}
