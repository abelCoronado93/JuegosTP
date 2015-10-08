package tp.pr5.logica;

/**
 * Esta clase define las Reglas del juego Conecta4
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class ReglasConecta4 implements ReglasJuego {

	/**
	 * Constante con el ancho del tablero
	 */
	private final static int ANCHO = 7;

	/**
	 * Constante con el alto del tablero
	 */
	private final static int ALTO = 6;

	public ReglasConecta4() {

	}

	@Override
	public Tablero iniciaTablero() {

		return new Tablero(ANCHO, ALTO);
	}

	@Override
	public Ficha jugadorInicial() {

		return Ficha.BLANCA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {

		if (UtilidadesConecta4.conectAll(ultimoMovimiento, t))
			return ultimoMovimiento.color;

		else
			return Ficha.VACIA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {

		if (tableroLleno(t))
			return true;

		return false;
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

	/**
	 * Comprueba el estado del tablero
	 * 
	 * @return true: si el tablero esta lleno/ false: si no lo esta
	 */
	public boolean tableroLleno(Tablero t) {

		boolean lleno = true;
		int i = 1;

		while (i <= t.getAncho() && lleno) {

			if (t.getCasilla(i, 1) != Ficha.VACIA)
				i++;

			else
				lleno = false;
		}
		return lleno;
	}
}
