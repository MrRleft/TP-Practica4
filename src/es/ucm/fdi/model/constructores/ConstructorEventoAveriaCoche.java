package es.ucm.fdi.model.constructores;

import es.ucm.fdi.model.constructores.ConstructorEventos;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.model.events.EventoAveriaCoche;
import es.ucm.fdi.model.ini.IniSection;

public class ConstructorEventoAveriaCoche extends ConstructorEventos {
	
	
	@SuppressWarnings("unused")
	private String[] valoresPorDefecto;

	public ConstructorEventoAveriaCoche() {
		 this.etiqueta = "make_vehicle_faulty";
		 this.claves = new String[] { "time", "vehicles","duration" };
		 this.valoresPorDefecto = new String[] { "", "", ""};
		}

	@Override
	public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
				 section.getValue("type") != null) 
		return null;
		else
			 return new EventoAveriaCoche(
			 ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
			 ConstructorEventos.toStringToList(section, "vehicles"),
			 ConstructorEventos.parseaIntNoNegativo(section, "duration", 0));
	}

}
