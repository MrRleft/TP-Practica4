package es.ucm.fdi.model.constructores;

import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.model.events.EventoNuevoCruceCongestionado;
import es.ucm.fdi.model.ini.IniSection;

public class ConstructorEventoNuevoCruceCongestionado extends ConstructorEventoNuevoCruce{
	
	public ConstructorEventoNuevoCruceCongestionado() {
		
		super();
	}
	
	 @Override
	 public Evento parser(IniSection section) {
			 if (!section.getTag().equals(this.etiqueta) || !section.getValue("type").equals("mc"))
				 return null;
			 else
				 return new EventoNuevoCruceCongestionado(
				 // extrae el valor del campo �time� en la secci�n
				 // 0 es el valor por defecto en caso de no especificar el tiempo
				 ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
				 // extrae el valor del campo �id� de la secci�n
				 ConstructorEventos.identificadorValido(section, "id"));
	}
}
