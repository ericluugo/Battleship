package org.example.Vistas;

import java.util.List;

public interface IVistaAtacable {
	
	public List<Integer> pedirCasilla();

	public boolean pedirDecision();

	public int pedirFila();

	public void imprimirBienvenida();

	public void imprimirRivalidad(String id1,String id2);

	public void imprimirTurno(String id);

	public void imprimirCasillaAtacada(List<Integer>casillaAtacada);

	public void imprimirObjetoImpacto(String nombreBarco);

	public void imprimirResultadoContraataque(Object object);

	public void imprimirFilaRevelo(int fila);

	public void imprimirReparacion();

	public void imprimirPuntuacion(String id1,int puntuacion1,String id2,int puntuacion2);

	public void imprimirGanador(String id);
}
