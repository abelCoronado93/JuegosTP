package tp.pr5.logica;

import java.util.Stack;

/**
 * Movimiento Reversi
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class MovimientoReversi extends Movimiento {

	private int fila;
	private Stack<FichasVolteadas> fichasVolteadas;

	public MovimientoReversi(int columna, int fila, Ficha color) {

		this.donde = columna;
		this.fila = fila;
		this.color = color;
		this.fichasVolteadas = new Stack<FichasVolteadas>();
	}

	@Override
	public void undo(Tablero tab) {

		Ficha rival = UtilidadesReversi.colorRival(this);

		tab.setCasilla(this.donde, this.fila, Ficha.VACIA);

		while (!this.fichasVolteadas.empty()) {
			FichasVolteadas ficha = this.fichasVolteadas
					.get(this.fichasVolteadas.size() - 1);
			tab.setCasilla(ficha.getCol(), ficha.getFil(), rival);
			this.fichasVolteadas.pop();
		}
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {

		if (this.donde > tab.getAncho() || this.donde < 1
				|| this.fila > tab.getAlto() || this.fila < 1)

			throw new MovimientoInvalido("Posición incorrecta.");

		if (tab.getCasilla(this.donde, this.fila) != Ficha.VACIA)

			throw new MovimientoInvalido("Casilla ocupada.");

		if (!posicionValida(tab))

			throw new MovimientoInvalido("Movimiento no válido");

		else
			colocarReversi(tab);
	}

	private void colocarReversi(Tablero tab) {

		tab.setCasilla(this.donde, fila, this.color);
		voltearFichas(tab);
	}

	private boolean posicionValida(Tablero tab) {

		if (!UtilidadesReversi.posicionValida(tab, this, this.fila))
			return false;

		return true;
	}

	private void voltearFichas(Tablero tab) {

		Ficha rival = UtilidadesReversi.colorRival(this);

		if (UtilidadesReversi.verticalArriba(tab, this, this.fila, rival))
			voltearVerticalArriba(tab, rival);

		if (UtilidadesReversi.verticalAbajo(tab, this, this.fila, rival))
			voltearVerticalAbajo(tab, rival);

		if (UtilidadesReversi.horizontalDerecha(tab, this, this.fila, rival))
			voltearHorizontalDerecha(tab, rival);

		if (UtilidadesReversi.horizontalIzquierda(tab, this, this.fila, rival))
			voltearHorizontalIzquierda(tab, rival);

		if (UtilidadesReversi
				.diagonalArribaDerecha(tab, this, this.fila, rival))
			voltearDiagArribaDr(tab, rival);

		if (UtilidadesReversi.diagonalArribaIzquierda(tab, this, this.fila,
				rival))
			voltearDiagArribaIz(tab, rival);

		if (UtilidadesReversi.diagonalAbajoDerecha(tab, this, this.fila, rival))
			voltearDiagAbajoDr(tab, rival);

		if (UtilidadesReversi.diagonalAbajoIzquierda(tab, this, this.fila,
				rival))
			voltearDiagAbajoIz(tab, rival);
	}

	private void voltearVerticalArriba(Tablero tab, Ficha rival) {

		boolean encontrado = false;

		for (int i = this.fila - 1; i >= 1 && !encontrado; --i) {

			if (tab.getCasilla(this.donde, i) == this.color)
				encontrado = true;

			else {
				tab.setCasilla(this.donde, i, this.color);
				this.fichasVolteadas.push(new FichasVolteadas(this.donde, i));
			}
		}
	}

	private void voltearVerticalAbajo(Tablero tab, Ficha rival) {

		boolean encontrado = false;

		for (int i = this.fila + 1; i <= tab.getAlto() && !encontrado; ++i) {

			if (tab.getCasilla(this.donde, i) == this.color)
				encontrado = true;

			else {
				tab.setCasilla(this.donde, i, this.color);
				this.fichasVolteadas.push(new FichasVolteadas(this.donde, i));
			}
		}
	}

	private void voltearHorizontalDerecha(Tablero tab, Ficha rival) {

		boolean encontrado = false;

		for (int i = this.donde + 1; i <= tab.getAncho() && !encontrado; ++i) {

			if (tab.getCasilla(i, this.fila) == this.color)
				encontrado = true;

			else {
				tab.setCasilla(i, this.fila, this.color);
				this.fichasVolteadas.push(new FichasVolteadas(i, this.fila));
			}
		}
	}

	private void voltearHorizontalIzquierda(Tablero tab, Ficha rival) {

		boolean encontrado = false;

		for (int i = this.donde - 1; i >= 1 && !encontrado; --i) {

			if (tab.getCasilla(i, this.fila) == this.color)
				encontrado = true;

			else {
				tab.setCasilla(i, this.fila, this.color);
				this.fichasVolteadas.push(new FichasVolteadas(i, this.fila));
			}
		}
	}

	private void voltearDiagArribaDr(Tablero tab, Ficha rival) {

		boolean encontrado = false;
		int i = this.donde + 1;
		int j = this.fila - 1;

		while (i <= tab.getAncho() && j >= 1 && !encontrado) {

			if (tab.getCasilla(i, j) == this.color)
				encontrado = true;

			else {
				tab.setCasilla(i, j, this.color);
				this.fichasVolteadas.push(new FichasVolteadas(i, j));
				i++;
				j--;
			}
		}
	}

	private void voltearDiagArribaIz(Tablero tab, Ficha rival) {

		boolean encontrado = false;
		int i = this.donde - 1;
		int j = this.fila - 1;

		while (i >= 1 && j >= 1 && !encontrado) {

			if (tab.getCasilla(i, j) == this.color)
				encontrado = true;

			else {
				tab.setCasilla(i, j, this.color);
				this.fichasVolteadas.push(new FichasVolteadas(i, j));
				i--;
				j--;
			}
		}
	}

	private void voltearDiagAbajoDr(Tablero tab, Ficha rival) {

		boolean encontrado = false;
		int i = this.donde + 1;
		int j = this.fila + 1;

		while (i <= tab.getAncho() && j <= tab.getAlto() && !encontrado) {

			if (tab.getCasilla(i, j) == this.color)
				encontrado = true;

			else {
				tab.setCasilla(i, j, this.color);
				this.fichasVolteadas.push(new FichasVolteadas(i, j));
				i++;
				j++;
			}
		}
	}

	private void voltearDiagAbajoIz(Tablero tab, Ficha rival) {

		boolean encontrado = false;
		int i = this.donde - 1;
		int j = this.fila + 1;

		while (i >= 1 && j <= tab.getAlto() && !encontrado) {

			if (tab.getCasilla(i, j) == this.color)
				encontrado = true;

			else {
				tab.setCasilla(i, j, this.color);
				this.fichasVolteadas.push(new FichasVolteadas(i, j));
				i--;
				j++;
			}
		}
	}
}
