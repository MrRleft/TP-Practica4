package es.ucm.fdi.events;

import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.events.Evento;

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
	public void ejecuta(MapaCarreteras mapa) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		System.out.println("[make_vehicle_faulty]");
		System.out.println("time = "+ super.getTiempo());
		System.out.println("vehicles = "+ this.vehicle);
		System.out.println("duration = " + this.duration);
		return sb.toString();
		
	}
	
	

}
