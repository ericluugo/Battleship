package org.example.Dato.Jugadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Maquina extends Jugador {
    private final Random rand = new Random();
    private String nombreMaquina;

    public Maquina(String nombre){
        this.nombreMaquina = nombre;
    }

    @Override
    public List<Integer> seleccionarCasilla() {
        List<Integer> coordenadas = new ArrayList<>();
        Integer coorY = rand.nextInt(10);
        Integer coorX = rand.nextInt(10);
        coordenadas.add(coorY);
        coordenadas.add(coorX);
        return coordenadas;
    }

    @Override
    public String getId() {
        return nombreMaquina;
    }

    @Override
    public boolean decisionHabilidad() {
        return true;
    }

    @Override
    public int seleccionarFila() {
        return rand.nextInt(10);
    }
}
