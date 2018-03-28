package es.ucm.fdi.events;

import es.ucm.fdi.constructores.ConstructorEventoAveriaCoche;
import es.ucm.fdi.constructores.ConstructorEventoNuevaAutopista;
import es.ucm.fdi.constructores.ConstructorEventoNuevaBicicleta;
import es.ucm.fdi.constructores.ConstructorEventoNuevaCarretera;
import es.ucm.fdi.constructores.ConstructorEventoNuevoCamino;
import es.ucm.fdi.constructores.ConstructorEventoNuevoCoche;
import es.ucm.fdi.constructores.ConstructorEventoNuevoCruce;
import es.ucm.fdi.constructores.ConstructorEventoNuevoCruceCircular;
import es.ucm.fdi.constructores.ConstructorEventoNuevoCruceCongestionado;
import es.ucm.fdi.constructores.ConstructorEventoNuevoVehiculo;
import es.ucm.fdi.constructores.ConstructorEventos;
import es.ucm.fdi.ini.IniSection;

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
