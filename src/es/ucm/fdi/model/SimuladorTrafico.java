package es.ucm.fdi.model;

import java.io.OutputStream;
import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Exceptions.InsertException;
import es.ucm.fdi.Exceptions.NotFoundException;
import es.ucm.fdi.Utils.SortedArrayList;
import es.ucm.fdi.events.Evento;

public class SimuladorTrafico {

	private MapaCarreteras mapa;
	private List<Evento> eventos;
	private int contadorTiempo;
	private int pasosSim;
	
	public SimuladorTrafico(int lt) {
		
		 this.mapa = new MapaCarreteras();
		 this.contadorTiempo = 0;
		 Comparator<Evento> cmp = new Comparator<Evento>() {
			//preguntar
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
		 this.eventos = new SortedArrayList<Evento>(cmp); // estructura ordenada por �tiempo�
		 this.pasosSim = lt;
	}
	
	public void ejecuta(int pasosSimulacion, OutputStream ficheroSalida) throws ErrorDeSimulacion, ErrorCarga, NotFoundException, InsertException {
		
		int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		 while (this.contadorTiempo <= limiteTiempo) {
			// ejecutar todos los eventos correspondienes a �this.contadorTiempo�
			 System.out.println("-------------PASO " + this.contadorTiempo + "----------------");
			for(Evento evento : eventos){
				if(evento.getTiempo() == this.contadorTiempo){
						evento.ejecuta(mapa);
				}	
			}
			for(Carretera r : this.mapa.getCarreteras())
				r.avanza();
			 // actualizar �mapa�
			this.mapa.actualizar();
			 // escribir el informe en �ficheroSalida�, controlando que no sea null.
			
			System.out.println(this.mapa.generateReport(this.contadorTiempo));
			this.contadorTiempo++;
			}

	}
	
	public void insertaEvento(Evento e) {
		 // inserta un evento en �eventos�, controlando que el tiempo de
		 // ejecuci�n del evento sea menor que �contadorTiempo�
			if(e.getTiempo() < this.pasosSim){
			this.eventos.add(e);
			}
		}
}
