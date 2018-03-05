package es.ucm.fdi.events;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruce extends ConstructorEventos {


		 private String[] valoresPorDefecto;
		public ConstructorEventoNuevoCruce() {
			 this.etiqueta = "new_junction";
			 this.claves = new String[] { "time", "id" };
			 this.valoresPorDefecto = new String[] { "", "", };
		}
		 @Override
		 public Evento parser(IniSection section) {
			 if (!section.getTag().equals(this.etiqueta) ||section.getValue("type") != null)
				 return null;
			 else
				 return new EventoNuevoCruce(
				 // extrae el valor del campo “time” en la sección
				 // 0 es el valor por defecto en caso de no especificar el tiempo
				 ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
				 // extrae el valor del campo “id” de la sección
				 ConstructorEventos.identificadorValido(section, "id"));
		 }
		 @Override

		 public String toString() { return "New Junction"; }

}

