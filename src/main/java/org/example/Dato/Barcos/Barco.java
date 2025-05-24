package org.example.Dato.Barcos;

import org.example.Dato.Partida.Casilla;
import org.example.Dato.Partida.Tablero;
import org.example.Logica.ControladorPartida;

import java.util.List;

public abstract class Barco {

    private String nombre;
    private int numCasillas;
    private List<Casilla> casillas;
    private boolean vivo;

    public Barco(String nombre, int numCasillas, List<Casilla> casillas) {
        this.nombre = nombre;
        this.numCasillas = numCasillas;
        this.casillas = casillas;
        this.vivo = true;
    }

    public abstract void habilidad(Tablero tableroEnemigo);

    public abstract boolean habilidadDisponible();

    public void isBarcoMuerto() {
        boolean hundido = false;
        int indexCasilla = 0;
        int casillasImpactadas = 0;
        while (!hundido && indexCasilla < casillas.size()) {
            if (casillas.get(indexCasilla).isEstadoImpactado()) {
                casillasImpactadas++;
            }
            indexCasilla++;
        }
        if (casillasImpactadas == numCasillas) {
            setVivo(false);
            ControladorPartida.getInstancia().getVistaPartida().imprimir("El " + getNombre() + " ha muerto");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Casilla> getCasillas() {
        return casillas;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
}
