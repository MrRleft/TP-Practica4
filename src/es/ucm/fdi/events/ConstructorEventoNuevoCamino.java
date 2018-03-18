package es.ucm.fdi.events;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCamino extends ConstructorEventoNuevaCarretera {

	public ConstructorEventoNuevoCamino() {
		this.etiqueta = "new_road";
	}

	@Override
	public Evento parser(IniSection section) {
		// TODO Auto-generated method stub
		if (!section.getTag().equals(this.etiqueta) ||
				 !section.getValue("type").equals("dirt")) 
		return null;
		else
			 return new EventoNuevoCamino(
			 ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
			 ConstructorEventos.identificadorValido(section, "id"),
			 ConstructorEventos.identificadorValido(section, "src"),
			 ConstructorEventos.identificadorValido(section, "dest"),
			 ConstructorEventos.parseaIntNoNegativo(section, "max_speed", 1), 
			 ConstructorEventos.parseaIntNoNegativo(section, "length", 1)
			 );
	}
	

}
