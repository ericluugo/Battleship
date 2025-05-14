package org.example.Logica;

import org.example.Dato.Jugadores.JugadorHumano;

public interface IControladorJugadores {
     JugadorHumano darAlta(String email, String nombre, String contraseña);
    boolean darBaja(JugadorHumano jugadorHumano);
    JugadorHumano iniciarSesion(String email, String contraseña);
    boolean comprobarEmail(String email);
    boolean comprobarContrasenia(String email, String contraseña);

}
