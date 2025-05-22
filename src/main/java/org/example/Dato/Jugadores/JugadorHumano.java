package org.example.Dato.Jugadores;

import org.example.Logica.ControladorPartida;
import org.example.Validaciones;
import servidor.Autenticacion;
import servidor.ObtencionDeRol;

import java.net.Authenticator;
import java.util.List;


public class JugadorHumano extends Jugador {
	
	private String email;
	private String nombre;
	private String contrasenia;
	private boolean esAdmin;

	public JugadorHumano(String nombre, String email, String contrasenia, boolean esAdmin) throws Exception {
		setNombre(nombre);
		setEmail(email);
		setContrasenia(contrasenia);
		setContrasenia(contrasenia);
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
	public void setEmail(String email) throws Exception {
		if (!Autenticacion.existeCuentaUPMStatic(email)) throw new Exception("Este email no pertenece a la UPM, no se puede registrar");
		this.email = email;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) throws Exception{
		Validaciones.checkContrasenia(contrasenia);
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

	@Override
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws Exception {
		Validaciones.checkNombre("nombre");
		this.nombre = nombre;
	}
}
