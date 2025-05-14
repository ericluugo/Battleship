package org.example.Vistas;

import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.IControladorJugadores;
import org.example.Utilidades;
import servidor.Autenticacion;

import java.util.Scanner;

public class VistaJugadores implements IVistaJugadores{
    private JugadorHumano jugadorLogueado;


    public VistaJugadores() {
        this.jugadorLogueado =null;

    }

    public boolean solicitudDatosAlta(IControladorJugadores iControladorJugadores, Scanner teclado){
        boolean nuevoJugador = true;
        String nombre = Utilidades.leerCadena("Introduzca su nombre: ");
       String email = Utilidades.leerCadena("Introduzca su email ");

       if (iControladorJugadores.comprobarEmail(email)){
           System.out.println("Ya esta registrado con este email, inicie Sesion");
           nuevoJugador = false;

           //Comprobar que se usuario de la UPM
       } else if(!Autenticacion.existeCuentaUPMStatic(email)){
           System.out.println("Este email no pertenece a la UPM, no se puede registrar");
           nuevoJugador = false;
       } else{
           //Si esta todo correcto
            String contrasenia = Utilidades.leerCadena("Introduzca su contraseña: ");
            JugadorHumano jugador = iControladorJugadores.darAlta(email, nombre, contrasenia);
            System.out.println("Ha iniciado sesion correctamente. ");
            this.jugadorLogueado = jugador;
       }
       return nuevoJugador;
    }

    public JugadorHumano getJugadorLogueado() {
        return jugadorLogueado;
    }
    public boolean iniciarSesion(IControladorJugadores iControladorJugadores){
        boolean sesionIniciada= false;
        String email = Utilidades.leerCadena("Introduce su email: ");

        //Comprobar si hay un jugador que ya exista que tenga este email
        if (!iControladorJugadores.comprobarEmail(email)){
            System.out.println("No hay un jugador registrado con este email, debe darse de alta primero");
        }else {
            //Comprobar contraseña
            String contrasenia = Utilidades.leerCadena("Introduce la contraseña: ");
            if (!iControladorJugadores.comprobarContrasenia(email, contrasenia)){
                System.out.println("Contraseña incorrecta. ");
            } else {
                this.jugadorLogueado =iControladorJugadores.iniciarSesion(email, contrasenia);
                if (this.jugadorLogueado==null) System.out.println("Ha ocurrido un error al iniciar sesion. ");
                else {
                    sesionIniciada=true;
                    System.out.println("Ha iniciado sesion correctamente. ");
                }
            }
        }
        return sesionIniciada;
    }

    public boolean darBaja(IControladorJugadores iControladorJugadores){
        boolean darBaja= false;
        if (jugadorLogueado != null) {
            darBaja = iControladorJugadores.darBaja(jugadorLogueado);
            if (darBaja) {
                this.jugadorLogueado = null;
                System.out.println("Se ha dado de baja correctamente.");
            } else {
                System.out.println("Error al dar de baja.");
            }
        } else {
            System.out.println("No hay ningún jugador logueado.");
        }
        return darBaja;
        }
    }

