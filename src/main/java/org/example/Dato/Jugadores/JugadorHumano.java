package org.example.Dato.Jugadores;

import org.example.Logica.ControladorPartida;
import org.example.ModelException;
import org.example.Validaciones;
import org.example.Vistas.IVistaAtacable;
import org.example.Vistas.VistaGeneral;

import java.util.List;


public class JugadorHumano extends Jugador {
	
	private String email;
	private String contrasenia;
	private boolean esAdmin;
	public JugadorHumano(String nombre, String email, String contrasenia, boolean esAdmin) throws ModelException {
		super(nombre);
		this.email = email;
		setContrasenia(contrasenia);
		this.contrasenia = contrasenia;
		this.esAdmin = esAdmin;
	}

	@Override
	public List<Integer> seleccionarCasilla() {
		return ControladorPartida.getInstancia().getVistaAtacable().pedirCasilla();
	}

	@Override
	public String getId() {
		return email;
	}

	@Override
	public boolean decisionHabilidad() {
		return ControladorPartida.getInstancia().getVistaAtacable().pedirDecision();
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
	public void setContrasenia(String contrasenia)throws ModelException {
		Validaciones.checkStringInRange(6, 12, contrasenia, "nombre");
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
