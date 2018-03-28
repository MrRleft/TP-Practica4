package es.ucm.fdi.events;

import es.ucm.fdi.cruces.CruceCongestionado;
import es.ucm.fdi.cruces.CruceGenerico;

public class EventoNuevoCruceCongestionado extends EventoNuevoCruce {

	public EventoNuevoCruceCongestionado(int tiempo, String id) {
		super(tiempo, id);
		// TODO Auto-generated constructor stub
	}
	
	 @Override protected CruceGenerico<?> creaCruce(){  
		
		 return new CruceCongestionado(this.id);
	 }

}
