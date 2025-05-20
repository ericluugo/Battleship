package org.example.Vistas;

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

    }

    public void publicSwitch(){
        int opcion = 0;
        do {
            opcion = Utilidades.leerNumeroIntervalo("Introduzca el numero de la opcion deseada: ", 1, 3);
            switch (opcion) {
                case 1: //alta
                    break;
                case 2:
                    vistaJugadores.iniciarSesion();
                    if (vistaJugadores.getJugadorLogueado() != null){
                        switchPlayer();
                    }
            }
        }while (opcion !=3);
    }

    public void switchPlayer();
}
