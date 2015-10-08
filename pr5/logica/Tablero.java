package tp.pr5.logica;

/**
 * Clase Tablero
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class Tablero implements TableroInmutable {

	/**
	 * El Tablero se compone por un array bidimensional de Fichas y las
	 * dimensiones de dicho array
	 */
	private Ficha[][] Tablero;
	private int alto;
	private int ancho;

	/**
	 * Constructora de la clase tablero
	 * 
	 * @param tx
	 *            columna
	 * @param ty
	 *            fila
	 */
	public Tablero(int tx, int ty) {

		if (tx < 1 && ty < 1) {
			this.ancho = 1;
			this.alto = 1;

		} else if (tx < 1 && ty >= 1) {
			this.ancho = 1;
			this.alto = ty;

		} else if (ty < 1 && tx >= 1) {
			this.alto = 1;
			this.ancho = tx;

		} else {
			this.ancho = tx;
			this.alto = ty;
		}

		this.Tablero = new Ficha[this.ancho][this.alto];
		resetTablero();
	}

	/**
	 * Alto actual del tablero
	 * 
	 * @return alto actual
	 */
	public int getAlto() {
		return this.alto;
	}

	/**
	 * Ancho actual del tablero
	 * 
	 * @return ancho actual
	 */
	public int getAncho() {
		return this.ancho;
	}

	/**
	 * Inicializa el tablero a Ficha VACIA
	 */
	public void resetTablero() {

		for (int i = 0; i < this.ancho; i++) {

			for (int j = 0; j < this.alto; j++)

				this.Tablero[i][j] = Ficha.VACIA;
		}
	}

	/**
	 * Redefine la funcion predeterminada toString
	 */
	@Override
	public String toString() { /* Dibuja el Tablero */
		String cadena = "";
		for (int i = 0; i < this.alto; i++) {
			cadena = cadena + "|";
			for (int j = 0; j < this.ancho; j++) {
				switch (this.Tablero[j][i]) {
				case VACIA:
					cadena = cadena + " ";
					break;
				case NEGRA:
					cadena = cadena + "X";
					break;
				case BLANCA:
					cadena = cadena + "O";
					break;
				}
			}
			cadena = cadena + "|" + System.lineSeparator();
		}
		cadena = cadena + "+";
		for (int i = 0; i < this.ancho; i++)
			cadena = cadena + "-";

		cadena = cadena + "+" + System.lineSeparator() + " ";
		for (int i = 0; i < this.ancho; i++)
			cadena = cadena + (i + 1) % 10;

		cadena = cadena + System.lineSeparator();
		return cadena;
	}

	/**
	 * Obtiene la ficha en una posicion dada
	 * 
	 * @param x
	 *            -columna
	 * @param y
	 *            -fila
	 * @return el color en esa posicion
	 */
	public Ficha getCasilla(int x, int y) {

		if ((y > getAlto() || (y <= 0) || (x > getAncho()) || (x <= 0)))
			return Ficha.VACIA;

		else
			return this.Tablero[x - 1][y - 1];
	}

	/**
	 * Introduce la ficha en una posicion dada
	 * 
	 * @param x
	 *            columna
	 * @param y
	 *            fila
	 * @param color
	 *            Ficha
	 */
	public void setCasilla(int x, int y, Ficha color) {

		if ((y <= getAlto() && (y > 0) && (x <= getAncho()) && (x > 0)))

			this.Tablero[x - 1][y - 1] = color;
	}
}
