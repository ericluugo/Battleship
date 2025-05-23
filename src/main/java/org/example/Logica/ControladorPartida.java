package org.example.Logica;

import org.example.Dato.Barcos.Barco;
import org.example.Dato.Casilla;
import org.example.Dato.Jugadores.IJugable;
import org.example.Dato.Jugadores.JugadorHumano;
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
    private List<Partida> partidas;
    private IVistaAtacable vistaAtacable;
    private IVistaPartida vistaPartida;
    private Partida partidaJugable;

    private ControladorPartida() {
        this.partidas = new ArrayList<>();
    }

    public static ControladorPartida getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPartida();
        }
        return instancia;
    }

    public Partida crearPartida(IJugable jugador1, IJugable jugador2) {
        return new Partida(jugador1, jugador2);
    }

    @Override
    public String generarPuntuaciones(Partida partida) {
        return null;
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

    public String getPuntuacionesJugador(JugadorHumano jugador) {
        StringBuilder line = new StringBuilder();
        List<Partida> listaPartidasJugador = new LinkedList<>();
        for (Partida partida : partidas) {
            if (partida.getJugador1() == jugador || partida.getJugador2() == jugador) {
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

    public String generarTableros(Partida partida) {
        return "Tablero 1 : " + partida.getJugador1().getId() + "\n" + partida.getTablero1().mostrarTablero() + "\n" + "Tablero 2 : " + partida.getJugador2().getId() + "\n" + partida.getTablero2().mostrarTablero();
    }

    public void finalizarPartida(Partida partida) {
        int puntuacionJugador1 = 0;
        int puntuacionJugador2 = 0;
        for (Barco barcoFinal : partida.getTablero2().getBarcos()) {
            for (Casilla casilla : barcoFinal.getCasillas()) {
                if (casilla.isEstadoImpactado() && casilla.isOcupada()) {
                    if (barcoFinal.isVivo()) puntuacionJugador1 += 2;
                    else puntuacionJugador1 += 5;
                }
            }
            for (int indexI = 0; indexI < partida.getTablero2().getTablero().length; indexI++) {
                for (int indexJ = 0; indexJ < partida.getTablero2().getTablero()[indexI].length; indexJ++) {
                    if (partida.getTablero2().getTablero()[indexI][indexJ].isEstadoVisibilidad() && partida.getTablero2().getTablero()[indexI][indexJ].isEstadoImpactado() && !partida.getTablero2().getTablero()[indexI][indexJ].isOcupada()) {
                        puntuacionJugador1--;
                    }
                }
            }
        }
        for (Barco barcoFinal : partida.getTablero1().getBarcos()) {
            for (Casilla casilla : barcoFinal.getCasillas()) {
                if (casilla.isEstadoImpactado() && casilla.isOcupada()) {
                    if (barcoFinal.isVivo()) puntuacionJugador2 += 2;
                    else puntuacionJugador2 += 5;
                }
            }
            for (int indexI = 0; indexI < partida.getTablero1().getTablero().length; indexI++) {
                for (int indexJ = 0; indexJ < partida.getTablero1().getTablero()[indexI].length; indexJ++) {
                    if (partida.getTablero1().getTablero()[indexI][indexJ].isEstadoVisibilidad() && partida.getTablero1().getTablero()[indexI][indexJ].isEstadoImpactado() && !partida.getTablero1().getTablero()[indexI][indexJ].isOcupada()) {
                        puntuacionJugador2--;
                    }
                }
            }
        }
        if (partida.getGanador() == partida.getJugador1()) {
            puntuacionJugador1 += 20;
            puntuacionJugador2 -= 20;
        } else {
            puntuacionJugador1 -= 20;
            puntuacionJugador2 += 20;
        }
        partida.setPuntosJugador1(puntuacionJugador1);
        partida.setPuntosJugador2(puntuacionJugador2);
        setPartidaJugable(null);
    }

    public void jugarPartida(Partida partida) throws Exception {
        setPartidaJugable(partida);
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
                    Barco atacado = atacar(partida.getTablero2(), coordenadasAtacar);
                    vistaPartida.imprimirCasillaAtacada(coordenadasAtacar);
                    // imprimir por pantalla FALLO o Acierto y casilla atacada
                    if (atacado != null) {
                        vistaPartida.imprimirObjetoImpacto(atacado.getNombre());
                        atacado.isBarcoMuerto();
                        if (atacado.habilidadDisponible()) {
                            if (partida.getJugador2().decisionHabilidad()) {
                                atacado.habilidad(partida.getTablero1());
                            }
                        } else vistaPartida.imprimir("No tiene la habilidad disponible");
                    } else {
                        vistaPartida.imprimirObjetoImpacto("");
                    }
                } else
                    vistaPartida.imprimir("Ha atacado a una casilla impactada anteriormente,por ello, ha perdido el turno");
            } else {
                // turno jugador2
                vistaPartida.imprimirTurno(partida.getJugador2().getId());
                //imprimir tableros
                vistaPartida.imprimir(generarTableros(partida));
                List<Integer> coordenadasAtacar = partida.getJugador2().seleccionarCasilla();
                if (!partida.getTablero1().isCasillaImpactada(coordenadasAtacar)) {
                    Barco atacado = atacar(partida.getTablero1(), coordenadasAtacar);
                    // imprimir por pantalla FALLO o Acierto y casilla atacada
                    vistaPartida.imprimirCasillaAtacada(coordenadasAtacar);
                    if (atacado != null) {
                        // imprimir por pantalla Tipo barco pegado
                        vistaPartida.imprimirObjetoImpacto(atacado.getNombre());
                        atacado.isBarcoMuerto();
                        if (atacado.habilidadDisponible()) {
                            if (partida.getJugador1().decisionHabilidad()) {
                                atacado.habilidad(partida.getTablero2());
                            }
                        } else vistaPartida.imprimir("No tiene la habilidad disponible");
                    } else {
                        vistaPartida.imprimirObjetoImpacto("");
                    }
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

    public Barco atacar(Tablero tableroEnemigo, List<Integer> coordenadas) {
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

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public IVistaAtacable getVistaAtacable() {
        return vistaAtacable;
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

    public Partida getPartidaJugable() {
        return partidaJugable;
    }

    public void setPartidaJugable(Partida partidaJugable) {
        this.partidaJugable = partidaJugable;
    }
}
