package org.example.Dato.Partida;

import org.example.Dato.Jugadores.IJugable;

import java.time.LocalDate;

public class Partida {

    private static final int MAX_FILAS = 10;
    private static final int MAX_COLUMNAS = 10;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private IJugable jugador1;
    private IJugable jugador2;
    private Tablero tablero1;
    private Tablero tablero2;
    private int puntosJugador1;
    private int puntosJugador2;
    private IJugable ganador;
    private boolean turnoJugador1;

    public Partida(IJugable jugador1, IJugable jugador2) {
        this.fechaInicio = LocalDate.now();
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero1 = new Tablero(MAX_FILAS, MAX_COLUMNAS);
        this.tablero2 = new Tablero(MAX_FILAS, MAX_COLUMNAS);
        this.turnoJugador1 = true;
    }

    public boolean verificarFinPartida() {
        boolean finPartida = false;
        if (tablero1.todosBarcosMuertos() && tablero2.todosBarcosMuertos()) {
            if (turnoJugador1) {
                setGanador(jugador1);
            } else setGanador(jugador2);
            finPartida = true;
            fechaFin = LocalDate.now();
        } else if (tablero1.todosBarcosMuertos()) {
            finPartida = true;
            setGanador(jugador2);
            fechaFin = LocalDate.now();
        } else if (tablero2.todosBarcosMuertos()) {
            finPartida = true;
            setGanador(jugador1);
            fechaFin = LocalDate.now();
        }
        return finPartida;
    }

    public void inicializarPartida() {
        tablero1.getBarcos().add(FactoriaBarcos.crearBarco("Portaviones", tablero1.colocacionBarcos(4)));
        tablero2.getBarcos().add(FactoriaBarcos.crearBarco("Portaviones", tablero2.colocacionBarcos(4)));
        tablero1.getBarcos().add(FactoriaBarcos.crearBarco("Acorazado", tablero1.colocacionBarcos(3)));
        tablero2.getBarcos().add(FactoriaBarcos.crearBarco("Acorazado", tablero2.colocacionBarcos(3)));
        tablero1.getBarcos().add(FactoriaBarcos.crearBarco("Submarino", tablero1.colocacionBarcos(3)));
        tablero2.getBarcos().add(FactoriaBarcos.crearBarco("Submarino", tablero2.colocacionBarcos(3)));
        tablero1.getBarcos().add(FactoriaBarcos.crearBarco("Patrullero", tablero1.colocacionBarcos(2)));
        tablero2.getBarcos().add(FactoriaBarcos.crearBarco("Patrullero", tablero2.colocacionBarcos(2)));
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void cambiarTurno() {
        setTurno(!isTurno());
    }

    public IJugable getJugador1() {
        return jugador1;
    }

    public IJugable getJugador2() {
        return jugador2;
    }

    public Tablero getTablero1() {
        return tablero1;
    }

    public Tablero getTablero2() {
        return tablero2;
    }

    public int getPuntosJugador1() {
        return puntosJugador1;
    }

    public void setPuntosJugador1(int puntosJugador1) {
        this.puntosJugador1 = puntosJugador1;
    }

    public int getPuntosJugador2() {
        return puntosJugador2;
    }

    public void setPuntosJugador2(int puntosJugador2) {
        this.puntosJugador2 = puntosJugador2;
    }

    public IJugable getGanador() {
        return ganador;
    }

    public void setGanador(IJugable ganador) {
        this.ganador = ganador;
    }

    public int getPuntosGanador() {
        int res = 0;
        IJugable jugador = getGanador();
        if (jugador1 == jugador) {
            res = puntosJugador1;
        } else {
            res = puntosJugador2;
        }
        return res;
    }

    public boolean isTurno() {
        return turnoJugador1;
    }

    public void setTurno(boolean turno) {
        this.turnoJugador1 = turno;
    }
}
