package org.example.Logica;

import org.example.Dato.Jugadores.IJugable;
import org.example.Dato.Partida.Partida;

public interface IControladorPartida {

    public String generarPuntuaciones(Partida partida);

    public void iniciarPartida(IJugable jugador1,IJugable jugador2);
}
