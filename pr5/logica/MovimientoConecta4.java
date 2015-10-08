package tp.pr5.logica;

/**
 * Movimiento para el juego Conecta 4
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class MovimientoConecta4 extends Movimiento {

	public MovimientoConecta4(int donde, Ficha color) {

		this.donde = donde;
		this.color = color;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como parámetro
	 * 
	 * @param tab
	 *            - Tablero en donde se deshace un Movimiento
	 */
	@Override
	public void undo(Tablero tab) {

		int stackFila = UtilidadesConecta4.numFila(this.donde, tab);

		tab.setCasilla(this.donde, stackFila, Ficha.VACIA);
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

		columnaLlena(this.donde, tab);

		if (this.donde > tab.getAncho() || this.donde <= 0)
			throw new MovimientoInvalido(
					"Columna incorrecta. Debe estar entre 1 y "
							+ tab.getAncho() + ".");

		introducirFicha(tab);
	}
}