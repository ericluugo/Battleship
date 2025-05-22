package org.example.Dato.Jugadores;


public abstract class Jugador implements IJugable {
	
	private String nombre;

	public Jugador() throws Exception{
	}

	public String getNombre() {
		return nombre;
	}

	//Comprobar si esto se puede hacer????? aqui no, en el controlador si :)
	public boolean existeEmail(String email){
		return false;
	}
	public boolean comprobarEmailContrasenia(String email, String contraseña){
		return false;
	}

	
	
}
