package tp.pr5.logica;

/**
 * Esta clase define las coordenadas de las Fichas volteadas en Reversi.
 * Facilita la implementación del undo
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class FichasVolteadas {

	private int col, fil;

	public FichasVolteadas(int col, int fil) {

		this.col = col;
		this.fil = fil;
	}

	/**
	 * Devuelve la columna
	 * 
	 * @return x
	 */
	public int getCol() {

		return this.col;
	}

	/**
	 * Devuelve la fila
	 * 
	 * @return y
	 */
	public int getFil() {

		return this.fil;
	}
}
