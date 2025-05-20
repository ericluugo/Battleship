package org.example.Vistas;

import org.example.Logica.IControladorPartida;
import org.example.Utilidades;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class VistaPartida implements IVistaPartida {

	private IControladorPartida controladorPartida;

	public VistaPartida() {}

	public void mostarPuntuaciones(IControladorPartida controladorPartida){

	}

	@Override
	public List<Integer> pedirCasilla () {
		List<Integer> coordenadas = new ArrayList<>();
		Integer coorX = Utilidades.lecturaCoordenada("Introduzca las coordenadas de la casilla deseada: " +'\n' + "Coordenada x : ");
		Integer coorY = Utilidades.lecturaCoordenada("Coordenada y : ");
		coordenadas.add(coorX);
		coordenadas.add(coorY);
		return coordenadas;
	}

	@Override
	public boolean pedirDecision() {
		boolean decision = false;
		String respuesta = Utilidades.lecturaDecision("Si desea contraatacar escriba 'Si', en caso contrario 'No'." + '\n' + "Respuesta: ");
		if (respuesta.equalsIgnoreCase("Si") || respuesta.equalsIgnoreCase("Sí")){
			decision = true;
		}
		return decision;
	}

	@Override
	public int pedirFila() {
		int fila = Utilidades.lecturaCoordenada("Introduzca la fila que quiere revelar: ");
		return fila;
	}

	@Override
	public void imprimirBienvenida() {
		JFrame frame = new JFrame("Battleship UPM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 200);
		frame.setLocationRelativeTo(null); // Centrar ventana

		// Crear un label con el texto del título
		JLabel titleLabel = new JLabel("BATTLESHIP UPM", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 100)); // Fuente grande y negrita
		titleLabel.setForeground(Color.BLUE); // Puedes cambiar el color
		// Agregar el label al frame
		frame.add(titleLabel);
		// Hacer visible la ventana
		frame.setVisible(true);
		System.out.println("Se ha empezado una partida....");
	}

	@Override
	public void imprimirRivalidad(String id1,String id2) {
		System.out.println("Partida inicada : "+ id1 + " vs "+id2);
	}

	@Override
	public void imprimirTurno(String id) {
		System.out.println("Turno " + id);
	}

	@Override
	public void imprimirCasillaAtacada(List<Integer> casillaAtacada) {
		System.out.println("Se ha atacado la casilla : ["+casillaAtacada.get(0)+"]["+casillaAtacada.get(1)+"]");
	}

	@Override
	public void imprimirObjetoImpacto(String nombreBarco) {
		if (nombreBarco.equals("")){
			System.out.println("El ataque ha fallado");
		}else System.out.println("El ataque ha impactdo a un "+ nombreBarco);
	}

	@Override
	public void imprimirResultadoContraataque(Object object) {
		if (object == null){
			System.out.println("El ataque ha acertado");
		}else System.out.println("El ataque ha fallado");
	}

	@Override
	public void imprimirFilaRevelo(int fila) {
		System.out.println("Se ha revelado la fila " + fila);
	}

	@Override
	public void imprimirReparacion() {
		System.out.println("Se ha reparado el Submarino");
	}

	@Override
	public void imprimirPuntuacion(String id1, int puntuacion1, String id2, int puntuacion2) {
		System.out.println("La partida ha finalizado...Las puntuaciones son las siguientes: " +
				"\n- " + id1 + ": " + puntuacion1 +
				"\n- " + id2 + ": " + puntuacion2);

	}

	@Override
	public void imprimirGanador(String id) {

		JFrame frameGanador = new JFrame("Resultado Final");
		frameGanador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameGanador.setSize(600, 200);
		frameGanador.setLocationRelativeTo(null);
		JLabel titleLabel = new JLabel("GANADOR: " + id, SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 100)); // Tamaño grande
		titleLabel.setForeground(Color.GREEN.darker()); // Color verde oscuro

		// Agregar el label al frame
		frameGanador.add(titleLabel);

		// Mostrar la ventana
		frameGanador.setVisible(true);

		// Mensaje en consola
		System.out.println("¡GANADOR: " + id + "!");
	}

	public IControladorPartida getControladorPartida() {
		return controladorPartida;
	}

	public void setControladorPartida(IControladorPartida controladorPartida) {
		this.controladorPartida = controladorPartida;
	}
}
