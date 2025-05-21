package org.example.Vistas;

import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.IControladorJugadores;
import org.example.ModelException;
import servidor.Autenticacion;

import java.util.Scanner;

public class VistaJugadores implements IVistaJugadores{
    private JugadorHumano jugadorLogueado;
    private IControladorJugadores controladorJugadores;
    private static final Scanner teclado = new Scanner(System.in);



    public VistaJugadores() {
        this.jugadorLogueado =null;

    }

    public boolean solicitudDatosAlta() throws ModelException {
        boolean nuevoJugador = true;
        String nombre = leerCadena("Introduzca su nombre: ");
       String email = leerCadena("Introduzca su email ");

       if (controladorJugadores.comprobarEmail(email)){
           imprimir("Ya esta registrado con este email, inicie Sesion");
           nuevoJugador = false;
           //Comprobar que se usuario de la UPM
       } else if(!Autenticacion.existeCuentaUPMStatic(email)){
           imprimir("Este email no pertenece a la UPM, no se puede registrar");
           nuevoJugador = false;
       } else{
           //Si esta todo correcto compruebo el rol
            String contrasenia = leerCadena("Introduzca su contraseña: ");
            JugadorHumano jugador = controladorJugadores.darAlta(email, nombre, contrasenia, false);
            imprimir("Ha iniciado sesion correctamente. ");
            this.jugadorLogueado = jugador;
       }
       return nuevoJugador;
    }

    public JugadorHumano getJugadorLogueado() {
        return jugadorLogueado;
    }

    public boolean iniciarSesion(){
        boolean sesionIniciada= false;
        String email = leerCadena("Introduce su email: ");

        //Comprobar si hay un jugador que ya exista que tenga este email
        if (!controladorJugadores.comprobarEmail(email)){
            imprimir("No hay un jugador registrado con este email, debe darse de alta primero");
        }else {
            //Comprobar contraseña
            String contrasenia = leerCadena("Introduce la contraseña: ");
            if (!controladorJugadores.comprobarContrasenia(email, contrasenia)){
                imprimir("Contraseña incorrecta. ");
            } else {
                this.jugadorLogueado =controladorJugadores.iniciarSesion(email, contrasenia);
                if (this.jugadorLogueado==null) imprimir("Ha ocurrido un error al iniciar sesion. ");
                else {
                    sesionIniciada=true;
                    imprimir("Ha iniciado sesion correctamente. ");
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
                imprimir("Se ha dado de baja correctamente.");
            } else {
                imprimir("Error al dar de baja.");
            }
        } else {
          imprimir("No hay ningún jugador logueado.");
        }
        return darBaja;
    }

    public static String leerCadena(String s){
        String string = "";
        do {
            System.out.println(s);
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


