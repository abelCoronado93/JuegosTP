package tp.pr5.logica;

/**
 * Esta clase define las Reglas del juego Complica
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class ReglasComplica implements ReglasJuego {

	/**
	 * Constante con el ancho del tablero
	 */
	private final static int ANCHO = 4;

	/**
	 * Constante con el alto del tablero
	 */
	private final static int ALTO = 7;

	public ReglasComplica() {

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

		Ficha colorGanador = UtilidadesComplica.complicAll(t, ultimoMovimiento);

		if (colorGanador == Ficha.BLANCA)
			return Ficha.BLANCA;

		else if (colorGanador == Ficha.NEGRA)
			return Ficha.NEGRA;

		else
			return Ficha.VACIA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {

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
}
