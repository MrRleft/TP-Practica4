package es.ucm.fdi.model.events;


import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.InsertException;
import es.ucm.fdi.model.Exceptions.NotFoundException;
import es.ucm.fdi.model.carreteras.Carretera;
import es.ucm.fdi.model.cruces.CruceGenerico;
import es.ucm.fdi.model.events.Evento;


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
		
		CruceGenerico<?> cOrigen = mapa.getCruce(cruceOrigenId);
		CruceGenerico<?> cDestino = mapa.getCruce(cruceDestinoId);
		// crea la carretera
		// añade al mapa la carretera	
		Carretera carretera = new Carretera(this.id,this.longitud,this.velocidadMaxima,cOrigen,cDestino);
		
		mapa.addCarretera(this.id, cOrigen, carretera, cDestino);
		
	}
	
	public String toString() {
		 String elRetornable = "";
		 String newline = System.getProperty("line.separator");
		 elRetornable += "[new_road]" + newline;
		 elRetornable += "time =" + super.getTiempo() + newline;
		 elRetornable += "id = "+ this.id + newline;
		 elRetornable += "src = "+ this.cruceOrigenId + newline;
		 elRetornable += "dest = " + this.cruceDestinoId + newline;
		 elRetornable += "max_speed = "+ this.velocidadMaxima+ newline;
		 elRetornable += "length + = " + this.longitud + newline;
		 return elRetornable;
	}
	

}
