package tp.pr5.logica;

/**
 * Esta clase define las Reglas del juego Gravity
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class ReglasGravity implements ReglasJuego {

	/**
	 * Constante con el ancho del tablero
	 */
	private int ancho;

	/**
	 * Constante con el alto del tablero
	 */
	private int alto;

	public ReglasGravity(int numCols, int numFilas) {

		this.ancho = numCols;
		this.alto = numFilas;
	}

	@Override
	public Tablero iniciaTablero() {

		return new Tablero(this.ancho, this.alto);
	}

	@Override
	public Ficha jugadorInicial() {

		return Ficha.BLANCA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {

		Ficha colorGanador = UtilidadesGravity.gravityAll(t, ultimoMovimiento);

		if (colorGanador == Ficha.BLANCA)
			return Ficha.BLANCA;

		else if (colorGanador == Ficha.NEGRA)
			return Ficha.NEGRA;

		else
			return Ficha.VACIA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {

		if (tableroLleno(t))
			return true;

		return false;
	}

	private boolean tableroLleno(Tablero t) {

		boolean encontrado = true;

		for (int i = 1; i <= t.getAncho() && encontrado; i++) {

			for (int j = 1; j <= t.getAlto() && encontrado; j++) {

				if (t.getCasilla(i, j) == Ficha.VACIA)

					return false;
			}
		}
		return encontrado;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {

		if (ultimoEnPoner == Ficha.BLANCA)
			return Ficha.NEGRA;

		else if (ultimoEnPoner == Ficha.NEGRA)
			return Ficha.BLANCA;

		else
			return Ficha.VACIA;
	}
}
