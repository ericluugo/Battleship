package org.example.Vistas;

import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.IControladorJugadores;

import java.util.Scanner;

public class VistaJugadores implements IVistaJugadores {
    private static final Scanner teclado = new Scanner(System.in);
    private JugadorHumano jugadorLogueado;
    private IControladorJugadores controladorJugadores;


    public VistaJugadores() {
        this.jugadorLogueado = null;
    }

    public static String leerCadena(String mensaje) {
        String string = "";
        do {
            System.out.print(mensaje);
            string = teclado.nextLine();
        } while (string.isEmpty());
        return string;
    }

    public void solicitudAlta() throws Exception {
        System.out.println("Un nombre de usuario, palabra corta de entre 3 y 10 caracteres de solo caracteres y números que no esté en la lista negra...");
        String nombre = leerCadena("Introduzca su nombre: ");
        System.out.println("Correo perteneciente a la UPM...");
        String email = leerCadena("Introduzca su email: ");
        System.out.println("Una contraseña, obligatorio mayúsculas, minúsculas, números y símbolos con tamaño mínimo de 6 caracteres y 12 de máximo");
        String contrasenia = leerCadena("Introduzca su contraseña: ");
        controladorJugadores.darAlta(email, nombre, contrasenia, false);
        imprimir("Se ha creado el usuario correctamente");
    }

    public JugadorHumano getJugadorLogueado() {
        return jugadorLogueado;
    }

    public void setJugadorLogueado(JugadorHumano jugadorLogueado) {
        this.jugadorLogueado = jugadorLogueado;
    }

    public boolean iniciarSesion() {
        boolean sesionIniciada = false;
        String email = leerCadena("Introduce su email: ");
        String contrasenia = leerCadena("Introduce la contraseña: ");
        setJugadorLogueado(controladorJugadores.iniciarSesion(email, contrasenia));
        if (this.jugadorLogueado == null) imprimir("Ha ocurrido un error al iniciar sesion. ");
        else {
            sesionIniciada = true;
            imprimir("Ha iniciado sesion correctamente. ");
        }
        return sesionIniciada;
    }

    public boolean darBaja() {
        boolean darBaja = false;
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

    public void imprimir(String msg) {
        System.out.println(msg);
    }

    public void setControladorJugadores(IControladorJugadores controladorJugadores) {
        this.controladorJugadores = controladorJugadores;
    }
}


