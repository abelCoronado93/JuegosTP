package tp.pr5.logica;

/**
 * Interface TableroInmutable
 * 
 * @author Abel Coronado y Maria Castañeda
 *
*/
public interface TableroInmutable {
	
	/**
	 * Devuelve el alto del Tablero
	 * 
	 * @return int
	 */
	public abstract int getAlto();
	
	/**
	 * Devuelve el ancho del Tablero
	 * 
	 * @return int
	 */
	public abstract int getAncho();
	
	/**
	 * Devuelve el color de una posicion dada
	 * 
	 * @param col
	 * @param fila
	 * @return int
	 */
	public abstract Ficha getCasilla(int col, int fila);
}
