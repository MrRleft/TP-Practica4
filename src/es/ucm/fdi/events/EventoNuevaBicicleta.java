package es.ucm.fdi.events;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Exceptions.InsertException;
import es.ucm.fdi.model.Bicicleta;
import es.ucm.fdi.model.Cruce;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevaBicicleta extends EventoNuevoVehiculo {

	public EventoNuevaBicicleta(int tiempo, String id, int velocidadMaxima, String[] itinerario) {
		super(tiempo, id, velocidadMaxima, itinerario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorCarga, ErrorDeSimulacion, InsertException {
		// TODO Auto-generated method stub
		 List<Cruce> iti = this.ayudaCarretera(this.itinerario, mapa);
		 
		 if(this.itinerario == null ){
			throw new ErrorCarga("Error al cargar el itinerario de " + id);
		 }
		 else{
			Bicicleta bicicleta = new Bicicleta(this.id,this.velocidadMaxima,iti);
				mapa.addVehiculo(id, bicicleta);
		 }
	}

	@Override
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
		 super.toString();
		 System.out.println("type="+"bike");
		 return sb.toString();
		 
	 }

	
}