package tp.pr5.logica;

import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;

/**
 * Esta clase define el enumerado TipoJuego
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public enum TipoJuego {

	C4, CO, GR, RV;

	/**
	 * Define las reglas de juego
	 * 
	 * @param param
	 *            -nombre del juego que elige el user
	 * 
	 * @return juego elegido
	 * @throws MovimientoInvalido 
	 */
	public static FactoriaTipoJuego fromParam(String param) throws MovimientoInvalido {

		if (param.equalsIgnoreCase("c4"))
			return new FactoriaConecta4();

		else if (param.equalsIgnoreCase("co"))
			return new FactoriaComplica();
		
		else if (param.equalsIgnoreCase("rv"))
			return new FactoriaReversi();

		else if (param.indexOf("gr") == 0) {
			String[] aux = param.split(" ");

			if (aux.length == 1)
				return new FactoriaGravity(10, 10);
			
			 else if (aux.length != 3) 
				throw new MovimientoInvalido("Coordenadas incorrectas");
			
			else {
				isNumeric(aux[1]);
				isNumeric(aux[2]);
				return new FactoriaGravity(Integer.parseInt(aux[1]),
						Integer.parseInt(aux[2]));
			}

		} else 
			throw new MovimientoInvalido("Juego no reconocido");
	}

	private static void isNumeric(String cadena) throws MovimientoInvalido {
		
		try {
			Integer.parseInt(cadena);
			
		} catch (NumberFormatException nfe) {
			throw new MovimientoInvalido("Parámetros no enteros");
		}
	}
}
