package es.ucm.fdi.events;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCoche extends ConstructorEventoNuevoVehiculo {

	
	public ConstructorEventoNuevoCoche() {
		//super();
		// TODO Auto-generated constructor stub
		this.etiqueta = "new_vehicle";
	}

	public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
				!section.getValue("type").equals("car")) 
		return null;
		else
			 return new EventoNuevoCoche(
			 ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
			 ConstructorEventos.identificadorValido(section, "id"),
			 ConstructorEventos.parseaIntNoNegativo(section, "max_speed", 0),
			 ConstructorEventos.toStringToList(section, "itinerary"),
			 ConstructorEventos.parseaIntNoNegativo(section, "resistance", 1),
			 ConstructorEventos.parseaDouble(section, "fault_probability"),
			 ConstructorEventos.parseaIntNoNegativo(section, "max_fault_duration",1),
			 ConstructorEventos.parseaLong(section, "seed")
			 );
			//FALTA
	}

}