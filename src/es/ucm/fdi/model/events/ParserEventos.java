package es.ucm.fdi.model.events;

import es.ucm.fdi.model.constructores.ConstructorEventoAveriaCoche;
import es.ucm.fdi.model.constructores.ConstructorEventoNuevaAutopista;
import es.ucm.fdi.model.constructores.ConstructorEventoNuevaBicicleta;
import es.ucm.fdi.model.constructores.ConstructorEventoNuevaCarretera;
import es.ucm.fdi.model.constructores.ConstructorEventoNuevoCamino;
import es.ucm.fdi.model.constructores.ConstructorEventoNuevoCoche;
import es.ucm.fdi.model.constructores.ConstructorEventoNuevoCruce;
import es.ucm.fdi.model.constructores.ConstructorEventoNuevoCruceCircular;
import es.ucm.fdi.model.constructores.ConstructorEventoNuevoCruceCongestionado;
import es.ucm.fdi.model.constructores.ConstructorEventoNuevoVehiculo;
import es.ucm.fdi.model.constructores.ConstructorEventos;
import es.ucm.fdi.model.ini.IniSection;

public class ParserEventos {
	 private static ConstructorEventos[] eventos = {
		 new ConstructorEventoNuevoCruce(),
		 new ConstructorEventoNuevaCarretera(),
		 new ConstructorEventoNuevoVehiculo(),
		 new ConstructorEventoAveriaCoche(),
		 new ConstructorEventoNuevoCoche(),
		 new ConstructorEventoNuevaBicicleta(),
		 new ConstructorEventoNuevaAutopista(),
		 new ConstructorEventoNuevoCamino(),
		 new ConstructorEventoNuevoCruceCongestionado(),
		 new ConstructorEventoNuevoCruceCircular()
	 };
	// bucle de prueba y error
	 public static Evento parseaEvento(IniSection sec) {
		 
		 int i = 0;
		 boolean seguir = true;
		 Evento e = null;
		 while (i < ParserEventos.eventos.length && seguir ) {
			 // ConstructorEventos contiene el método parse(sec)
			 e = ParserEventos.eventos[i].parser(sec);
			 if (e!=null) seguir = false;
			 else i++;
		 }
		 return e;
		}
}
