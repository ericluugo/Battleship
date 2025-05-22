package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Logica.ControladorPartida;

import java.util.List;

public class Portaviones extends Barco {

	
	public Portaviones(String nombre, int numCasillas, List<Casilla> casillas) {
		super(nombre, numCasillas, casillas);
	}



	@Override
	public void habilidad(Tablero tableroEnemigo) {
		List<Integer> coordenadas;
		if (ControladorPartida.getInstancia().getPartidaJugable().isTurno()) {
			coordenadas = ControladorPartida.getInstancia().getPartidaJugable().getJugador2().seleccionarCasilla();
		}else coordenadas = ControladorPartida.getInstancia().getPartidaJugable().getJugador1().seleccionarCasilla();
		Barco atacado = tableroEnemigo.recibirCoordenadas(coordenadas);
		ControladorPartida.getInstancia().getVistaPartida().imprimir("Se ha atacado la casilla : ["+coordenadas.get(1)+"]["+coordenadas.get(0)+"]");
		atacado.isBarcoMuerto();
		if (atacado == null){
			ControladorPartida.getInstancia().getVistaPartida().imprimir("El ataque ha fallado");
		}else ControladorPartida.getInstancia().getVistaPartida().imprimir("El ataque ha acertado");
	}

	@Override
	public boolean habilidadDisponible() {
		return true;
	}
}
