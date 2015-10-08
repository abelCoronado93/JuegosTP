package tp.pr5.logica;

/**
 * Clase que administra excepciones
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class MovimientoInvalido extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor sin parámetros
	 */
	public MovimientoInvalido() {
		super();
	}

	/**
	 * Constructor para mensaje
	 * @param msg
	 */
	public MovimientoInvalido(java.lang.String msg) {
		super(msg);
	}

	/**
	 * Constructor para mensaje y otro para causa
	 * @param msg
	 * @param arg
	 */
	public MovimientoInvalido(java.lang.String msg, java.lang.Throwable arg) {
		super(msg, arg);
	}

	/**
	 * Constructor para la causa inicial que provocó la excepción
	 * @param arg
	 */
	public MovimientoInvalido(java.lang.Throwable arg) {
		super(arg);
	}

}
