package es.ucm.fdi.events;

import java.util.List;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevoVehiculo extends Evento{

	 protected String id;
	 protected Integer velocidadMaxima;
	 protected String itinerario;

	 public EventoNuevoVehiculo(int tiempo, String id, int velocidadMaxima, String itinerario)
	 {
		 super(tiempo);
		 this.id = id;
		 this.velocidadMaxima = velocidadMaxima;
		 this.itinerario = itinerario;
	 }
	
	 @Override
	 public void ejecuta(MapaCarreteras mapa) {
		 

		 // si iti es null o tiene menos de dos cruces lanzar excepci�n
		 // en otro caso crear el veh�culo y a�adirlo al mapa.
	 }

	 @Override
	 public String toString() {
		 
		 return null;
		 
	 }


}
