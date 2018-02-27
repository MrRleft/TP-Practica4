package es.ucm.fdi.events;


import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.events.Evento;


public class EventoNuevaCarretera extends Evento {

	public EventoNuevaCarretera(int tiempo, String id, String src, String dest, int max_speed, int lenght) {
		super(tiempo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) {
		// TODO Auto-generated method stub

	}

}
