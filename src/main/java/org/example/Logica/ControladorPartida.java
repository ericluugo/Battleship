package org.example.Logica;

import org.example.Dato.*;
import org.example.Dato.Barcos.Barco;
import org.example.Dato.Jugadores.IJugable;
import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Dato.Partida.Partida;
import org.example.Dato.Partida.Tablero;
import org.example.Vistas.IVistaAtacable;
import org.example.Vistas.IVistaPartida;

import java.util.*;

public class ControladorPartida implements IControladorPartida {
    private static ControladorPartida instancia;
    private List<Partida> partidas;
    private IVistaAtacable vistaAtacable;
    private IVistaPartida vistaPartida;

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
        return new Partida(jugador1,jugador2);
    }

    @Override
    public String generarPuntuaciones(Partida partida) {
        return null;
    }

    public String getPuntuacionesGlobal(){
        String line = "";
        List<Partida> sortedPartidas = new LinkedList<>(partidas);
        sortedPartidas.sort(Comparator.comparingDouble(Partida::getPuntosGanador));
        for (int i = 0; i<10; i++){
            line += partidas.get(i).toString()+"\n";
        }
        return line;
    }

    public String getPuntuacionesJugador(JugadorHumano jugador){
        String line = "";
        List<Partida> listaPartidasJugador = new LinkedList<>();
        for(Partida partida:partidas){
            if (partida.getJugador1()==jugador || partida.getJugador2()==jugador){
                listaPartidasJugador.add(partida);
            }
        }
        listaPartidasJugador.sort(Comparator.comparingDouble(Partida::getPuntosJugador1)); // El jugador 1 siempre va a ser el humano
        for (Partida partida:listaPartidasJugador){
            line += partida.toString()+"\n";
        }
        return line;
    }

    @Override
    public void iniciarPartida(IJugable jugador1, IJugable jugador2) {
        Partida nuevaPartida = crearPartida(jugador1,jugador2);
        nuevaPartida.inicializarPartida();
        jugarPartida(nuevaPartida);
    }

    public String generarTableros(Partida partida){
        return "Tablero 1 : " +partida.getJugador1().getId()+ "\n" + partida.getTablero1().mostrarTablero() + "\n" + "Tablero 2 : "+ partida.getJugador2().getId()+ "\n" + partida.getTablero2().mostrarTablero();
    }

    public void finalizarPartida(Partida partida) {
        int puntuacionJugador1 = 0;
        int puntuacionJugador2 = 0;
        for(Barco barcoFinal : partida.getTablero1().getBarcos()){
            for (Casilla casilla : barcoFinal.getCasillas()) {
                if (casilla.isEstadoImpactado() && casilla.isOcupada()) {
                    if (barcoFinal.isVivo()) puntuacionJugador1 += 2;
                    else puntuacionJugador1 += 5;
                }
            }
            for(int indexI =0; indexI<partida.getTablero2().getTablero().length;indexI++){
                for (int indexJ=0; indexJ<partida.getTablero2().getTablero()[indexI].length;indexJ++){
                    if (partida.getTablero2().getTablero()[indexI][indexJ].isEstadoVisibilidad() && partida.getTablero2().getTablero()[indexI][indexJ].isEstadoImpactado() && !partida.getTablero2().getTablero()[indexI][indexJ].isOcupada()){
                        puntuacionJugador1--;
                    }
                }
            }
        }
        for(Barco barcoFinal : partida.getTablero2().getBarcos()){
            for (Casilla casilla : barcoFinal.getCasillas()) {
                if (casilla.isEstadoImpactado() && casilla.isOcupada()) {
                    if (barcoFinal.isVivo()) puntuacionJugador2 += 2;
                    else puntuacionJugador2 += 5;
                }
            }
            for(int indexI =0; indexI<partida.getTablero1().getTablero().length;indexI++){
                for (int indexJ=0; indexJ<partida.getTablero1().getTablero()[indexI].length;indexJ++){
                    if (partida.getTablero1().getTablero()[indexI][indexJ].isEstadoVisibilidad() && partida.getTablero1().getTablero()[indexI][indexJ].isEstadoImpactado() && !partida.getTablero1().getTablero()[indexI][indexJ].isOcupada()){
                        puntuacionJugador1--;
                    }
                }
            }
        }
        if (partida.getGanador() == partida.getJugador1()){
            puntuacionJugador1 += 20;
            puntuacionJugador2 -= 20;
        } else {
            puntuacionJugador1 -= 20;
            puntuacionJugador2 += 20;
        }
        partida.setPuntosJugador1(puntuacionJugador1);
        partida.setPuntosJugador2(puntuacionJugador2);
    }

    public void jugarPartida(Partida partida) {
        Partida partidaEnJuego = partida;
        //Bienvenida
        vistaPartida.imprimirBienvenida();
        // Partida iniciada : IJugable vs IJugable
        vistaPartida.imprimirRivalidad(partidaEnJuego.getJugador1().getId(),partidaEnJuego.getJugador2().getId());
        while (!partidaEnJuego.verificarFinPartida()){
            if (partidaEnJuego.isTurno()){
                // turno jugador1
                vistaPartida.imprimirTurno(partidaEnJuego.getJugador1().getId());
                // imprimir tableros
                generarTableros(partidaEnJuego);
                List<Integer> coordenadasAtacar = partida.getJugador1().seleccionarCasilla();
                Barco atacado = atacar(partidaEnJuego.getTablero2(),coordenadasAtacar);
                vistaPartida.imprimirCasillaAtacada(coordenadasAtacar);
                // imprimir por pantalla FALLO o Acierto y casilla atacada
                if (atacado != null && atacado.habilidadDisponible()){
                    vistaPartida.imprimirObjetoImpacto(atacado.getNombre());
                    if (partidaEnJuego.getJugador1().decisionHabilidad()){
                        atacado.habilidad(partidaEnJuego.getTablero2());
                    }
                }
                vistaPartida.imprimirObjetoImpacto("");
            }else {
                // turno jugador2
                vistaPartida.imprimirTurno(partidaEnJuego.getJugador2().getId());
                //imprimir tableros
                generarTableros(partidaEnJuego);
                List<Integer> coordenadasAtacar = partida.getJugador2().seleccionarCasilla();
                Barco atacado = atacar(partidaEnJuego.getTablero1(),coordenadasAtacar);
                // imprimir por pantalla FALLO o Acierto y casilla atacada
                vistaPartida.imprimirCasillaAtacada(coordenadasAtacar);
                if (atacado != null && atacado.habilidadDisponible()){
                        // imprimir por pantalla Tipo barco pegado
                    vistaPartida.imprimirObjetoImpacto(atacado.getNombre());
                    if (partidaEnJuego.getJugador2().decisionHabilidad()){
                            atacado.habilidad(partidaEnJuego.getTablero1());
                    }
                }
                vistaPartida.imprimirObjetoImpacto("");
            }
            partidaEnJuego.cambiarTurno();
        }
        finalizarPartida(partidaEnJuego);
        //imprimir puntuaciones
        vistaPartida.imprimirPuntuacion(partidaEnJuego.getJugador1().getId(),partidaEnJuego.getPuntosJugador1(),partidaEnJuego.getJugador2().getId(),partidaEnJuego.getPuntosJugador2());
        //imprimir ganador
        vistaPartida.imprimirGanador(partidaEnJuego.getGanador().getId());
        partidas.add(partidaEnJuego);
    }

    public Barco atacar(Tablero tableroEnemigo,List<Integer> coordenadas) {
        Barco afectado = tableroEnemigo.recibirCoordenadas(coordenadas);
        return afectado;
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
}
