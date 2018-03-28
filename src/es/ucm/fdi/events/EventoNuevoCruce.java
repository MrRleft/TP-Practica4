package es.ucm.fdi.events;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.InsertException;
import es.ucm.fdi.cruces.Cruce;
import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.model.MapaCarreteras;


public class EventoNuevoCruce extends Evento {
	
	protected String id;

	public EventoNuevoCruce(int tiempo, String id) {
		super(tiempo);
		this.id= id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorCarga {

		// TODO Auto-generated method stub
		try {
			CruceGenerico<?> cruce = creaCruce();
			mapa.addCruce(this.id, cruce);
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			throw new ErrorCarga();
		}
	}

	protected CruceGenerico<?> creaCruce(){
		
		return new Cruce(this.id);
	}
	
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
		 System.out.println("[new_junction]");
		 System.out.println("time= " + super.getTiempo());
		 System.out.println("id = "+ this.id);
		 return sb.toString();
	
	 }
	 
}

