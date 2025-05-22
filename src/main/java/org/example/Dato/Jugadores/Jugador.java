package org.example.Dato.Jugadores;


public abstract class Jugador implements IJugable {

	public Jugador() throws Exception{
	}

	//Comprobar si esto se puede hacer????? aqui no, en el controlador si :)
	public boolean existeEmail(String email){
		return false;
	}
	public boolean comprobarEmailContrasenia(String email, String contraseña){
		return false;
	}

	
	
}
