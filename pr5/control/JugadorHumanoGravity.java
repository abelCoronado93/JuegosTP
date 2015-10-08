package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

/**
 * Clase Jugador Humano Gravity
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class JugadorHumanoGravity implements Jugador {

	private Scanner in;
	private FactoriaTipoJuego f;

	public JugadorHumanoGravity(FactoriaTipoJuego f, java.util.Scanner in) {

		this.f = f;
		this.in = in;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) throws NumberFormatException{

		System.out.print("Introduce la columna: ");
		String columna = in.nextLine();
		int columnaUser = Integer.parseInt(columna);

		System.out.print("Introduce la fila: ");
		String filaUser = in.nextLine();
		int fila = Integer.parseInt(filaUser);

		return f.creaMovimiento(columnaUser, fila, color);
	}
}
