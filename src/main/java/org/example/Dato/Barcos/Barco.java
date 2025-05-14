package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Vistas.IVistaPartida;

import java.util.List;

public abstract class Barco{
	
	private String nombre;
	private int numCasillas;
	private boolean vivo;
	private Tablero suTablero;
	private List<Casilla> casillas;
	
	public Barco(String nombre, int numCasillas, Tablero suTablero, List<Casilla> casillas) {
		this.nombre = nombre;
		this.numCasillas = numCasillas;
		this.suTablero = suTablero;
		this.casillas = casillas;
		this.vivo = true;
	}

	public abstract void habilidad(Tablero tableroEnemigo, IVistaPartida vistaPartida);

	public abstract boolean habilidadDisponible();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumCasillas() {
		return numCasillas;
	}

	public void setNumCasillas(int numCasillas) {
		this.numCasillas = numCasillas;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public Tablero getSuTablero() {
		return suTablero;
	}

	public void setSuTablero(Tablero suTablero) {
		this.suTablero = suTablero;
	}

	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}
}
