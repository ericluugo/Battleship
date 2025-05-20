package org.example.Vistas;

import org.example.Dato.Jugadores.IJugable;
import org.example.Logica.ControladorJugadores;
import org.example.Logica.ControladorPartida;
import org.example.Logica.IControladorPartida;
import org.example.Utilidades;

public class VistaGeneral {
    private VistaPartida vistaPartida;
    private VistaJugadores vistaJugadores;
    private ControladorPartida controladorPartida;
    private ControladorJugadores controladorJugadores;
    private static VistaGeneral instancia;

    private VistaGeneral(){
        vistaPartida = new VistaPartida();
        vistaJugadores = new VistaJugadores();
        controladorJugadores = new ControladorJugadores();
        controladorPartida = new ControladorPartida();
        vistaPartida.setControladorPartida(controladorPartida);
        controladorPartida.setVistaPartida(vistaPartida);
        vistaJugadores.setControladorJugadores(controladorJugadores);
        controladorJugadores.setVistaJugadores(vistaJugadores);
    }

    // 3. Método público para obtener la instancia única
    public static VistaGeneral getInstancia() {
        if (instancia == null) {
            instancia = new VistaGeneral();
        }
        return instancia;
    }
    public void programa(){
        System.out.println("Bienvenido al sistema de juego Battleship");
        publicSwitch();
    }

    public void publicSwitch(){
        int opcion = 0;
        do {
            System.out.println("\t\tGestor de Usuarios" +
                    "----------------------------------------" +
                    "Inserte 1: Darse de Alta" +
                    "Inserte 2: Iniciar Sesión" +
                    "Inserte 3: Darse de baja" +
                    "Inserte 4: Exit");
            opcion = Utilidades.leerNumeroIntervalo("Introduzca el numero de la opcion deseada: ", 1, 4);
            switch (opcion) {
                case 1: //alta
                    vistaJugadores.solicitudDatosAlta(controladorJugadores);
                    break;
                case 2: // iniciar sesión
                    vistaJugadores.iniciarSesion();
                    if (vistaJugadores.getJugadorLogueado() != null){
                        switchPlayer();
                    }
                    break;
                case 3: // dar de baja jugador
                    vistaJugadores.darBaja(controladorJugadores);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema");
                    break;
            }
        }while (opcion !=4);
    }

    private void switchPlayer(){
        if (vistaJugadores.getJugadorLogueado() != null){
            int opcion = 0;
            do {
                System.out.println("\t\tGestor de partidas" +
                        "----------------------------------------" +
                        "Inserte 1: Iniciar Partida" +
                        "Inserte 2: Generar Puntuaciones" +
                        "Inserte 3: Cerrar Sesión");
                opcion = Utilidades.leerNumeroIntervalo("Introduzca el numero de la opcion deseada: ", 1, 3);
                switch (opcion) {
                    case 1: //iniciarPartida
                        IJugable first_player = vistaJugadores.getJugadorLogueado();
                        IJugable second_player = vistaJugadores.getMaquina();// TODO: Esto hay que mejorarlo
                        controladorPartida.iniciarPartida(first_player, second_player);
                        break;
                    case 2: // generarPuntuaciones
                        vistaPartida.mostarPuntuaciones(controladorPartida);
                        break;
                    case 3:// Cerrar sesión
                        System.out.println("Cerrando sesión");
                        vistaJugadores.setJugadorLogueado(null);
                        break;
                }
            }while (opcion !=3);
        }
    }
}
