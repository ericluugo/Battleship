package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Vistas.IVistaPartida;

import java.util.List;

public class Submarino extends Barco {

	private int numReparacionesDisponibles;
	private final int NUM_REPARACIONES_DISPONIBLES = 1;
	
	public Submarino(String nombre, int numCasillas, Tablero suTablero, List<Casilla> casillas) {
		super(nombre, numCasillas, suTablero,casillas);
		this.numReparacionesDisponibles = NUM_REPARACIONES_DISPONIBLES;
	}

	@Override
	public void habilidad(Tablero tableroEnemigo, IVistaPartida vistaPartida) {
		for (Casilla casilla : getCasillas()){
			casilla.setEstadoImpactado(false);
			casilla.setEstadoVisibilidad(false);
		}
		vistaPartida.imprimirReparacion();
	}

	@Override
	public boolean habilidadDisponible() {
		if (numReparacionesDisponibles > 0){
			numReparacionesDisponibles--;
			return true;
		}else return false;
	}
}
