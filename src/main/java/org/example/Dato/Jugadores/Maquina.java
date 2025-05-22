package org.example.Dato.Jugadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Maquina extends Jugador {
	private String nombreMaquina;
	public Maquina(String nombre) throws Exception {
		this.nombreMaquina = nombre;
	}

	@Override
	public List<Integer> seleccionarCasilla() {
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
		return nombreMaquina;
	}

	@Override
	public boolean decisionHabilidad() {
		return true;
	}

	public String getNombreMaquina() {
		return nombreMaquina;
	}

	public void setNombreMaquina(String nombreMaquina) {
		this.nombreMaquina = nombreMaquina;
	}
}
