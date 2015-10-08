package tp.pr5.logica;

/**
 * Movimiento para el juego Complica
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class MovimientoComplica extends Movimiento {

	private Ficha colorPerdido;

	public MovimientoComplica(int donde, Ficha color) {
		
		this.donde = donde;
		this.color = color;
		this.colorPerdido = Ficha.VACIA;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como parámetro
	 * 
	 * @param tab
	 *            - Tablero en donde se deshace un Movimiento
	 */
	@Override
	public void undo(Tablero tab) {

		if (this.colorPerdido == Ficha.VACIA) {
			int stackFila = UtilidadesConecta4.numFila(this.donde, tab);
			tab.setCasilla(this.donde, stackFila, Ficha.VACIA);
			
		} else {
			desplazarArriba(tab);
			tab.setCasilla(this.donde, tab.getAlto(), colorPerdido);
		}
	}

	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro
	 * 
	 * @param tab Tablero sobre el que se ejecuta un Movimiento
	 * 
	 * @throws MovimientoInvalido
	 * 
	 */
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {

		if (this.donde > tab.getAncho() || this.donde < 1)
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y "
					+ tab.getAncho() + ".");

		if (!colLlena(this.donde, tab)){

			this.colorPerdido = tab.getCasilla(this.donde, tab.getAlto());
			desplazarAbajo(tab);
		}

		introducirFicha(tab);
	}

	private void desplazarAbajo(Tablero tab) {

		for (int j = tab.getAlto(); j > 1; j--)
			tab.setCasilla(this.donde, j, tab.getCasilla(this.donde, j - 1));

		tab.setCasilla(this.donde, 1, Ficha.VACIA);
	}

	private void desplazarArriba(Tablero tab) {

		for (int j = 1; j < tab.getAlto(); j++)
			tab.setCasilla(this.donde, j, tab.getCasilla(this.donde, j + 1));
	}
	
	private boolean colLlena(int col, Tablero tab) {

		if (tab.getCasilla(col, 1) != Ficha.VACIA)
			return false;

		return true;
	}

}
