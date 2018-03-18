package es.ucm.fdi.events;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevaBicicleta extends EventoNuevoVehiculo {

	public EventoNuevaBicicleta(int tiempo, String id, int velocidadMaxima, String[] itinerario) {
		super(tiempo, id, velocidadMaxima, itinerario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorCarga, ErrorDeSimulacion {
		// TODO Auto-generated method stub
		super.ejecuta(mapa);
	}

	@Override
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
		 super.toString();
		 System.out.println("type= "+"bike");
		 return sb.toString();
		 
	 }

	
}
