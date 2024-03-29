package es.ucm.fdi.model.events;

import java.util.List;

import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorCarga;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.Exceptions.InsertException;
import es.ucm.fdi.model.cruces.CruceGenerico;
import es.ucm.fdi.model.vehiculos.Bicicleta;

public class EventoNuevaBicicleta extends EventoNuevoVehiculo {

	public EventoNuevaBicicleta(int tiempo, String id, int velocidadMaxima, String[] itinerario) {
		super(tiempo, id, velocidadMaxima, itinerario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorCarga, ErrorDeSimulacion {
		// TODO Auto-generated method stub
		 List<CruceGenerico<?>> iti = this.ayudaCarretera(this.itinerario, mapa);
		 
		 if(this.itinerario == null ){
			throw new ErrorCarga("Error al cargar el itinerario de " + id);
		 }
		 else{
			Bicicleta bicicleta = new Bicicleta(this.id,this.velocidadMaxima,iti);
				try {
					mapa.addVehiculo(id, bicicleta);
				} catch (InsertException e) {
					// TODO Auto-generated catch block
					throw new ErrorCarga("Problema al aniadir " + id + "Al mapa de carreteras");
				}
		 }
	}

	@Override
	 public String toString() {
		String elRetornable = "";
		String newline = System.getProperty("line.separator");
		elRetornable += super.toString();
		elRetornable += "type="+"bike" + newline;
		return elRetornable;
		 
	 }

	
}
