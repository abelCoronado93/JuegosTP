package tp.pr5.control;

/**
 * Modo para gestionar el tipo de jugador
 * 
 * @author Abel Coronado y Maria Castañeda
 *
*/
public interface Modo {

	/**
	 * Lanzas thread
	 */
	public abstract void comenzar();
	
	/**
	 * Detienes thread
	 */
	public abstract void terminar();
}
