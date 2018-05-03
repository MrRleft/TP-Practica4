package es.ucm.fdi.model.events;

import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorCarga;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.Exceptions.InsertException;
import es.ucm.fdi.model.Exceptions.NotFoundException;

public abstract class Evento {
	
	protected Integer tiempo;
	
	public Evento(int tiempo) { 
		
		this.tiempo = tiempo;
		
	}
	
	public int getTiempo() {
		
		return this.tiempo;
	}
	
	 // cada clase que hereda implementa su m�todo ejecuta, que crear�
	 // el correspondiente objeto de la simulaci�n.

	 public abstract void ejecuta(MapaCarreteras mapa) throws NotFoundException, ErrorCarga, InsertException, ErrorDeSimulacion;
	 public abstract String toString();

	}
