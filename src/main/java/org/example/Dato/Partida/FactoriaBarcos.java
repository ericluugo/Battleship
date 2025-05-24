package org.example.Dato.Partida;

import org.example.Dato.Barcos.*;

import java.util.List;

public class FactoriaBarcos {

    public static Barco crearBarco(String tipo, List<Casilla> casillas ) {
        switch (tipo) {
            case "Acorazado":
                return new Acorazado("Acorazado",casillas.size(), casillas);
            case "Submarino":
                return new Submarino("Submarino",casillas.size(), casillas);
            case "Patrullero":
                return new Patrullero("Patrullero", casillas.size(), casillas);
            case "Portaviones":
                return new Portaviones("Portaviones", casillas.size(), casillas);
            default:
                throw new IllegalArgumentException("Tipo de barco no válido: " + tipo);
        }
    }
}
