package org.example.Vistas;

import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.IControladorJugadores;
import org.example.ModelException;
import org.example.Utilidades;
import servidor.Autenticacion;
import servidor.ObtencionDeRol;

import java.util.Scanner;

public class VistaJugadores implements IVistaJugadores{
    private JugadorHumano jugadorLogueado;
    private IControladorJugadores controladorJugadores;


    public VistaJugadores() {
        this.jugadorLogueado =null;

    }

    public boolean solicitudDatosAlta() throws ModelException {
        boolean nuevoJugador = true;
        String nombre = Utilidades.leerCadena("Introduzca su nombre: ");
       String email = Utilidades.leerCadena("Introduzca su email ");

       if (controladorJugadores.comprobarEmail(email)){
           Utilidades.imprimir("Ya esta registrado con este email, inicie Sesion");
           nuevoJugador = false;
           //Comprobar que se usuario de la UPM
       } else if(!Autenticacion.existeCuentaUPMStatic(email)){
           Utilidades.imprimir("Este email no pertenece a la UPM, no se puede registrar");
           nuevoJugador = false;
       } else{
           //Si esta todo correcto compruebo el rol
            String contrasenia = Utilidades.leerCadena("Introduzca su contraseña: ");
            JugadorHumano jugador = controladorJugadores.darAlta(email, nombre, contrasenia, false);
            Utilidades.imprimir("Ha iniciado sesion correctamente. ");
            this.jugadorLogueado = jugador;
       }
       return nuevoJugador;
    }

    public JugadorHumano getJugadorLogueado() {
        return jugadorLogueado;
    }

    public boolean iniciarSesion(){
        boolean sesionIniciada= false;
        String email = Utilidades.leerCadena("Introduce su email: ");

        //Comprobar si hay un jugador que ya exista que tenga este email
        if (!controladorJugadores.comprobarEmail(email)){
            Utilidades.imprimir("No hay un jugador registrado con este email, debe darse de alta primero");
        }else {
            //Comprobar contraseña
            String contrasenia = Utilidades.leerCadena("Introduce la contraseña: ");
            if (!controladorJugadores.comprobarContrasenia(email, contrasenia)){
                Utilidades.imprimir("Contraseña incorrecta. ");
            } else {
                this.jugadorLogueado =controladorJugadores.iniciarSesion(email, contrasenia);
                if (this.jugadorLogueado==null) Utilidades.imprimir("Ha ocurrido un error al iniciar sesion. ");
                else {
                    sesionIniciada=true;
                    Utilidades.imprimir("Ha iniciado sesion correctamente. ");
                }
            }
        }
        return sesionIniciada;
    }

    public boolean darBaja(){
        boolean darBaja= false;
        if (jugadorLogueado != null) {
            darBaja = controladorJugadores.darBaja(jugadorLogueado);
            if (darBaja) {
                this.jugadorLogueado = null;
                Utilidades.imprimir("Se ha dado de baja correctamente.");
            } else {
                Utilidades.imprimir("Error al dar de baja.");
            }
        } else {
          Utilidades.imprimir("No hay ningún jugador logueado.");
        }
        return darBaja;
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


