package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Vistas.IVistaPartida;

import java.util.Arrays;
import java.util.List;

public class Acorazado extends Barco {

	private int numAtaquesDisponibles;
	private final int NUM_ATAQUE_MAX = 2;

	public Acorazado(String nombre, int numCasillas, Tablero suTablero, List<Casilla> casillas) {
		super(nombre, numCasillas, suTablero, casillas);
		this.numAtaquesDisponibles = NUM_ATAQUE_MAX;
	}

	@Override
	public void habilidad(Tablero tableroEnemigo, IVistaPartida vistaPartida) {
		List<Integer> coordenadas = vistaPartida.pedirCasilla();
		int fila = coordenadas.get(0);
		int columna = coordenadas.get(1);
		List<Integer> coordenadaSuperior = Arrays.asList(fila, columna + 1);
		List<Integer> coordenadaInferior = Arrays.asList(fila, columna - 1);
		List<Integer> coordenadaIzquierda = Arrays.asList(fila - 1, columna);
		List<Integer> coordenadaDerecha = Arrays.asList(fila + 1, columna);
		List<List<Integer>> coordenadasVecinas = Arrays.asList(
				coordenadas,
				coordenadaSuperior,
				coordenadaInferior,
				coordenadaIzquierda,
				coordenadaDerecha
		);
		for(int index = 0; index<coordenadasVecinas.size();index++){
			List<Integer> coordenadaActual = coordenadasVecinas.get(index);
			if ((coordenadaActual.get(0)>= 0 && coordenadaActual.get(0)<=9) && (coordenadaActual.get(1)>= 0 && coordenadaActual.get(1)<=9)){
				tableroEnemigo.recibirCoordenadas(coordenadaActual);
			}
		}
	}

	@Override
	public boolean habilidadDisponible() {
		if (numAtaquesDisponibles > 0){
			numAtaquesDisponibles--;
			return true;
		}else return false;
	}
}
