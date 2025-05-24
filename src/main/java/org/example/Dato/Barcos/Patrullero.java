package org.example.Dato.Barcos;

import org.example.Dato.Partida.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Logica.ControladorPartida;

import java.util.List;

public class Patrullero extends Barco {

    private final int NUM_REVELACIONES_DISPONIBLES = 1;
    private int numRevelacionesDisponibles;

    public Patrullero(String nombre, int numCasillas, List<Casilla> casillas) {
        super(nombre, numCasillas, casillas);
        this.numRevelacionesDisponibles = NUM_REVELACIONES_DISPONIBLES;
    }

    @Override
    public void habilidad(Tablero tableroEnemigo) {
        int fila = 0;
        if (ControladorPartida.getInstancia().getPartidaEnJuego().isTurno()) {
            fila = ControladorPartida.getInstancia().getPartidaEnJuego().getJugador2().seleccionarFila();
        } else fila = ControladorPartida.getInstancia().getPartidaEnJuego().getJugador1().seleccionarFila();
        for (int j = 0; j < tableroEnemigo.getNumColumnas(); j++) {
            tableroEnemigo.getTablero()[fila][j].setEstadoVisibilidad(true);
        }
        ControladorPartida.getInstancia().getVistaPartida().imprimir("Se ha revelado la fila " + fila + " con El Patrullero");
        numRevelacionesDisponibles--;
    }

    @Override
    public boolean habilidadDisponible() {
        if (numRevelacionesDisponibles > 0) {
            return true;
        } else return false;
    }
}
