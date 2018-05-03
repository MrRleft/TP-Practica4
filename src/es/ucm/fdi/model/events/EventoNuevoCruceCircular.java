package es.ucm.fdi.model.events;

import es.ucm.fdi.model.cruces.CruceCircular;
import es.ucm.fdi.model.cruces.CruceGenerico;

public class EventoNuevoCruceCircular extends EventoNuevoCruce{

	protected int maxValorIntervalo;
	protected int minValorIntervalo; 
	
	public EventoNuevoCruceCircular(int tiempo, String id, int minValorIntervalo, int maxValorIntervalo) {
		super(tiempo, id);
		this.maxValorIntervalo = maxValorIntervalo;
		this.minValorIntervalo = minValorIntervalo;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected CruceGenerico<?> creaCruce(){
		
		return new CruceCircular(this.id, this.minValorIntervalo, this.maxValorIntervalo);
	}
}
