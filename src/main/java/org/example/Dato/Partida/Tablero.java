package org.example.Dato.Partida;

import org.example.Dato.Barcos.Barco;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {

    private final Random rand = new Random();
    private Casilla[][] tablero;
    private int numFilas;
    private int numColumnas;
    private List<Barco> barcos;


    public Tablero(int numFilas, int numColumnas) {
        this.tablero = new Casilla[numFilas][numColumnas];
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.barcos = new ArrayList<>();
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                this.tablero[i][j] = new Casilla(i, j);
            }
        }
    }

    public String mostrarTablero() {
        StringBuilder sb = new StringBuilder();

        sb.append("   ");
        for (int col = 0; col < 10; col++) {
            sb.append(col).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < 10; i++) {
            sb.append(i).append("  ");
            for (int j = 0; j < 10; j++) {
                Casilla casilla = tablero[i][j];

                if (!casilla.isEstadoVisibilidad()) {
                    sb.append("  "); //agua
                } else if (casilla.isEstadoImpactado() && casilla.isOcupada()) {
                    sb.append("X "); //acierto
                } else if (casilla.isEstadoImpactado() && !casilla.isOcupada()) {
                    sb.append("O "); //fallo
                } else if (!casilla.isEstadoImpactado() && !casilla.isOcupada()) {
                    sb.append(". "); // revelado vacio
                } else if (!casilla.isEstadoImpactado() && casilla.isOcupada()) {
                    sb.append("… "); // revelado con barco
                }
            }
            sb.append("\n");
        }
        sb.append("' ' agua, 'X' impacto, 'O' agua impactada, '.' agua sin impactar, '…' barco sin impactar.\n");
        return sb.toString();
    }

    public Barco recibirCoordenadas(List<Integer> coordenadas) {
        Barco barcoImpactado = null;
        tablero[coordenadas.get(0)][coordenadas.get(1)].setEstadoVisibilidad(true);
        tablero[coordenadas.get(0)][coordenadas.get(1)].setEstadoImpactado(true);
        for (Barco barco : barcos) {
            int index = 0;
            while (index < barco.getCasillas().size() && barcoImpactado == null) {//(Y,X)
                if (barco.getCasillas().get(index).getFila() == coordenadas.get(1) && barco.getCasillas().get(index).getColumna() == coordenadas.get(0)) {
                    barcoImpactado = barco;
                }
                index++;
            }
        }
        return barcoImpactado;
    }

    public boolean todosBarcosMuertos() {
        for (Barco barco : barcos) {
            if (barco.isVivo()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCasillaImpactada(List<Integer> ataque) {
        return tablero[ataque.get(0)][ataque.get(1)].isEstadoImpactado() && tablero[ataque.get(0)][ataque.get(1)].isEstadoVisibilidad();
    }

    public List<Casilla> colocacionBarcos(int tamanio) {
        List<Casilla> seleccionadas = new ArrayList<>();
        boolean hecho = false;

        while (!hecho) {
            seleccionadas.clear();
            int fila = rand.nextInt(numFilas);
            int columna = rand.nextInt(numColumnas);
            int direccion = rand.nextInt(2); // 0 = vertical, 1 = horizontal

            boolean valido = true;
            for (int i = 0; i < tamanio; i++) {
                int f = fila + (direccion == 0 ? i : 0);
                int c = columna + (direccion == 1 ? i : 0);

                if (f >= numFilas || c >= numColumnas || tablero[f][c].isOcupada()) {
                    valido = false;
                } else {
                    seleccionadas.add(tablero[f][c]);
                }
            }

            // Solo si todas las casillas fueron válidas y suficientes
            if (valido && seleccionadas.size() == tamanio) {
                for (Casilla casilla : seleccionadas) {
                    casilla.setOcupada(true);
                }
                hecho = true;
            }
        }
        return seleccionadas;
    }

    public int getNumFilas() {
        return numFilas;
    }

    public void setNumFilas(int numFilas) {
        this.numFilas = numFilas;
    }

    public int getNumColumnas() {
        return numColumnas;
    }

    public void setNumColumnas(int numColumnas) {
        this.numColumnas = numColumnas;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(List<Barco> barcos) {
        this.barcos = barcos;
    }

    public Casilla[][] getTablero() {
        return tablero;
    }

    public void setTablero(Casilla[][] tablero) {
        this.tablero = tablero;
    }
}