package es.ucm.fdi.constructores;

import es.ucm.fdi.events.Evento;
import es.ucm.fdi.events.EventoNuevoCruceCircular;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruceCircular extends ConstructorEventoNuevoCruce {

	public ConstructorEventoNuevoCruceCircular(){
		super();
	}
	
	 @Override
	 public Evento parser(IniSection section) {
			 if (!section.getTag().equals(this.etiqueta) || !section.getValue("type").equals("rr"))
				 return null;
			 else
				 return new EventoNuevoCruceCircular(
				 // extrae el valor del campo �time� en la secci�n
				 // 0 es el valor por defecto en caso de no especificar el tiempo
				 ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
				 // extrae el valor del campo �id� de la secci�n
				 ConstructorEventos.identificadorValido(section, "id"),
				 ConstructorEventos.parseaIntNoNegativo(section, "min_time_slice", 0),
				 ConstructorEventos.parseaIntNoNegativo(section, "max_time_slice", 0));
	}
}
