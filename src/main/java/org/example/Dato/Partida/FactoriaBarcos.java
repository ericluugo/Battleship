package org.example.Dato.Partida;

import org.example.Dato.Barcos.*;

import java.util.List;

public class FactoriaBarcos {

    public static Barco crearBarco(String tipo, List<Casilla> casillas,int numCasillas ) {
        switch (tipo) {
            case "Acorazado":
                return new Acorazado("Acorazado",numCasillas, casillas);
            case "Submarino":
                return new Submarino("Submarino",numCasillas, casillas);
            case "Patrullero":
                return new Patrullero("Patrullero", numCasillas, casillas);
            case "Portaviones":
                return new Portaviones("Portaviones", numCasillas, casillas);
            default:
                throw new IllegalArgumentException("Tipo de barco no válido: " + tipo);
        }
    }
}
