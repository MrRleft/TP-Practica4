package es.ucm.fdi.events;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.Cruce;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevoCoche extends EventoNuevoVehiculo {
	protected String tipo;
	
	public EventoNuevoCoche(int tiempo, String id, int velocidadMaxima, String[] itinerario, String tipo) {
		super(tiempo, id, velocidadMaxima, itinerario);
		// TODO Auto-generated constructor stub
		this.tipo = tipo;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorCarga, ErrorDeSimulacion {
		// TODO Auto-generated method stub
		super.ejecuta(mapa);
	}

	@Override
	public List<Cruce> ayudaCarretera(String[] stCr, MapaCarreteras mC) throws ErrorCarga {
		// TODO Auto-generated method stub
		return super.ayudaCarretera(stCr, mC);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// TODO Auto-generated method stub
		super.toString();
		System.out.println("type= "+ this.tipo);
		return sb.toString() ;
	}

}
