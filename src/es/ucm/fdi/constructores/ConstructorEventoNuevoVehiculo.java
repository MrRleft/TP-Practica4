package es.ucm.fdi.constructores;

import es.ucm.fdi.constructores.ConstructorEventos;
import es.ucm.fdi.events.Evento;
import es.ucm.fdi.events.EventoNuevoVehiculo;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoVehiculo extends ConstructorEventos {
	
	@SuppressWarnings("unused")
	private String[] valoresPorDefecto;

	public ConstructorEventoNuevoVehiculo() {
		 this.etiqueta = "new_vehicle";
		 this.claves = new String[] { "time", "id" ,"max_speed", "itinerary" };
		 this.valoresPorDefecto = new String[] { "", "", };
		
		}


	@Override
	public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
				 section.getValue("type") != null) 
		return null;
		else
			 return new EventoNuevoVehiculo(
			 ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
			 ConstructorEventos.identificadorValido(section, "id"),
			 ConstructorEventos.parseaIntNoNegativo(section, "max_speed", 0),
			 ConstructorEventos.toStringToList(section, "itinerary"));
			//FALTA
		}
	

}

