package org.example.Dato.Partida;

public class Casilla {

    private int columna;
    private int fila;
    private boolean estadoImpactado;
    private boolean estadoVisibilidad;
    private boolean ocupada;

    public Casilla(int columna, int fila) {
        this.fila = fila;
        this.columna = columna;
        this.estadoImpactado = false;
        this.estadoVisibilidad = false;
        this.ocupada = false;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    public boolean isEstadoImpactado() {
        return estadoImpactado;
    }

    public void setEstadoImpactado(boolean estadoImpactado) {
        this.estadoImpactado = estadoImpactado;
    }

    public boolean isEstadoVisibilidad() {
        return estadoVisibilidad;
    }

    public void setEstadoVisibilidad(boolean estadoVisibilidad) {
        this.estadoVisibilidad = estadoVisibilidad;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
