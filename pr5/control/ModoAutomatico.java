package tp.pr5.control;

/**
 * Modo Automático
 * 
 * @author Abel Coronado y Maria Castañeda
 *
*/
public class ModoAutomatico implements Modo, Runnable {

	private ControladorGui c;
	private Thread t;

	public ModoAutomatico(ControladorGui controlador) {

		this.c = controlador;
	}

	@Override
	public void comenzar() {

		this.t = new Thread(this);
		this.t.start();
	}

	@Override
	public void terminar() {

		if (this.t != null)
			t.interrupt();
	}

	/**
	 * Interrumpimos thread y pone ficha aleatorio
	 */
	@Override
	public void run() {
		
		try {
			Thread.sleep(2000);
			c.ponerAleatorio();

		} catch (InterruptedException e) {	}
	}
}