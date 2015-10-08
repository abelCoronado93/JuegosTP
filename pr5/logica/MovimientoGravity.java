package tp.pr5.logica;

/**
 * Movimiento para el juego Gravity
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class MovimientoGravity extends Movimiento {

	private int fila;

	public MovimientoGravity(int columna, int fila, Ficha color) {

		this.donde = columna;
		this.fila = fila;
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

		tab.setCasilla(this.donde, this.fila, Ficha.VACIA);
	}

	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro
	 * 
	 * @param tab
	 *            - Tablero sobre el que se ejecuta un Movimiento
	 * @return true: si todo fue bien/ false: si no pudo ejecutarse el
	 *         Movimiento
	 * @throws MovimientoInvalido
	 * 
	 */
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {

		if (this.donde > tab.getAncho() || this.donde < 1
				|| this.fila > tab.getAlto() || this.fila < 1)

			throw new MovimientoInvalido("Posición incorrecta.");

		if (tab.getCasilla(this.donde, this.fila) != Ficha.VACIA)

			throw new MovimientoInvalido("Casilla ocupada.");
		else
			introducirFicha(tab);

	}

	@Override
	public void introducirFicha(Tablero tab) {
		int x = compararAncho(tab);
		int y = compararAlto(tab);
		desplazarFicha(x, y, tab);
	}

	private int compararAncho(Tablero tab) {
		int anchoIzq = this.donde - 1;
		int anchoDrch = tab.getAncho() - this.donde;

		if (anchoIzq == anchoDrch)
			return 0;

		else if (anchoIzq > anchoDrch)
			return 1;

		else
			return -1;
	}

	private int compararAlto(Tablero tab) {
		int altoArriba = this.fila - 1;
		int altoAbajo = tab.getAlto() - this.fila;

		if (altoArriba == altoAbajo)
			return 0;

		else if (altoArriba > altoAbajo)
			return 1;

		else
			return -1;
	}

	private int altoMinimo(Tablero tab) {
		int altoArriba = this.fila - 1;
		int altoAbajo = tab.getAlto() - this.fila;

		if (altoArriba <= altoAbajo)
			return altoArriba;

		else
			return altoAbajo;
	}

	private int anchoMinimo(Tablero tab) {
		int anchoIzq = this.donde - 1;
		int anchoDrch = tab.getAncho() - this.donde;

		if (anchoIzq <= anchoDrch)
			return anchoIzq;

		else
			return anchoDrch;
	}

	private void desplazarFicha(int x, int y, Tablero tab) {
		if (x != 0 && y != 0) {
			int altoMinimo = altoMinimo(tab);
			int anchoMinimo = anchoMinimo(tab);

			if (altoMinimo < anchoMinimo)
				x = 0;
			else if (altoMinimo > anchoMinimo)
				y = 0;
		}
		if (x == 1 && y == -1) {
			desplazarDArribaDrcha(tab);
		} else if (x == -1 && y == 1) {
			desplazarDAbajoIzq(tab);
		} else if (x == -1 && y == -1) {
			desplazarDArribaIzq(tab);
		} else if (x == 1 && y == 1) {
			desplazarDAbajoDrcha(tab);
		} else if (x == 1 && y == 0) {
			desplazarDerecha(tab);
		} else if (x == -1 && y == 0) {
			desplazarIzquierda(tab);
		} else if (x == 0 && y == -1) {
			desplazarArriba(tab);
		} else if (x == 0 && y == 1) {
			desplazarAbajo(tab);
		} else {
			tab.setCasilla(this.donde, this.fila, this.color);
		}
	}

	private void desplazarAbajo(Tablero tab) {

		int i = this.fila;
		boolean encontrado = false;

		while (i <= tab.getAlto() && !encontrado) {

			if (i == tab.getAlto()
					&& tab.getCasilla(this.donde, i) == Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(this.donde, i, this.color);
			} else if (tab.getCasilla(this.donde, i) != Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(this.donde, --i, this.color); //--i para que se actualice antes
			} else
				i++;
		}
		this.fila = i;
	}

	private void desplazarArriba(Tablero tab) {

		int i = this.fila;
		boolean encontrado = false;

		while (i > 0 && !encontrado) {

			if (i == 1 && tab.getCasilla(this.donde, i) == Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(this.donde, i, this.color);
			} else if (tab.getCasilla(this.donde, i) != Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(this.donde, ++i, this.color);
			} else
				i--;
		}
		this.fila = i;
	}

	private void desplazarDerecha(Tablero tab) {

		int i = this.donde;
		boolean encontrado = false;

		while (i <= tab.getAncho() && !encontrado) {

			if (i == tab.getAncho()
					&& tab.getCasilla(i, this.fila) == Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(i, this.fila, this.color);
			} else if (tab.getCasilla(i, this.fila) != Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(--i, this.fila, this.color);
			} else
				i++;
		}
		this.donde = i;
	}

	private void desplazarIzquierda(Tablero tab) {

		int i = this.donde;
		boolean encontrado = false;

		while (i >= 1 && !encontrado) {

			if (i == 1 && tab.getCasilla(i, this.fila) == Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(i, this.fila, this.color);
			} else if (tab.getCasilla(i, this.fila) != Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(++i, this.fila, this.color);
			} else
				i--;
		}
		this.donde = i;
	}

	private void desplazarDArribaDrcha(Tablero tab) { //(+1,-1)
		int i = this.donde;
		int j = this.fila;

		boolean encontrado = false;

		while (i <= tab.getAncho() && !encontrado) {

			if (i == tab.getAncho() && tab.getCasilla(i, j) == Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(i, j, this.color);
			} else if (tab.getCasilla(i, j) != Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(--i, ++j, this.color);
			} else {
				i++;
				j--;
			}
		}
		this.donde = i;
		this.fila = j;

	}

	private void desplazarDArribaIzq(Tablero tab) { //(-1,-1)
		int i = this.donde;
		int j = this.fila;

		boolean encontrado = false;

		while (i >= 1 && !encontrado) {

			if (i == 1 && tab.getCasilla(i, j) == Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(i, j, this.color);
			} else if (tab.getCasilla(i, j) != Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(++i, ++j, this.color);
			} else {
				i--;
				j--;
			}
		}
		this.donde = i;
		this.fila = j;

	}

	private void desplazarDAbajoDrcha(Tablero tab) { //(+1, +1)
		int i = this.donde;
		int j = this.fila;

		boolean encontrado = false;

		while (i <= tab.getAncho() && !encontrado) {

			if (i == tab.getAncho() && tab.getCasilla(i, j) == Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(i, j, this.color);
			} else if (tab.getCasilla(i, j) != Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(--i, --j, this.color);
			} else {
				i++;
				j++;
			}
		}
		this.donde = i;
		this.fila = j;
	}

	private void desplazarDAbajoIzq(Tablero tab) { //(-1, +1)
		int i = this.donde;
		int j = this.fila;

		boolean encontrado = false;

		while (i >= 1 && !encontrado) {

			if (i == 1 && tab.getCasilla(i, j) == Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(i, j, this.color);
			} else if (tab.getCasilla(i, j) != Ficha.VACIA) {
				encontrado = true;
				tab.setCasilla(++i, --j, this.color);
			} else {
				i--;
				j++;
			}
		}
		this.donde = i;
		this.fila = j;

	}
}
