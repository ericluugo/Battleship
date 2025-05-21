package org.example.Dato.Jugadores;

import java.util.List;
import org.example.Vistas.IVistaAtacable;

public interface IJugable {

	public List<Integer> seleccionarCasilla();
	
	public String getId();

	public boolean decisionHabilidad();
	
}
