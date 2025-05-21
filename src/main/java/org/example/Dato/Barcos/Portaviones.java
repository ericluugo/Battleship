package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Utilidades;
import org.example.Vistas.IVistaAtacable;

import java.util.List;

public class Portaviones extends Barco {

	
	public Portaviones(String nombre, int numCasillas, List<Casilla> casillas) {
		super(nombre, numCasillas, casillas);
	}



	@Override
	public void habilidad(Tablero tableroEnemigo, IVistaAtacable vistaPartida) {
		List<Integer> coordenadas = vistaPartida.pedirCasilla();
		Barco atacado = tableroEnemigo.recibirCoordenadas(coordenadas);
		if (atacado == null){
			Utilidades.imprimir("El ataque ha fallado");
		}else Utilidades.imprimir("El ataque ha acertado");
	}

	@Override
	public boolean habilidadDisponible() {
		return true;
	}
}
