package tp.pr5.logica;

import tp.pr5.control.Modo;
import tp.pr5.control.ModoHumano;
import tp.pr5.control.TipoTurno;

/**
 * Esta clase define el enumerado Ficha
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public enum Ficha {

	VACIA(null, null), BLANCA(TipoTurno.HUMANO, new ModoHumano()), NEGRA(
			TipoTurno.HUMANO, new ModoHumano());

	/**
	 * Tipo Turno actual
	 */
	private TipoTurno tipoturno;
	

	/**
	 * Modo Jugador actual
	 */
	private Modo modojugador;

	/**
	 * Constructor Ficha
	 * 
	 * @param turno
	 * @param modo
	 */
	Ficha(TipoTurno turno, Modo modo) {

		this.tipoturno = turno;
		this.modojugador = modo;
	}

	/**
	 * Devuelve el TipoTurno de la Ficha
	 * 
	 * @return el tipo de turno
	 */
	public TipoTurno getTipoTurno() {

		return this.tipoturno;
	}

	/**
	 * Cambia el TipoTurno de la Ficha
	 * 
	 * @param turno
	 */
	public void setTipoTurno(TipoTurno turno) {

		this.tipoturno = turno;
	}
	
	/**
	 * Devuelve el Modo de la Ficha
	 * 
	 * @return Modo
	 */
	public Modo getModo() {

		return this.modojugador;
	}

	/**
	 * Cambia el Modo de la Ficha
	 * 
	 * @param modo
	 */
	public void setModo(Modo modo) {

		this.modojugador = modo;
	}
	
}