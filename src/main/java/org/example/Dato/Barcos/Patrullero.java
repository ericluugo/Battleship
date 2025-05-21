package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Logica.ControladorPartida;

import java.util.List;

public class Patrullero extends Barco {
	
	private int numRevelacionesDisponibles;
	private final int NUM_REVELACIONES_DISPONIBLES = 1;

	public Patrullero(String nombre, int numCasillas, List<Casilla> casillas) {
		super(nombre, numCasillas,casillas);
		this.numRevelacionesDisponibles = NUM_REVELACIONES_DISPONIBLES;
	}

	@Override
	public void habilidad(Tablero tableroEnemigo) {
		int fila = ControladorPartida.getInstancia().getVistaAtacable().pedirFila();
		for (int j = 0; j<tableroEnemigo.getNumColumnas();j++){
			tableroEnemigo.getTablero()[fila][j].setEstadoVisibilidad(true);
		}
		ControladorPartida.getInstancia().getVistaPartida().imprimir("Se ha revelado la fila " + fila);
	}

	@Override
	public boolean habilidadDisponible() {
		if (numRevelacionesDisponibles > 0){
			numRevelacionesDisponibles--;
			return true;
		}else return false;
	}
}
