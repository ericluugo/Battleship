package org.example.Vistas;

import org.example.Dato.Jugadores.IJugable;
import org.example.Logica.ControladorJugadores;
import org.example.Logica.ControladorPartida;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VistaGeneral {
    private VistaPartida vistaPartida;
    private VistaJugadores vistaJugadores;
    private ControladorPartida controladorPartida;
    private ControladorJugadores controladorJugadores;
    private static VistaGeneral instancia;
    private static final Scanner teclado = new Scanner(System.in);


    private VistaGeneral() throws Exception{
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
    public static VistaGeneral getInstancia() throws Exception {
        if (instancia == null) {
            instancia = new VistaGeneral();
        }
        return instancia;
    }
    public void programa(){
        boolean ejecucionCorrecta = false;
       imprimir("Bienvenido al sistema de juego Battleship");
       do {
           try {
               publicSwitch();
               ejecucionCorrecta = true;
           } catch (Exception e) {
               imprimir("Ha ocurrido un error...");
               imprimir(e.getMessage());
           }
       }while(!ejecucionCorrecta);

    }

    public void publicSwitch()throws Exception{
        int opcion = 0;
        do {
            imprimir("\t\tGestor de Usuarios\n" +
                    "----------------------------------------\n" +
                    "Inserte 1: Darse de Alta\n" +
                    "Inserte 2: Iniciar Sesión\n" +
                    "Inserte 3: Exit\n");
            opcion = leerNumeroIntervalo("Introduzca el numero de la opcion deseada: ", 1, 3);
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
                    imprimir("Seleccione una opción válida");
                    break;
            }
        }while (opcion !=3);
    }

    private void switchPlayer() throws Exception{
        if (vistaJugadores.getJugadorLogueado() != null){ // no hace falta
            int opcion = 0;
            do {
                imprimir("\t\tGestor de partidas\n" +
                        "----------------------------------------\n" +
                        "Inserte 1: Iniciar Partida\n" +
                        "Inserte 2: Generar Puntuaciones\n" +
                        "Inserte 3: Darse de baja\n" +
                        "Inserte 4: Cerrar Sesión\n" );
                opcion = leerNumeroIntervalo("Introduzca el numero de la opcion deseada: ", 1, 4);
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
                        if (vistaJugadores.darBaja())
                            publicSwitch();
                        break;
                    case 4 : // Cerrar sesión
                        imprimir("Cerrando sesión");
                        vistaJugadores.setJugadorLogueado(null);
                        publicSwitch();
                        break;
                    default:
                        imprimir("Seleccione una opción válida");
                        break;
                }
            }while (opcion !=4);
        }
    }

    private void precargaAdmins() throws Exception {
        try {
            controladorJugadores.darAlta("admin1@alumnos.upm.es","Admin1","Admin1*",true);
            controladorJugadores.darAlta("admin2@alumnos.upm.es","Admin2","Admin2*",true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static int leerNumeroIntervalo(String mensaje,int min, int max) {
        boolean correcto = false;
        int numero = 0;
        while (!correcto) {
            System.out.print(mensaje);
            try {
                numero = teclado.nextInt();
                if (numero >= min && numero <= max) {
                    correcto = true;
                } else {
                    System.out.println("Introduzca una opcion valida...");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, introduzca un número.");
                teclado.next(); // limpia el valor incorrecto del buffer
            }
        }
        return numero;
    }

    public void imprimir(String msg){
        System.out.println(msg);
    }
}
