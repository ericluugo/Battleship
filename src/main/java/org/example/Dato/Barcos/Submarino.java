package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Logica.ControladorPartida;

import java.util.List;

public class Submarino extends Barco {

	private int numReparacionesDisponibles;
	private final int NUM_REPARACIONES_DISPONIBLES = 1;
	
	public Submarino(String nombre, int numCasillas, List<Casilla> casillas) {
		super(nombre, numCasillas,casillas);
		this.numReparacionesDisponibles = NUM_REPARACIONES_DISPONIBLES;
	}

	@Override
	public void habilidad(Tablero tableroEnemigo) {
		for (Casilla casilla : getCasillas()){
			casilla.setEstadoImpactado(false);
			casilla.setEstadoVisibilidad(false);
		}
		setVivo(true);
		ControladorPartida.getInstancia().getVistaPartida().imprimir("Se ha reparado el Submarino");
		numReparacionesDisponibles--;
	}

	@Override
	public boolean habilidadDisponible() {
		if (numReparacionesDisponibles > 0){
			return true;
		}else return false;
	}
}
