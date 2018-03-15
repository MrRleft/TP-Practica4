package es.ucm.fdi.events;


import es.ucm.fdi.model.Carretera;
import es.ucm.fdi.model.Cruce;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.Exceptions.InsertException;
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
	public void ejecuta(MapaCarreteras mapa) throws NotFoundException, InsertException {
		// TODO Auto-generated method stub
		
		// obten cruce origen y cruce destino utilizando el mapa
		
		Cruce cOrigen = mapa.getCruce(cruceOrigenId);
		Cruce cDestino = mapa.getCruce(cruceDestinoId);
		// crea la carretera
		// añade al mapa la carretera	
		Carretera carretera = new Carretera(this.id,this.longitud,this.velocidadMaxima,cOrigen,cDestino);
		
		mapa.addCarretera(this.id, cOrigen, carretera, cDestino);
		
	}
	
	public String toString() {
		 StringBuilder sb = new StringBuilder();
		 System.out.println("[new_road]");
		 System.out.println("time =" + super.getTiempo());
		 System.out.println("id = "+ this.id);
		 System.out.println("src = "+ this.cruceOrigenId);
		 System.out.println("dest = "+ this.cruceDestinoId);
		 System.out.println("max_speed = "+ this.velocidadMaxima);
		 System.out.println("length + = " + this.longitud);
		 return sb.toString();
	}
	

}
