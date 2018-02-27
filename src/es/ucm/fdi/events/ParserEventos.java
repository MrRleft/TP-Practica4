package es.ucm.fdi.events;

import es.ucm.fdi.ini.IniSection;

public class ParserEventos {
	 private static ConstructorEventos[] eventos = {
		 new ConstructorEventoNuevoCruce(),
		 new ConstructorEventoNuevaCarretera(),
		 new ConstructorEventoNuevoVehiculo(),
		 new ConstructorEventoAveriaCoche()
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
