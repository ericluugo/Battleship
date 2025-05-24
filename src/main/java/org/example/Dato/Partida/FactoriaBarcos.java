package org.example.Dato.Partida;

import org.example.Dato.Barcos.*;

import java.util.List;

public class FactoriaBarcos {

    private static final int NUM_CASILLAS_ACORAZADO = 3;
    private static final int NUM_CASILLAS_SUBMARINO = 3;
    private static final int NUM_CASILLAS_PATRULLERO = 2;
    private static final int NUM_CASILLAS_PORTAVIONES = 4;

    public static Barco crearBarco(String tipo, List<Casilla> casillas,int ) {
        switch (tipo) {
            case "Acorazado":
                return new Acorazado("Acorazado",NUM_CASILLAS_ACORAZADO, casillas);
            case "Submarino":
                return new Submarino("Submarino", NUM_CASILLAS_SUBMARINO, casillas);
            case "Patrullero":
                return new Patrullero("Patrullero", NUM_CASILLAS_PATRULLERO, casillas);
            case "Portaviones":
                return new Portaviones("Portaviones", NUM_CASILLAS_PORTAVIONES, casillas);
            default:
                throw new IllegalArgumentException("Tipo de barco no válido: " + tipo);
        }
    }
}
