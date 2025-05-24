package org.example.Dato.Jugadores;

import java.util.List;

public interface IJugable {

    List<Integer> seleccionarCasilla();

    String getId();

    boolean decisionHabilidad();

    int seleccionarFila();


}
