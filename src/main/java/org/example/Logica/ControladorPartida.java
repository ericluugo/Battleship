package org.example.Logica;

import org.example.Dato.Barcos.Barco;
import org.example.Dato.Partida.Casilla;
import org.example.Dato.Jugadores.IJugable;
import org.example.Dato.Partida.Partida;
import org.example.Dato.Partida.Tablero;
import org.example.Vistas.IVistaAtacable;
import org.example.Vistas.IVistaPartida;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ControladorPartida implements IControladorPartida {
    private static ControladorPartida instancia;
    private final List<Partida> partidas;
    private IVistaAtacable vistaAtacable;
    private IVistaPartida vistaPartida;
    private Partida partidaEnJuego;

    private ControladorPartida() {
        this.partidas = new ArrayList<>();
    }

    public static ControladorPartida getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPartida();
        }
        return instancia;
    }

    private Partida crearPartida(IJugable jugador1, IJugable jugador2) {
        return new Partida(jugador1, jugador2);
    }

    public String getPuntuacionesGlobal() {
        StringBuilder line = new StringBuilder();
        List<Partida> sortedPartidas = new LinkedList<>(partidas);
        sortedPartidas.sort(Comparator.comparingDouble(Partida::getPuntosGanador));
        int nPartidas = Math.min(partidas.size(), 10);
        for (int i = 0; i < nPartidas; i++) {
            line.append(partidas.get(i).toString()).append("\n");
        }
        return line.toString();
    }

    public String getPuntuacionesJugador(String id) {
        StringBuilder line = new StringBuilder();
        List<Partida> listaPartidasJugador = new LinkedList<>();
        for (Partida partida : partidas) {
            if (partida.getJugador1().getId().equals(id)) {
                listaPartidasJugador.add(partida);
            }
        }
        listaPartidasJugador.sort(Comparator.comparingDouble(Partida::getPuntosJugador1)); // El jugador 1 siempre va a ser el humano
        for (Partida partida : listaPartidasJugador) {
            line.append(partida.toString()).append("\n");
        }
        return line.toString();
    }

    @Override
    public void iniciarPartida(IJugable jugador1, IJugable jugador2) throws Exception {
        Partida nuevaPartida = crearPartida(jugador1, jugador2);
        nuevaPartida.inicializarPartida();
        jugarPartida(nuevaPartida);
    }

    private String generarTableros(Partida partida) {
        return "Tablero 1 : " + partida.getJugador1().getId() + "\n" + partida.getTablero1().mostrarTablero() + "\n" + "Tablero 2 : " + partida.getJugador2().getId() + "\n" + partida.getTablero2().mostrarTablero();
    }

    private void finalizarPartida(Partida partida) {
        int puntuacionJugador1 = 0;
        int puntuacionJugador2 = 0;
        puntuacionJugador1 += puntosBarcos(partida.getTablero2());
        puntuacionJugador1 += puntosAgua(partida.getTablero2());
        puntuacionJugador2 += puntosBarcos(partida.getTablero1());
        puntuacionJugador2 += puntosAgua(partida.getTablero1());
        if (partida.getGanador() == partida.getJugador1()) {
            puntuacionJugador1 += 20;
            puntuacionJugador2 -= 20;
        } else {
            puntuacionJugador1 -= 20;
            puntuacionJugador2 += 20;
        }
        partida.setPuntosJugador1(puntuacionJugador1);
        partida.setPuntosJugador2(puntuacionJugador2);
        setPartidaEnJuego(null);
    }

    private int puntosAgua(Tablero tablero){
        int puntosRestados = 0;
        for (int indexI = 0; indexI < tablero.getNumFilas(); indexI++) {
            for (int indexJ = 0; indexJ < tablero.getNumColumnas(); indexJ++) {
                if (tablero.getTablero()[indexI][indexJ].isEstadoVisibilidad() && tablero.getTablero()[indexI][indexJ].isEstadoImpactado() && !tablero.getTablero()[indexI][indexJ].isOcupada()) {
                    puntosRestados--;
                }
            }
        }
        return puntosRestados;
    }

    private int puntosBarcos(Tablero tablero){
        int puntosSumados = 0;
        for (Barco barcoFinal : tablero.getBarcos()) {
            for (Casilla casilla : barcoFinal.getCasillas()) {
                if (casilla.isEstadoImpactado() && casilla.isOcupada()) {
                    if (barcoFinal.isVivo()) puntosSumados += 2;
                    else puntosSumados += 5;
                }
            }
        }
        return puntosSumados;
    }

    private void jugarPartida(Partida partida) throws Exception {
        setPartidaEnJuego(partida);
        //Bienvenida
        vistaPartida.imprimirBienvenida();
        // Partida iniciada : IJugable vs IJugable
        vistaPartida.imprimirRivalidad(partida.getJugador1().getId(), partida.getJugador2().getId());
        while (!partida.verificarFinPartida()) {
            if (partida.isTurno()) {
                // turno jugador1
                vistaPartida.imprimirTurno(partida.getJugador1().getId());
                // imprimir tableros
                vistaPartida.imprimir(generarTableros(partida));
                List<Integer> coordenadasAtacar = partida.getJugador1().seleccionarCasilla();
                if (!partida.getTablero2().isCasillaImpactada(coordenadasAtacar)) {
                    realizarAtaque(coordenadasAtacar, partida.getJugador2(), partida.getTablero1(),partida.getTablero2());
                } else
                    vistaPartida.imprimir("Ha atacado a una casilla impactada anteriormente,por ello, ha perdido el turno");
            } else {
                // turno jugador2
                vistaPartida.imprimirTurno(partida.getJugador2().getId());
                //imprimir tableros
                vistaPartida.imprimir(generarTableros(partida));
                List<Integer> coordenadasAtacar = partida.getJugador2().seleccionarCasilla();
                if (!partida.getTablero1().isCasillaImpactada(coordenadasAtacar)) {
                    realizarAtaque(coordenadasAtacar, partida.getJugador1(), partida.getTablero2(),partida.getTablero1());
                } else
                    vistaPartida.imprimir("Ha atacado a una casilla impactada anteriormente,por ello, ha perdido el turno");
            }
            partida.cambiarTurno();
            Thread.sleep(1500);
        }
        finalizarPartida(partida);
        //imprimir puntuaciones
        vistaPartida.imprimirPuntuacion(partida.getJugador1().getId(), partida.getPuntosJugador1(), partida.getJugador2().getId(), partida.getPuntosJugador2());
        vistaPartida.imprimirGanador(partida.getGanador().getId());
        partidas.add(partida);
    }

    private void contrataque(Barco atacado,IJugable jugador,Tablero tablero){
        vistaPartida.imprimirObjetoImpacto(atacado.getNombre());
        atacado.isBarcoMuerto();
        if (atacado.habilidadDisponible()) {
            if (jugador.decisionHabilidad()) {
                atacado.habilidad(tablero);
            }
        } else vistaPartida.imprimir("No tiene la habilidad disponible");
    }

    private void realizarAtaque(List<Integer> coordenadasAtacar,IJugable jugadorEnemigo,Tablero tableroPropio,Tablero tableroEnemigo){
        Barco atacado = atacar(tableroEnemigo, coordenadasAtacar);
        vistaPartida.imprimirCasillaAtacada(coordenadasAtacar);
        if (atacado != null) {
            contrataque(atacado, jugadorEnemigo, tableroPropio);
        } else {
            vistaPartida.imprimirObjetoImpacto("");
        }
    }

    Barco atacar(Tablero tableroEnemigo, List<Integer> coordenadas) {
        return tableroEnemigo.recibirCoordenadas(coordenadas);
    }

    public List<Integer> pedirCasilla() {
        return vistaAtacable.pedirCasilla();
    }

    public int pedirFila() {
        return vistaAtacable.pedirFila();
    }

    public boolean pedirDecision() {
        return vistaAtacable.pedirDecision();
    }

    public void setVistaAtacable(IVistaAtacable vistaAtacable) {
        this.vistaAtacable = vistaAtacable;
    }

    public IVistaPartida getVistaPartida() {
        return vistaPartida;
    }

    public void setVistaPartida(IVistaPartida vistaPartida) {
        this.vistaPartida = vistaPartida;
    }

    public Partida getPartidaEnJuego() {
        return partidaEnJuego;
    }

    public void setPartidaEnJuego(Partida partidaEnJuego) {
        this.partidaEnJuego = partidaEnJuego;
    }
}
