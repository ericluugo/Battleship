package org.example.Vistas;

import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.IControladorJugadores;
import servidor.Autenticacion;

import java.util.Scanner;

public class VistaJugadores implements IVistaJugadores{
    private JugadorHumano jugadorLogueado;
    private IControladorJugadores controladorJugadores;
    private static final Scanner teclado = new Scanner(System.in);



    public VistaJugadores() {
        this.jugadorLogueado =null;
    }

    public void solicitudDatosAlta() throws Exception {
        String nombre = leerCadena("Introduzca su nombre: ");
       String email = leerCadena("Introduzca su email ");
       String contrasenia = leerCadena("Introduzca su contraseña: ");
       controladorJugadores.darAlta(email, nombre, contrasenia, false);
    }

    public JugadorHumano getJugadorLogueado() {
        return jugadorLogueado;
    }

    public boolean iniciarSesion(){
        boolean sesionIniciada= false;
        String email = leerCadena("Introduce su email: ");
        String contrasenia = leerCadena("Introduce la contraseña: ");
        this.jugadorLogueado = controladorJugadores.iniciarSesion(email, contrasenia);
        if (this.jugadorLogueado==null) imprimir("Ha ocurrido un error al iniciar sesion. ");
        else {
            sesionIniciada=true;
            imprimir("Ha iniciado sesion correctamente. ");
        }
        return sesionIniciada;
    }

    public boolean darBaja(){
        boolean darBaja= false;
        if (jugadorLogueado != null) {
            darBaja = controladorJugadores.darBaja(jugadorLogueado);
            if (darBaja) {
                this.jugadorLogueado = null;
                imprimir("Se ha dado de baja correctamente.");
            } else {
                imprimir("Error al dar de baja.");
            }
        } else {
          imprimir("No hay ningún jugador logueado.");
        }
        return darBaja;
    }

    public static String leerCadena(String mensaje){
        String string = "";
        do {
            System.out.print(mensaje);
            string = teclado.nextLine();
        } while (string.isEmpty());
        return string;
    }

    public void imprimir(String msg){
        System.out.println(msg);
    }

    public IControladorJugadores getControladorJugadores() {
        return controladorJugadores;
    }

    public void setControladorJugadores(IControladorJugadores controladorJugadores) {
        this.controladorJugadores = controladorJugadores;
    }

    public void setJugadorLogueado(JugadorHumano jugadorLogueado) {
        this.jugadorLogueado = jugadorLogueado;
    }
}


