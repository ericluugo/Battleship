package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Vistas.IVistaPartida;

import java.util.List;

public class Patrullero extends Barco {
	
	private int numRevelacionesDisponibles;
	private final int NUM_REVELACIONES_DISPONIBLES = 1;

	public Patrullero(String nombre, int numCasillas, Tablero suTablero, List<Casilla> casillas) {
		super(nombre, numCasillas, suTablero,casillas);
		this.numRevelacionesDisponibles = NUM_REVELACIONES_DISPONIBLES;
	}

	@Override
	public void habilidad(Tablero tableroEnemigo, IVistaPartida vistaPartida) {
		int fila = vistaPartida.pedirFila();
		for (int j = 0; j<tableroEnemigo.getNumColumnas();j++){
			getSuTablero().getTablero()[fila][j].setEstadoVisibilidad(true);
		}
		vistaPartida.imprimirFilaRevelo(fila);
	}

	@Override
	public boolean habilidadDisponible() {
		if (numRevelacionesDisponibles > 0){
			numRevelacionesDisponibles--;
			return true;
		}else return false;
	}
}
