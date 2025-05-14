package org.example.Vistas;

import org.example.Logica.IControladorPartida;
import org.example.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class VistaPartida implements IVistaPartida {

	public VistaPartida() {}

	public void mostarPuntuaciones(IControladorPartida controladorPartida){

	}

	@Override
	public List<Integer> pedirCasilla () {
		List<Integer> coordenadas = new ArrayList<>();
		Integer coorX = Utilidades.lecturaCoordenada("Introduzca las coordenadas de la casilla deseada: " +'\n' + "Coordenada x : ");
		Integer coorY = Utilidades.lecturaCoordenada("Coordenada y : ");
		coordenadas.add(coorX);
		coordenadas.add(coorY);
		return coordenadas;
	}

	@Override
	public boolean pedirDecision() {
		boolean decision = false;
		String respuesta = Utilidades.lecturaDecision("Si desea contraatacar escriba 'Si', en caso contrario 'No'." + '\n' + "Respuesta: ");
		if (respuesta.equalsIgnoreCase("Si") || respuesta.equalsIgnoreCase("Sí")){
			decision = true;
		}
		return decision;
	}

	@Override
	public int pedirFila() {
		int fila = Utilidades.lecturaCoordenada("Introduzca la fila que quiere revelar: ");
		return fila;
	}
}
