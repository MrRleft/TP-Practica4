package es.ucm.fdi.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.events.Evento;
import es.ucm.fdi.events.ParserEventos;
import es.ucm.fdi.ini.Ini;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.SimuladorTrafico;

public class Controlador {
	
	private SimuladorTrafico simulador;
	private OutputStream ficheroSalida;
	private InputStream ficheroEntrada;
	private int pasosSimulacion;
	
	 public Controlador(SimuladorTrafico sim, Integer limiteTiempo, InputStream is, OutputStream os) {

		 this.simulador = sim;
		 this.ficheroEntrada = is;
		 this.ficheroSalida = os;
		 this.pasosSimulacion = limiteTiempo;
	}

	public void ejecuta() {
		 try {
			 
			 this.cargaEventos(this.ficheroEntrada);
			 this.simulador.ejecuta(pasosSimulacion,this.ficheroSalida);
		 }
		 catch(Exception r) {
			 
			 System.err.println(r.getMessage());
		 }
	 }
	 
	 private void cargaEventos(InputStream inStream) throws ErrorDeSimulacion {
		 Ini ini;
		 try {
			 // lee el fichero y carga su atributo iniSections
			 ini = new Ini(inStream);
		 }
		 catch (IOException e) {
			 throw new ErrorDeSimulacion("Error en la lectura de eventos: " + e);
		 }
		
		 for (IniSection sec : ini.getSections()) {
			 Evento e = ParserEventos.parseaEvento(sec);
			 if (e != null)
				 this.simulador.insertaEvento(e);
			 else
				 throw new ErrorDeSimulacion("Evento desconocido: " + sec.getTag());
		}
		 
	 }
	}

/*
 
Parse para la parte uno
for (IniSection sec : ini.getSections()) {
	 Evento e = ParserEventos.parseaEvento(sec);
	 if (e != null)
		 this.simulador.insertaEvento(e);
	 else
		 throw new ErrorDeSimulacion("Evento desconocido: " + sec.getTag());
}

Parse parte0
for (IniSection sec : ini.getSections()) {
			 // parseamos la sección para ver a que evento corresponde
				 EventoNuevoVehiculo e = EventoNuevoVehiculo.parseaEvento(sec);
				 this.simulador.insertaEvento(e);
		 }
*/