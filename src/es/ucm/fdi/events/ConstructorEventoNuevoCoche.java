package es.ucm.fdi.events;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCoche extends ConstructorEventoNuevoVehiculo {

	private String tipo ;
	
	public ConstructorEventoNuevoCoche() {
		super();
		// TODO Auto-generated constructor stub
		this.tipo = "type";
	}

	public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
				 section.getValue("type") != "car") 
		return null;
		else
			 return new EventoNuevoCoche(
			 ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
			 ConstructorEventos.identificadorValido(section, "id"),
			 ConstructorEventos.parseaIntNoNegativo(section, "max_speed", 0),
			 ConstructorEventos.toStringToList(section, "itinerary"),
			 ConstructorEventos.identificadorValido(section, "type"));
			//FALTA
	}

}