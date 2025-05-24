package org.example.Dato.Jugadores;


import java.util.List;

public abstract class Jugador implements IJugable {

	public Jugador() throws Exception {}

	public abstract List<Integer> seleccionarCasilla();

	public abstract String getId();

	public abstract boolean decisionHabilidad();

	public abstract int pedirFila();

	public abstract boolean existeEmail(String email);

	public abstract boolean comprobarEmailContrasenia(String email, String contraseña);

}