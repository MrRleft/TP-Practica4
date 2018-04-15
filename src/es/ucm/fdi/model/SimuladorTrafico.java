package es.ucm.fdi.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Exceptions.InsertException;
import es.ucm.fdi.Exceptions.NotFoundException;
import es.ucm.fdi.MVC.Observador;
import es.ucm.fdi.MVC.ObservadorSimuladorTrafico;
import es.ucm.fdi.Utils.SortedArrayList;
import es.ucm.fdi.events.Evento;

public class SimuladorTrafico implements Observador<ObservadorSimuladorTrafico>{

	private MapaCarreteras mapa;
	private List<ObservadorSimuladorTrafico> observadores;
	private List<Evento> eventos;
	private int contadorTiempo;
	private int pasosSim;
	
	public SimuladorTrafico(int lt)  {
		
		 this.mapa = new MapaCarreteras();
		 this.contadorTiempo = 0;
		 Comparator<Evento> cmp = new Comparator<Evento>() {

			@Override
			public int compare(Evento o1, Evento o2) {
				if(o1.getTiempo() < o2.getTiempo())
					return -1;
				else if(o1.getTiempo() > o2.getTiempo())
					return 1;
				else 
					return 0;
			}
			 
		 };
		 this.observadores = new ArrayList<>();
		 this.eventos = new SortedArrayList<Evento>(cmp); // estructura ordenada por �tiempo�
		 this.pasosSim = lt;
	}
	
	public void ejecuta(int pasosSimulacion, OutputStream ficheroSalida) throws ErrorDeSimulacion, ErrorCarga, NotFoundException, InsertException {
		
		String output = "";
		int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		while (this.contadorTiempo <= limiteTiempo) {
			 
			for(Evento evento : eventos){
				if(evento.getTiempo() == this.contadorTiempo){
						evento.ejecuta(mapa);
				}	
			}
			try {
				this.mapa.actualizar();
				this.notificaAvanza();
			}
			catch(ErrorDeSimulacion e) {
				this.notificaError(e);
				throw new ErrorDeSimulacion();
			}
			output += this.mapa.generateReport(this.contadorTiempo);
			System.out.println(output);
			this.contadorTiempo++;
				
		}
		try {
			 ficheroSalida.write(output.getBytes());
		} catch (IOException e) {
			throw new ErrorDeSimulacion("Error al grabarse en el fichero out");
		}

	}
	
	private void notificaAvanza() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addObservador(ObservadorSimuladorTrafico o) {
	
		if (o != null && !this.observadores.contains(o)) {
			this.observadores.add(o);
		}
	}
	
	@Override
	public void removeObservador(ObservadorSimuladorTrafico o) {
		
		if (o != null && this.observadores.contains(o)) {
			this.observadores.remove(o);
		}
	}
	
	public void insertaEvento(Evento e) throws ErrorDeSimulacion {
		
		if (e != null) {
			if (e.getTiempo() < this.contadorTiempo) {
				ErrorDeSimulacion err = new ErrorDeSimulacion("Se ha introducido un evento antes de tiempo");
				this.notificaError(err);
				throw err;
			}
			else {
				this.eventos.add(e);
				this.notificaNuevoEvento(); // se notifica a los observadores
			}
		}
		else {
			ErrorDeSimulacion err = new ErrorDeSimulacion("El evento no existe");
			this.notificaError(err); // se notifica a los observadores
			throw err;
		}
	}
	
	private void notificaError(ErrorDeSimulacion err) {
		// TODO Auto-generated method stub
		for(int i = 0; i< this.observadores.size();++i){
			this.observadores.get(i).errorSimulador(this.contadorTiempo, this.mapa, eventos, err);
		}
	}

	private void notificaNuevoEvento() {
		for (ObservadorSimuladorTrafico o : this.observadores) {
			o.addEvento(this.contadorTiempo,this.mapa,this.eventos);
		 }
	}

	public void reinicia() {
		// TODO Auto-generated method stub
		// El simulador tendr� un m�todo reinicia, que reinicia todos sus atributos y
		//notifca a los observadores dicha acci�n. 
		Por HACER
		
		this.notificaReinicia();
		
	}

	private void notificaReinicia() {
		// TODO Auto-generated method stub
	
		for(int i = 0; i< this.observadores.size();++i){
			this.observadores.get(i).reinicia(contadorTiempo, mapa, eventos);
		}
	}
}
