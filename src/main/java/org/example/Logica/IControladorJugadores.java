package org.example.Logica;

import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Dato.Jugadores.Maquina;
import org.example.ModelException;

public interface IControladorJugadores {
    public JugadorHumano darAlta(String email, String nombre, String contraseña) throws ModelException;
    public boolean darBaja(JugadorHumano jugadorHumano);
    public JugadorHumano iniciarSesion(String email, String contraseña);
    public boolean comprobarEmail(String email);
    public boolean comprobarContrasenia(String email, String contraseña);
    public Maquina crearMaquina();


}
