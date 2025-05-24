package org.example.Vistas;

import java.util.List;

public interface IVistaPartida {

    void imprimirBienvenida();

    void imprimirRivalidad(String id1, String id2);

    void imprimirTurno(String id);

    void imprimirCasillaAtacada(List<Integer> casillaAtacada);

    void imprimirObjetoImpacto(String nombreBarco);

    void imprimirPuntuacion(String id1, int puntuacion1, String id2, int puntuacion2);

    void imprimirGanador(String id);

    void imprimir(String msg);

}
