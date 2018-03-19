package es.ucm.fdi.constructores;

import es.ucm.fdi.events.Evento;
import es.ucm.fdi.events.EventoNuevaBicicleta;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaBicicleta extends ConstructorEventoNuevoVehiculo {

	public ConstructorEventoNuevaBicicleta() {
		// TODO Auto-generated constructor stub
		this.etiqueta = "new_vehicle";
	}

	@Override
	public Evento parser(IniSection section) {
		// TODO Auto-generated method stub
		if (!section.getTag().equals(this.etiqueta) ||
				!section.getValue("type").equals("bike")) 
		return null;
		else 
			return new EventoNuevaBicicleta(
					 ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
					 ConstructorEventos.identificadorValido(section, "id"),
					 ConstructorEventos.parseaIntNoNegativo(section, "max_speed", 0),
					 ConstructorEventos.toStringToList(section, "itinerary"));
					
	}

	
}
