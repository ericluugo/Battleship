package org.example.Logica;

import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Dato.Jugadores.Maquina;

public interface IControladorJugadores {
     JugadorHumano darAlta(String email, String nombre, String contrasenia, boolean esAdmin) throws Exception;
    boolean darBaja(JugadorHumano jugadorHumano);
     JugadorHumano iniciarSesion(String email, String contrasenia);
     boolean comprobarEmail(String email);
     Maquina crearMaquina() throws Exception;


}
