package es.ucm.fdi.events;

public class EventoNuevaAutopista extends EventoNuevaCarretera {

	private int carriles;
	public EventoNuevaAutopista(int tiempo, String id, String src, String dest, int max_speed, int lenght,int carriles) {
		super(tiempo, id, src, dest, max_speed, lenght);
		// TODO Auto-generated constructor stub
	}

}
