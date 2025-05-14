package org.example.Dato.Jugadores;

import java.util.List;
import org.example.Vistas.IVistaPartida;

public interface IJugable {

	public List<Integer> seleccionarCasilla(IVistaPartida vistaAtacable);
	
	public String getId();

	public boolean decisionHabilidad(IVistaPartida vistaPartida);
	
}
