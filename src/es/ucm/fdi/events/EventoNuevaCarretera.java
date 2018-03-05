package es.ucm.fdi.events;


import es.ucm.fdi.model.Cruce;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.Exceptions.NotFoundException;
import es.ucm.fdi.events.Evento;


public class EventoNuevaCarretera extends Evento {
	
	 protected String id;
	 protected Integer velocidadMaxima;
	 protected Integer longitud;
	 protected String cruceOrigenId;
	 protected String cruceDestinoId;

	public EventoNuevaCarretera(int tiempo, String id, String src, String dest, int max_speed, int lenght) {
		super(tiempo);
		this.id = id;
		this.cruceOrigenId = src;
		this.cruceDestinoId = dest;
		this.velocidadMaxima = max_speed;
		this.longitud = lenght;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) {
		// TODO Auto-generated method stub
		Cruce cOrigen,cDestino;
		// obten cruce origen y cruce destino utilizando el mapa
		try {
			cOrigen = mapa.getCruce(cruceOrigenId);
			cDestino = mapa.getCruce(cruceDestinoId);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		 // crea la carretera
		
		
		
		 // añade al mapa la carretera
	}

}
