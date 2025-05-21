package org.example.Vistas;

import org.example.Dato.Jugadores.IJugable;
import org.example.Logica.ControladorJugadores;
import org.example.Logica.ControladorPartida;
import org.example.ModelException;
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
        controladorPartida = ControladorPartida.getInstancia();
        vistaPartida.setControladorPartida(controladorPartida);
        controladorPartida.setVistaAtacable(vistaPartida);
        controladorPartida.setVistaPartida(vistaPartida);
        vistaJugadores.setControladorJugadores(controladorJugadores);
        controladorJugadores.setVistaJugadores(vistaJugadores);
        this.precargaAdmins();
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
                    vistaJugadores.solicitudDatosAlta();
                    break;
                case 2: // iniciar sesión
                    vistaJugadores.iniciarSesion();
                    if (vistaJugadores.getJugadorLogueado() != null){
                        switchPlayer();
                    }
                    break;
                default:
                    Utilidades.imprimir("Seleccione una opción válida");
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
                        "Inserte 3: Darse de baja\n" +
                        "Inserte 4: Cerrar Sesión\n" );
                opcion = Utilidades.leerNumeroIntervalo("Introduzca el numero de la opcion deseada: ", 1, 3);
                switch (opcion) {
                    case 1: //iniciarPartida
                        IJugable first_player = vistaJugadores.getJugadorLogueado();
                        IJugable second_player = controladorJugadores.crearMaquina();// TODO: Esto hay que mejorarlo
                        controladorPartida.iniciarPartida(first_player, second_player);
                        break;
                    case 2: // generarPuntuaciones
                        if (vistaJugadores.getJugadorLogueado().isEsAdmin()) {
                            vistaPartida.mostrarPuntuacionesGlobal();
                        }else{
                            vistaPartida.mostrarPuntuacionesJugador(vistaJugadores.getJugadorLogueado());
                        }
                        break;
                    case 3:// darse de baja
                        vistaJugadores.darBaja();
                        break;
                    case 4 : // Cerrar sesión
                        Utilidades.imprimir("Cerrando sesión");
                        vistaJugadores.setJugadorLogueado(null);
                        break;
                    default:
                        Utilidades.imprimir("Seleccione una opción válida");
                        break;
                }
            }while (opcion !=4);
        }
    }

    private void precargaAdmins() throws ModelException {
        try {
            controladorJugadores.darAlta("admin1@alumnos.upm.es","Admin1","Admin1*",true);
            controladorJugadores.darAlta("admin2@alumnos.upm.es","Admin2","Admin2*",true);
        } catch (ModelException e) {
            throw new RuntimeException(e);
        }

    }
}
