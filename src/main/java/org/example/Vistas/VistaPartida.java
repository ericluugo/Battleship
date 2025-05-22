package org.example.Vistas;

import org.example.Dato.Jugadores.IJugable;
import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.IControladorPartida;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class VistaPartida implements IVistaAtacable, IVistaPartida {

	private IControladorPartida controladorPartida;
	private static final Scanner teclado = new Scanner(System.in);


	public VistaPartida() {}

	public void mostrarPuntuacionesGlobal(){
		String line = controladorPartida.getPuntuacionesGlobal();
		System.out.println(line);
	}

	public void mostrarPuntuacionesJugador(JugadorHumano jugador){
		String line = controladorPartida.getPuntuacionesJugador(jugador);
		System.out.println(line);
	}

	@Override
	public List<Integer> pedirCasilla () {
		List<Integer> coordenadas = new ArrayList<>();
		Integer coorY = lecturaCoordenada("Introduzca las coordenadas de la casilla deseada: " +'\n' + "Coordenada y : ");
		Integer coorX = lecturaCoordenada("Coordenada x : ");
		coordenadas.add(coorY);
		coordenadas.add(coorX);
		return coordenadas;
	}

	public static int lecturaCoordenada(String mensaje) {
		boolean correcto = false;
		int coordenada = -1;

		while (!correcto) {
			System.out.print(mensaje);
			try {
				coordenada = teclado.nextInt();
				if (coordenada >= 0 && coordenada <= 9) {
					correcto = true;
				} else {
					System.out.println("Introduzca una coordenada entre 0 y 9.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, introduzca un número.");
				teclado.next(); // limpia el valor incorrecto del buffer
			}
		}
		return coordenada;
	}

	@Override
	public boolean pedirDecision() {
		boolean decision = false;
		String respuesta = lecturaDecision("Si desea contraatacar escriba 'Si', en caso contrario 'No'." + '\n' + "Respuesta: ");
		if (respuesta.equalsIgnoreCase("Si") || respuesta.equalsIgnoreCase("Sí")){
			decision = true;
		}
		return decision;
	}

	public static String lecturaDecision(String mensaje) {
		boolean correcto = false;
		String respuesta = "";

		while (!correcto) {
			System.out.print(mensaje);
			try {
				respuesta = teclado.nextLine();

				if (respuesta.equalsIgnoreCase("Si") ||
						respuesta.equalsIgnoreCase("Sí") ||
						respuesta.equalsIgnoreCase("No")) {
					correcto = true;
				} else {
					System.out.println("Escriba una respuesta válida (Si o No).");
				}

			} catch (NoSuchElementException | IllegalStateException e) {
				System.out.println("Error al leer la entrada");
			}
		}

		return respuesta;
	}

	@Override
	public int pedirFila() {
		int fila = lecturaCoordenada("Introduzca la fila que quiere revelar: ");
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
		System.out.println("Se ha atacado la casilla : ["+casillaAtacada.get(1)+"]["+casillaAtacada.get(0)+"]");
	}

	@Override
	public void imprimirObjetoImpacto(String nombreBarco) {
		if (nombreBarco.equals("")){
			System.out.println("El ataque ha fallado");
		}else System.out.println("El ataque ha impactdo a un "+ nombreBarco);
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

	@Override
	public void imprimir(String msg) {
		System.out.println(msg);
	}
}
