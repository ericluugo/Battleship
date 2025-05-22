package org.example.Dato.Partida;

import org.example.Dato.FactoriaBarcos;
import org.example.Dato.Jugadores.IJugable;

import java.time.LocalDate;

public class Partida {

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
	private final int MAX_FILAS = 10;
	private final int MAX_COLUMNAS = 10;

	public Partida( IJugable jugador1, IJugable jugador2) {
		this.fechaInicio = LocalDate.now();
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.tablero1 = new Tablero(MAX_FILAS,MAX_COLUMNAS);
		this.tablero2 = new Tablero(MAX_FILAS,MAX_COLUMNAS);
		this.turnoJugador1 = true;
	}

	public boolean verificarFinPartida(){
		boolean finPartida = false;
		if (tablero1.todosBarcosMuertos()&&tablero2.todosBarcosMuertos()){
			if (turnoJugador1){
				setGanador(jugador1);
			}else setGanador(jugador2);
			finPartida = true;
			fechaFin = LocalDate.now();
		}else if(tablero1.todosBarcosMuertos()){
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
	public void generarPuntuacion(){
		puntosJugador1 = tablero2.calcularPuntuacion();
		puntosJugador2 = tablero1.calcularPuntuacion();
	}


	public void inicializarPartida(){
		FactoriaBarcos.crearBarco("Portaviones",tablero1.colocacionBarcos(4));
		FactoriaBarcos.crearBarco("Portaviones",tablero2.colocacionBarcos(4));
		FactoriaBarcos.crearBarco("Acorazado",tablero1.colocacionBarcos(3));
		FactoriaBarcos.crearBarco("Acorazado",tablero2.colocacionBarcos(3));
		FactoriaBarcos.crearBarco("Submarino",tablero1.colocacionBarcos(3));
		FactoriaBarcos.crearBarco("Submarino",tablero2.colocacionBarcos(3));
		FactoriaBarcos.crearBarco("Patrullero",tablero1.colocacionBarcos(2));
		FactoriaBarcos.crearBarco("Patrullero",tablero2.colocacionBarcos(2));
	}

	public void cambiarTurno(){
		setTurno(!isTurno());
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public IJugable getJugador1() {
		return jugador1;
	}

	public void setJugador1(IJugable jugador1) {
		this.jugador1 = jugador1;
	}

	public IJugable getJugador2() {
		return jugador2;
	}

	public void setJugador2(IJugable jugador2) {
		this.jugador2 = jugador2;
	}

	public Tablero getTablero1() {
		return tablero1;
	}

	public void setTablero1(Tablero tablero1) {
		this.tablero1 = tablero1;
	}

	public Tablero getTablero2() {
		return tablero2;
	}

	public void setTablero2(Tablero tablero2) {
		this.tablero2 = tablero2;
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

	public int getPuntosGanador(){
		int res = 0;
		IJugable jugador = getGanador();
		if (jugador1 == jugador){
			res = puntosJugador1;
		}else{
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

	public String toString(){
		String line;
		line = "Partida "+jugador1+" vs "+jugador2+" ("+fechaInicio+" to "+fechaFin+")";
		return line;
	}
}
