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
		Integer coorY = rand.nextInt(10);
		Integer coorX = rand.nextInt(10);
		coordenadas.add(coorY);
		coordenadas.add(coorX);
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

	@Override
	public int pedirFila() {
		Random rand = new Random();
		int fila = rand.nextInt(10);
		return fila;
	}

	public String getNombreMaquina() {
		return nombreMaquina;
	}

	public void setNombreMaquina(String nombreMaquina) {
		this.nombreMaquina = nombreMaquina;
	}
}
