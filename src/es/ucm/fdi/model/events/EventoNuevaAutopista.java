package es.ucm.fdi.model.events;

import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.InsertException;
import es.ucm.fdi.model.Exceptions.NotFoundException;
import es.ucm.fdi.model.carreteras.Autopista;
import es.ucm.fdi.model.cruces.CruceGenerico;

public class EventoNuevaAutopista extends EventoNuevaCarretera {

	private int carriles;
	public EventoNuevaAutopista(int tiempo, String id, String src, String dest, int max_speed, int lenght,int carriles) {
		super(tiempo, id, src, dest, max_speed, lenght);
		// TODO Auto-generated constructor stub
		this.carriles = carriles;
	}
	@Override
	public void ejecuta(MapaCarreteras mapa) throws NotFoundException, InsertException {
		// TODO Auto-generated method stub
		CruceGenerico<?> cOrigen = mapa.getCruce(cruceOrigenId);
		CruceGenerico<?> cDestino = mapa.getCruce(cruceDestinoId);
		// crea la carretera
		// añade al mapa la carretera	
		Autopista autopista = new Autopista(this.id,this.longitud,this.velocidadMaxima,cOrigen,cDestino,this.carriles);		
		mapa.addCarretera(this.id, cOrigen, autopista, cDestino);
	}
	
	
	
}
