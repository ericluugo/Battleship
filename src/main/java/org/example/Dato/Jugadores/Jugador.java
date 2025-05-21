package org.example.Dato.Jugadores;

import org.example.ModelException;
import org.example.Validaciones;
import org.example.Vistas.IVistaAtacable;

import java.util.List;

public abstract class Jugador implements IJugable {
	
	private String nombre;

	public Jugador(String nombre) throws ModelException{
		setNombre(nombre);
	}

	@Override
	public  abstract List<Integer> seleccionarCasilla(IVistaAtacable vistaPartida);

	@Override
	public abstract String getId();

	@Override
	public abstract boolean decisionHabilidad(IVistaAtacable vistaPartida);

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws ModelException {
		Validaciones.checkString(nombre, "nombre");
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
