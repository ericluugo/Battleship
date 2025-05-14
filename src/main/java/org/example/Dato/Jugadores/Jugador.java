package org.example.Dato.Jugadores;

import org.example.Vistas.IVistaPartida;

import java.util.List;

public abstract class Jugador implements IJugable {
	
	private String nombre;

	public Jugador(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public  abstract List<Integer> seleccionarCasilla(IVistaPartida vistaPartida);

	@Override
	public abstract String getId();

	@Override
	public abstract boolean decisionHabilidad(IVistaPartida vistaPartida);

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//Comprobar si esto se puede hacer????? aqui no, en el controlador si :)
	public boolean existeEmail(String email){
		return false;
	}
	public boolean comprobarEmailContrasenia(String email, String contraseña){
		return false;
	}

	
	
}
