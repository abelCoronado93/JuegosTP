package tp.pr5.logica;

/**
 * Esta clase define un Movimiento
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public abstract class Movimiento {

	/**
	 * Columna donde el user desea introducir una Ficha
	 */
	protected int donde;

	/**
	 * Color de dicha Ficha
	 */
	protected Ficha color;

	/**
	 * Deshace el movimiento en el tablero recibido como parámetro
	 * 
	 * @param tab 
	 * 			- Tablero en donde se deshace un Movimiento
	 */
	public abstract void undo(Tablero tab);

	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro
	 * 
	 * @param tab
	 * 			- Tablero sobre el que se ejecuta un Movimiento
	 * @return true: si todo fue bien/ false: si no pudo ejecutarse el Movimiento
	 * @throws MovimientoInvalido 
	 * 			
	 */
	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;

	/**
	 * Devuelve el jugador actual
	 * 
	 * @return color del jugador al que pertecene el Movimiento
	 */
	public Ficha getJugador() {
		
		return this.color;
	}

	/**
	 * Introduce la Ficha en la columna deseada por el user
	 * 
	 * @param tab
	 * 			- Tablero sobre el que se va a introducir una Ficha
	 */
	public void introducirFicha(Tablero tab) {

		int i = 1;
		boolean encontrado = false;

		while (i <= tab.getAlto() && !encontrado) {

			if (i == tab.getAlto()
					&& tab.getCasilla(this.donde, i) == Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(this.donde, i, this.color);
			} else if (tab.getCasilla(this.donde, i) != Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(this.donde, i - 1, this.color);
			} else
				i++;
		}
	}

	/**
	 * Comprueba si la columna esta llena
	 * 
	 * @param col
	 * @return true: si esta llena / false: coc
	 * @throws MovimientoInvalido 
	 */
	public void columnaLlena(int col, Tablero tab) throws MovimientoInvalido {

		if (tab.getCasilla(col, 1) != Ficha.VACIA)
			
			throw new MovimientoInvalido("Columna llena.");
	}

}
