package org.example.Dato.Partida;

import org.example.Dato.Jugadores.IJugable;

import java.time.LocalDate;

public class Partida {

    private static final int MAX_FILAS = 10;
    private static final int MAX_COLUMNAS = 10;
    private static final int NUM_CASILLAS_ACORAZADO = 3;
    private static final int NUM_CASILLAS_SUBMARINO = 3;
    private static final int NUM_CASILLAS_PATRULLERO = 2;
    private static final int NUM_CASILLAS_PORTAVIONES = 4;
    private final LocalDate fechaInicio;
    private LocalDate fechaFin;
    private final IJugable jugador1;
    private final IJugable jugador2;
    private final Tablero tablero1;
    private final Tablero tablero2;
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
        tablero1.getBarcos().add(FactoriaBarcos.crearBarco("Portaviones", tablero1.colocacionBarcos(NUM_CASILLAS_PORTAVIONES),NUM_CASILLAS_PORTAVIONES));
        tablero2.getBarcos().add(FactoriaBarcos.crearBarco("Portaviones", tablero2.colocacionBarcos(NUM_CASILLAS_PORTAVIONES),NUM_CASILLAS_PORTAVIONES));
        tablero1.getBarcos().add(FactoriaBarcos.crearBarco("Acorazado", tablero1.colocacionBarcos(NUM_CASILLAS_ACORAZADO),NUM_CASILLAS_ACORAZADO));
        tablero2.getBarcos().add(FactoriaBarcos.crearBarco("Acorazado", tablero2.colocacionBarcos(NUM_CASILLAS_ACORAZADO),NUM_CASILLAS_ACORAZADO));
        tablero1.getBarcos().add(FactoriaBarcos.crearBarco("Submarino", tablero1.colocacionBarcos(NUM_CASILLAS_SUBMARINO),NUM_CASILLAS_SUBMARINO));
        tablero2.getBarcos().add(FactoriaBarcos.crearBarco("Submarino", tablero2.colocacionBarcos(NUM_CASILLAS_SUBMARINO),NUM_CASILLAS_SUBMARINO));
        tablero1.getBarcos().add(FactoriaBarcos.crearBarco("Patrullero", tablero1.colocacionBarcos(NUM_CASILLAS_PATRULLERO),NUM_CASILLAS_PATRULLERO));
        tablero2.getBarcos().add(FactoriaBarcos.crearBarco("Patrullero", tablero2.colocacionBarcos(NUM_CASILLAS_PATRULLERO),NUM_CASILLAS_PATRULLERO));
    }

    @Override
    public String toString() {
        return jugador1.getId()+" vs "+jugador2.getId()+", "+puntosJugador1+" / "+puntosJugador2+" ("+fechaInicio+"-"+fechaFin+")";
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
