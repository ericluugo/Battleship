package org.example.Dato.Barcos;

import org.example.Dato.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Logica.ControladorPartida;

import java.util.Arrays;
import java.util.List;

public class Acorazado extends Barco {

    private final int NUM_ATAQUE_MAX = 2;
    private int numAtaquesDisponibles;

    public Acorazado(String nombre, int numCasillas, List<Casilla> casillas) {
        super(nombre, numCasillas, casillas);
        this.numAtaquesDisponibles = NUM_ATAQUE_MAX;
    }

    @Override
    public void habilidad(Tablero tableroEnemigo) {
        List<Integer> coordenadas;
        if (ControladorPartida.getInstancia().getPartidaJugable().isTurno()) {
            coordenadas = ControladorPartida.getInstancia().getPartidaJugable().getJugador2().seleccionarCasilla();
        } else coordenadas = ControladorPartida.getInstancia().getPartidaJugable().getJugador1().seleccionarCasilla();
        int fila = coordenadas.get(1);
        int columna = coordenadas.get(0);
        List<Integer> coordenadaSuperior = Arrays.asList(fila, columna + 1);
        List<Integer> coordenadaInferior = Arrays.asList(fila, columna - 1);
        List<Integer> coordenadaIzquierda = Arrays.asList(fila - 1, columna);
        List<Integer> coordenadaDerecha = Arrays.asList(fila + 1, columna);
        List<List<Integer>> coordenadasVecinas = Arrays.asList(
                coordenadas,
                coordenadaSuperior,
                coordenadaInferior,
                coordenadaIzquierda,
                coordenadaDerecha
        );
        for (int index = 0; index < coordenadasVecinas.size(); index++) {
            List<Integer> coordenadaActual = coordenadasVecinas.get(index);
            if ((coordenadaActual.get(0) >= 0 && coordenadaActual.get(0) <= 9) && (coordenadaActual.get(1) >= 0 && coordenadaActual.get(1) <= 9)) {
                Barco atacado = tableroEnemigo.recibirCoordenadas(coordenadaActual);
                ControladorPartida.getInstancia().getVistaPartida().imprimir("Se ha atacado la casilla : [" + coordenadaActual.get(1) + "][" + coordenadaActual.get(0) + "] con El Acorazado");
                if (atacado == null) {
                    ControladorPartida.getInstancia().getVistaPartida().imprimir("El ataque ha fallado");
                } else {
                    ControladorPartida.getInstancia().getVistaPartida().imprimir("El ataque ha acertado");
                    atacado.isBarcoMuerto();
                }
            }
        }
        numAtaquesDisponibles--;
    }

    @Override
    public boolean habilidadDisponible() {
        if (numAtaquesDisponibles > 0) {
            return true;
        } else return false;
    }
}
