package tp.pr5.logica;

/**
 * Esta clase define las Reglas del juego Reversi
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class ReglasReversi implements ReglasJuego {

	/**
	 * Constante con el ancho del tablero
	 */
	private final static int ANCHO = 8;

	/**
	 * Constante con el alto del tablero
	 */
	private final static int ALTO = 8;

	public ReglasReversi() {

	}

	@Override
	public Tablero iniciaTablero() {

		Tablero tablero = new Tablero(ANCHO, ALTO);

		tablero.setCasilla(4, 4, Ficha.BLANCA);
		tablero.setCasilla(5, 4, Ficha.NEGRA);
		tablero.setCasilla(4, 5, Ficha.NEGRA);
		tablero.setCasilla(5, 5, Ficha.BLANCA);

		return tablero;
	}

	@Override
	public Ficha jugadorInicial() {

		return Ficha.NEGRA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {

		Ficha colorGanador = UtilidadesReversi.hayGanador(t, ultimoMovimiento);
		return colorGanador;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {

		if (UtilidadesReversi.hayTablas(t))
			return true;

		return false;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {

		if (ultimoEnPoner == Ficha.BLANCA) {

			if (UtilidadesReversi.puedeMover(t, Ficha.NEGRA))
				return Ficha.NEGRA;
			
			else
				return ultimoEnPoner;
		}

		else if (ultimoEnPoner == Ficha.NEGRA) {

			if (UtilidadesReversi.puedeMover(t, Ficha.BLANCA))
				return Ficha.BLANCA;
			
			else
				return ultimoEnPoner;
		}

		else
			return Ficha.VACIA;
	}

}
