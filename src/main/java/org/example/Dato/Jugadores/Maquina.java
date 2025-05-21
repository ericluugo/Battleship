package org.example.Dato.Jugadores;

import org.example.Vistas.IVistaAtacable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Maquina extends Jugador {

	public Maquina(String nombre) {
		super(nombre);
	}

	@Override
	public List<Integer> seleccionarCasilla(IVistaAtacable vistaPartida) {
		List<Integer> coordenadas = new ArrayList<>();
		Random rand = new Random();
		Integer coorX = rand.nextInt(10);
		Integer coordY = rand.nextInt(10);
		coordenadas.add(coorX);
		coordenadas.add(coordY);
		return coordenadas;
	}

	@Override
	public String getId() {
		return super.getNombre();
	}

	@Override
	public boolean decisionHabilidad(IVistaAtacable vistaPartida) {
		return true;
	}
}
