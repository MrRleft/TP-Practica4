package es.ucm.fdi.events;

import es.ucm.fdi.Exceptions.InsertException;
import es.ucm.fdi.Exceptions.NotFoundException;
import es.ucm.fdi.carreteras.Camino;
import es.ucm.fdi.model.Cruce;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevoCamino extends EventoNuevaCarretera {

	public EventoNuevoCamino(int tiempo, String id, String src, String dest, int max_speed, int lenght) {
		super(tiempo, id, src, dest, max_speed, lenght);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws NotFoundException, InsertException {
		// TODO Auto-generated method stub
		Cruce cOrigen = mapa.getCruce(cruceOrigenId);
		Cruce cDestino = mapa.getCruce(cruceDestinoId);
		// crea la carretera
		// añade al mapa la carretera	
		Camino camino = new Camino(this.id,this.longitud,this.velocidadMaxima,cOrigen,cDestino);		
		mapa.addCarretera(this.id, cOrigen, camino, cDestino);
	}

}
