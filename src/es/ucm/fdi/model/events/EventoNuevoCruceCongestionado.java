package es.ucm.fdi.model.events;

import es.ucm.fdi.model.cruces.CruceCongestionado;
import es.ucm.fdi.model.cruces.CruceGenerico;

public class EventoNuevoCruceCongestionado extends EventoNuevoCruce {

	public EventoNuevoCruceCongestionado(int tiempo, String id) {
		super(tiempo, id);
		// TODO Auto-generated constructor stub
	}
	
	 @Override protected CruceGenerico<?> creaCruce(){  
		
		 return new CruceCongestionado(this.id);
	 }

}
