package org.example.Dato.Jugadores;

import java.util.List;
import org.example.Vistas.IVistaAtacable;

public interface IJugable {

	List<Integer> seleccionarCasilla();
	
	String getId();

	boolean decisionHabilidad();

	int pedirFila();

	
}
