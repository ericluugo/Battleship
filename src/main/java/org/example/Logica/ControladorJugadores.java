package org.example.Logica;

import org.example.Dato.Jugadores.Jugador;
import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Dato.Jugadores.Maquina;
import org.example.Vistas.IVistaJugadores;
import utilidades.Cifrado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControladorJugadores implements IControladorJugadores {
    private static final String RUTA_LISTA_NEGRA = "src/main/resources/lista_negra.txt";
    private static ControladorJugadores instancia;
    private final List<String> listaNegra;
    private List<Jugador> listaJugadores;
    private IVistaJugadores vistaJugadores;

    private ControladorJugadores() {
        this.listaJugadores = new ArrayList<>();
        this.listaNegra = new ArrayList<>();
        inicializarListaNegra();
    }

    public static ControladorJugadores getInstancia() {
        if (instancia == null) {
            instancia = new ControladorJugadores();
        }
        return instancia;
    }

    public static void setInstancia(ControladorJugadores instancia) {
        ControladorJugadores.instancia = instancia;
    }

    @Override
    public void darAlta(String email, String nombre, String contrasenia, boolean esAdmin) throws Exception {
        //Comprobar que no hay alguien ya registrado con este email
        if (comprobarEmail(email)) {
            vistaJugadores.imprimir("Ya esta registrado con este email, inicie Sesion");
        } else {
            JugadorHumano jugador = new JugadorHumano(nombre, email, contrasenia, esAdmin);
            listaJugadores.add(jugador);
        }
    }

    public boolean darBaja(JugadorHumano jugadorHumano) {
        if (!jugadorHumano.isEsAdmin()) {
            return listaJugadores.remove(jugadorHumano);
        }
        return false;
    }

    @Override
    public JugadorHumano iniciarSesion(String email, String contrasenia) {
        Jugador jugador = null;
        int index = 0;
        while (index < listaJugadores.size() && jugador == null) {
            if (listaJugadores.get(index).comprobarEmailContrasenia(email, Cifrado.cifrar(contrasenia))) {
                jugador = listaJugadores.get(index);
            } else index++;
        }
        return (JugadorHumano) jugador;
    }

    public boolean comprobarEmail(String email) {
        boolean existe = false;
        int index = 0;
        while (index < listaJugadores.size() && !existe) {
            if (listaJugadores.get(index).getId().equalsIgnoreCase(email)) existe = true;
            else index++;
        }
        return existe;
    }

    public Maquina crearMaquina() throws Exception {
        Random random = new Random(System.currentTimeMillis());
        String nombre = "Maquina_FACIL_" + random.nextInt();
        Maquina maquina = new Maquina(nombre);
        listaJugadores.add(maquina);
        return maquina;
    }

    private void inicializarListaNegra() {
        String nombre;
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_LISTA_NEGRA))) {
            do {
                nombre = reader.readLine();
                listaNegra.add(nombre);
            } while (nombre != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public List<String> getListaNegra() {
        return listaNegra;
    }

    public String getRUTA_LISTA_NEGRA() {
        return RUTA_LISTA_NEGRA;
    }
}
