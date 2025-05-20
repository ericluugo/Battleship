package org.example.Logica;

import org.example.Dato.*;
import org.example.Dato.Barcos.Barco;
import org.example.Dato.Jugadores.IJugable;
import org.example.Dato.Partida.Partida;
import org.example.Dato.Partida.Tablero;
import org.example.Vistas.IVistaPartida;
import java.util.ArrayList;
import java.util.List;

public class ControladorPartida implements IControladorPartida {

    private List<Partida> partidas;
    private IVistaPartida vistaPartida;

    public ControladorPartida() {
        this.partidas = new ArrayList<>();
    }

    public Partida crearPartida(IJugable jugador1, IJugable jugador2) {
        Partida nuevaPartida = new Partida(jugador1,jugador2);
        return nuevaPartida;
    }

    @Override
    public String generarPuntuaciones(Partida partida) {
        return null;
    }

    @Override
    public void iniciarPartida(IJugable jugador1, IJugable jugador2) {
        Partida nuevaPartida = crearPartida(jugador1,jugador2);
        nuevaPartida.inicializarPartida();
        jugarPartida(nuevaPartida);
    }

    public String generarTableros(Partida partida){
        String vistaTableros = "Tablero 1 : " +partida.getJugador1().getId()+ "\n" + partida.getTablero1().mostrarTablero() + "\n" + "Tablero 2 : "+ partida.getJugador2().getId()+ "\n" + partida.getTablero2().mostrarTablero();
        return vistaTableros;
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
                List<Integer> coordenadasAtacar = partida.getJugador1().seleccionarCasilla(vistaPartida);
                Barco atacado = atacar(partidaEnJuego.getTablero2(),partidaEnJuego.getJugador1(),coordenadasAtacar);
                vistaPartida.imprimirCasillaAtacada(coordenadasAtacar);
                // imprimir por pantalla FALLO o Acierto y casilla atacada
                if (atacado != null && atacado.habilidadDisponible()){
                    vistaPartida.imprimirObjetoImpacto(atacado.getNombre());
                    if (partidaEnJuego.getJugador1().decisionHabilidad(vistaPartida)){
                        atacado.habilidad(partidaEnJuego.getTablero2(),vistaPartida);
                    }
                }
                vistaPartida.imprimirObjetoImpacto("");
            }else {
                // turno jugador2
                vistaPartida.imprimirTurno(partidaEnJuego.getJugador2().getId());
                //imprimir tableros
                generarTableros(partidaEnJuego);
                List<Integer> coordenadasAtacar = partida.getJugador2().seleccionarCasilla(vistaPartida);
                Barco atacado = atacar(partidaEnJuego.getTablero1(),partidaEnJuego.getJugador2(),coordenadasAtacar);
                // imprimir por pantalla FALLO o Acierto y casilla atacada
                vistaPartida.imprimirCasillaAtacada(coordenadasAtacar);
                if (atacado != null && atacado.habilidadDisponible()){
                        // imprimir por pantalla Tipo barco pegado
                    vistaPartida.imprimirObjetoImpacto(atacado.getNombre());
                    if (partidaEnJuego.getJugador2().decisionHabilidad(vistaPartida)){
                            atacado.habilidad(partidaEnJuego.getTablero1(),vistaPartida);
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

    public Barco atacar(Tablero tableroEnemigo, IJugable atacante,List<Integer> coordenadas) {
        Barco afectado = tableroEnemigo.recibirCoordenadas(coordenadas);
        return afectado;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public IVistaPartida getVistaPartida() {
        return vistaPartida;
    }

    public void setVistaPartida(IVistaPartida vistaPartida) {
        this.vistaPartida = vistaPartida;
    }
}
