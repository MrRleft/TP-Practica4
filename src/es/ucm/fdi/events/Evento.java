package es.ucm.fdi.events;

import es.ucm.fdi.model.MapaCarreteras;

public abstract class Evento {
	
	protected Integer tiempo;
	
	public Evento(int tiempo) { 
		
		this.tiempo = tiempo;
		
	}
	
	public int getTiempo() {
		
		return this.tiempo;
	}
	
	 // cada clase que hereda implementa su método ejecuta, que creará
	 // el correspondiente objeto de la simulación.

	 public abstract void ejecuta(MapaCarreteras mapa);

	}
