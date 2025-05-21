package org.example.Vistas;

import org.example.Dato.Jugadores.IJugable;
import org.example.Logica.ControladorJugadores;
import org.example.Logica.ControladorPartida;
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
        controladorPartida.setVistaAtacable(vistaPartida);
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
        boolean ejecucionCorrecta = false;
       Utilidades.imprimir("Bienvenido al sistema de juego Battleship");
       do {
           try {
               publicSwitch();
               ejecucionCorrecta = true;
           } catch (Exception e) {
               Utilidades.imprimir("Ha ocurrido un error...");
           }
       }while(!ejecucionCorrecta);

    }

    public void publicSwitch()throws Exception{
        int opcion = 0;
        do {
            Utilidades.imprimir("\t\tGestor de Usuarios" +
                    "----------------------------------------" +
                    "Inserte 1: Darse de Alta" +
                    "Inserte 2: Iniciar Sesión" +
                    "Inserte 3: Exit");
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
                default:
                    break;
            }
        }while (opcion !=3);
    }

    private void switchPlayer() throws Exception{
        if (vistaJugadores.getJugadorLogueado() != null){ // no hace falta
            int opcion = 0;
            do {
                Utilidades.imprimir("\t\tGestor de partidas" +
                        "----------------------------------------" +
                        "Inserte 1: Iniciar Partida\n" +
                        "Inserte 2: Generar Puntuaciones\n" +
                        "Inserte 3: Cerrar Sesión\n" +
                        "Inserte 4: Darse de baja\n" );
                opcion = Utilidades.leerNumeroIntervalo("Introduzca el numero de la opcion deseada: ", 1, 3);
                switch (opcion) {
                    case 1: //iniciarPartida
                        IJugable first_player = vistaJugadores.getJugadorLogueado();
                        IJugable second_player = controladorJugadores.crearMaquina();// TODO: Esto hay que mejorarlo
                        controladorPartida.iniciarPartida(first_player, second_player);
                        break;
                    case 2: // generarPuntuaciones
                        vistaPartida.mostarPuntuaciones();
                        break;
                    case 3:// Cerrar sesión
                       Utilidades.imprimir("Cerrando sesión");
                        vistaJugadores.setJugadorLogueado(null);
                        break;
                    case 4 : //darse de baja
                        vistaJugadores.darBaja();
                }
            }while (opcion !=3);
        }
    }
}
