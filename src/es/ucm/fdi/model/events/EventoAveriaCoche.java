package es.ucm.fdi.model.events;

import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorCarga;
import es.ucm.fdi.model.events.Evento;

public class EventoAveriaCoche extends Evento {
	String[] vehicle;
	int duration;

	public EventoAveriaCoche(int tiempo, String[] vehicles, int duration) {
		super(tiempo);
		// TODO Auto-generated constructor stub
		this.vehicle = vehicles;
		this.duration = duration;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorCarga {
		// TODO Auto-generated method stub
		for(String i: vehicle) {
			mapa.SetAveria(i, duration);
		}
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String elRetornable = "";
		String newline = System.getProperty("line.separator");
		elRetornable += "[make_vehicle_faulty]" + newline;
		//elRetornable += "time = "+ super.getTiempo()+ newline;
		//elRetornable += "vehicles = "+ this.vehicle+ newline;
		//elRetornable += "duration = " + this.duration+ newline;
		return elRetornable;
		
	}
	
	

}
