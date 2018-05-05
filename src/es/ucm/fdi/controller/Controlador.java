package es.ucm.fdi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import es.ucm.fdi.model.SimuladorTrafico;
import es.ucm.fdi.model.Exceptions.ErrorCarga;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.Exceptions.InsertException;
import es.ucm.fdi.model.Exceptions.NotFoundException;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.model.events.ParserEventos;
import es.ucm.fdi.model.ini.Ini;
import es.ucm.fdi.model.ini.IniSection;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;

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
			 this.simulador.ejecuta(this.pasosSimulacion,this.ficheroSalida);
		 }
		 catch(Exception r) {
			 
			 System.err.println(r.getMessage());
		 }
	 }
	
	public void ejecuta(int pasos) {
		 
		try {
			this.simulador.ejecuta(pasos,this.ficheroSalida);
		} 
		catch (ErrorDeSimulacion | ErrorCarga | NotFoundException | InsertException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
	}
	
	public void reinicia() {
	
		this.simulador.reinicia(); 
		
	}
	 
	public void addObserver(ObservadorSimuladorTrafico o) {
	
		this.simulador.addObservador(o);
		
	}
	
	public void removeObserver(ObservadorSimuladorTrafico o) {
		
		this.simulador.removeObservador(o);
		
	}
	 public void cargaEventos(InputStream inStream) throws ErrorDeSimulacion {
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

