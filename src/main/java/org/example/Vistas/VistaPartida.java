package org.example.Vistas;

import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.IControladorPartida;

import java.util.*;

public class VistaPartida implements IVistaAtacable, IVistaPartida {

    private static final Scanner teclado = new Scanner(System.in);
    private IControladorPartida controladorPartida;


    public VistaPartida() {
    }

    public int lecturaCoordenada(String mensaje) {
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

    public String lecturaDecision(String mensaje) {
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

    public void mostrarPuntuacionesGlobal() {
        String line = controladorPartida.getPuntuacionesGlobal();
        System.out.println(line);
    }

    public void mostrarPuntuacionesJugador(JugadorHumano jugador) {
        String line = controladorPartida.getPuntuacionesJugador(jugador);
        System.out.println(line);
    }

    @Override
    public List<Integer> pedirCasilla() {
        List<Integer> coordenadas = new ArrayList<>();
        Integer coorY = lecturaCoordenada("Introduzca las coordenadas de la casilla deseada: " + '\n' + "Coordenada y : ");
        Integer coorX = lecturaCoordenada("Coordenada x : ");
        coordenadas.add(coorY);
        coordenadas.add(coorX);
        return coordenadas;
    }

    @Override
    public boolean pedirDecision() {
        boolean decision = false;
        String respuesta = lecturaDecision("Si desea contraatacar escriba 'Si', en caso contrario 'No'." + '\n' + "Respuesta: ");
        if (respuesta.equalsIgnoreCase("Si") || respuesta.equalsIgnoreCase("Sí")) {
            decision = true;
        }
        return decision;
    }

    @Override
    public int pedirFila() {
        return lecturaCoordenada("Introduzca la fila que quiere revelar: ");
    }

    @Override
    public void imprimirBienvenida() {
        System.out.println("Battleship UPM");
        System.out.println("Se ha empezado una partida....");
    }

    @Override
    public void imprimirRivalidad(String id1, String id2) {
        System.out.println("Partida inicada : " + id1 + " vs " + id2);
    }

    @Override
    public void imprimirTurno(String id) {
        System.out.println("Turno " + id);
    }

    @Override
    public void imprimirCasillaAtacada(List<Integer> casillaAtacada) {
        System.out.println("Se ha atacado la casilla : [" + casillaAtacada.get(0) + "][" + casillaAtacada.get(1) + "]");
    }

    @Override
    public void imprimirObjetoImpacto(String nombreBarco) {
        if (nombreBarco.equals("")) {
            System.out.println("El ataque ha fallado");
        } else System.out.println("El ataque ha impactdo a un " + nombreBarco);
    }

    @Override
    public void imprimirPuntuacion(String id1, int puntuacion1, String id2, int puntuacion2) {
        System.out.println("La partida ha finalizado...Las puntuaciones son las siguientes: " +
                "\n- " + id1 + ": " + puntuacion1 +
                "\n- " + id2 + ": " + puntuacion2);

    }

    @Override
    public void imprimirGanador(String id) {
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
