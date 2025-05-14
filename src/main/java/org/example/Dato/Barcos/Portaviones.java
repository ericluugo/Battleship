package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Vistas.IVistaPartida;

import java.util.List;

public class Portaviones extends Barco {

	
	public Portaviones(String nombre, int numCasillas, Tablero suTablero, List<Casilla> casillas) {
		super(nombre, numCasillas, suTablero, casillas);
	}



	@Override
	public void habilidad(Tablero tableroEnemigo, IVistaPartida vistaPartida) {
		List<Integer> coordenadas = vistaPartida.pedirCasilla();
		Barco atacado = tableroEnemigo.recibirCoordenadas(coordenadas);
		vistaPartida.imprimirResultadoContraataque(atacado);
	}

	@Override
	public boolean habilidadDisponible() {
		return true;
	}
}
