package org.example.Logica;

import org.example.Dato.Jugadores.Jugador;
import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Dato.Jugadores.Maquina;
import org.example.Vistas.IVistaJugadores;
import servidor.ObtencionDeRol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControladorJugadores implements IControladorJugadores{
    private List<Jugador> listaJugadores;
    IVistaJugadores vistaJugadores;

    public ControladorJugadores() {
        this.listaJugadores = new ArrayList<>();
    }



    @Override
    public JugadorHumano darAlta(String email, String nombre, String contrasenia) {
        boolean esAdmin= !ObtencionDeRol.get_UPM_AccountRol(email).equals("ALUMNO");
        JugadorHumano jugador = new JugadorHumano(nombre, email, contrasenia, esAdmin);
        listaJugadores.add(jugador);
        return jugador;
    }


    public boolean darBaja(JugadorHumano jugadorHumano) {
        return listaJugadores.remove(jugadorHumano);
    }

    @Override
    public JugadorHumano iniciarSesion(String email, String contrasenia) {
        Jugador jugador=null;
        int index =0;
        while (index< listaJugadores.size() && jugador==null){
            if (listaJugadores.get(index).comprobarEmailContrasenia(email, contrasenia)){
                jugador = listaJugadores.get(index);
            } else index++;
        }
        return (JugadorHumano) jugador;
    }
    @Override
    public boolean comprobarEmail(String email){
        boolean existe = false;
        int index =0;
        while (index<listaJugadores.size() && !existe){
            if (listaJugadores.get(index).existeEmail(email)) existe=true;
            else index++;
        }
        return existe;
    }
    @Override
    public boolean comprobarContrasenia(String email, String contrasenia){
        boolean correcto =false;
        int index =0;
        while (index<listaJugadores.size() && !correcto){
            if (listaJugadores.get(index).comprobarEmailContrasenia(email, contrasenia)) correcto=true;
            else index++;
        }
        return correcto;
    }

    public void crearMaquina(){
        StringBuilder st = new StringBuilder("Maquina_FACIL");
        Random random = new Random(System.currentTimeMillis());
        st.append(random);
        Maquina maquina = new Maquina(st.toString());
        listaJugadores.add(maquina);
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public IVistaJugadores getVistaJugadores() {
        return vistaJugadores;
    }

    public void setVistaJugadores(IVistaJugadores vistaJugadores) {
        this.vistaJugadores = vistaJugadores;
    }
}
