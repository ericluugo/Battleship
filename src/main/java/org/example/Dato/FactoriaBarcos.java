package org.example.Dato;

import org.example.Dato.Barcos.*;

import java.util.List;

public class FactoriaBarcos {

    public static Barco crearBarco(String tipo, List<Casilla> casillas) {
        switch (tipo) {
            case "Acorazado":
                return new Acorazado("Acorazado",3 , casillas);
            case "Submarino":
                return new Submarino("Submarino",3, casillas);
            case "Patrullero":
                return new Patrullero("Patrullero", 2, casillas);
            case "Portaviones":
                return new Portaviones("Portaviones", 4, casillas);
            default:
                throw new IllegalArgumentException("Tipo de barco no válido: " + tipo);
        }
    }
}
