package org.example.Logica;

import org.example.Dato.Jugadores.IJugable;
import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Dato.Partida.Partida;

public interface IControladorPartida {

    void iniciarPartida(IJugable jugador1, IJugable jugador2) throws Exception;

    String getPuntuacionesGlobal();

    String getPuntuacionesJugador(String id);
}
