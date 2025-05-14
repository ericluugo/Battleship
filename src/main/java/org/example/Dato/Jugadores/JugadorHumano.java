package org.example.Dato.Jugadores;

import org.example.Vistas.IVistaPartida;

import java.util.List;


public class JugadorHumano extends Jugador {
	
	private String email;
	private String contrasenia;
	private boolean esAdmin;
	public JugadorHumano(String nombre, String email, String contrasenia, boolean esAdmin) {
		super(nombre);
		this.email = email;
		this.contrasenia = contrasenia;
		this.esAdmin = esAdmin;
	}
	@Override
	public List<Integer> seleccionarCasilla(IVistaPartida vistaPartida) {
		return vistaPartida.pedirCasilla();
	}
	@Override
	public String getId() {
		return email;
	}

	@Override
	public boolean decisionHabilidad(IVistaPartida vistaPartida) {
		return vistaPartida.pedirDecision();
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public boolean isEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	
	@Override
	public boolean existeEmail(String email){
		return this.email.equals(email);
	}
	@Override
	public boolean comprobarEmailContrasenia(String email, String contrasenia){
        return this.email.equals(email) && this.contrasenia.equals(contrasenia);
	}

}
