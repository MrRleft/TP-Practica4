package es.ucm.fdi.model;

import java.io.OutputStream;
import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Utils.SortedArrayList;
import es.ucm.fdi.events.Evento;

public class SimuladorTrafico {

	private MapaCarreteras mapa;
	private List<Evento> eventos;
	private int contadorTiempo;
	
	public SimuladorTrafico() {
		
		 this.mapa = new MapaCarreteras();
		 this.contadorTiempo = 0;
		 Comparator<Evento> cmp = new Comparator<Evento>() {
			//preguntar
			@Override
			public Evento compare(Evento o1, Evento o2) {
				if(o1.getTiempo() < o2.getTiempo())
					return o1;
				else
					return o2;
			}
			 
		 };
		 this.eventos = new SortedArrayList<Evento>(cmp); // estructura ordenada por �tiempo�
	}
	
	public void ejecuta(int pasosSimulacion, OutputStream ficheroSalida) throws ErrorDeSimulacion, ErrorCarga {
		 int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		 while (this.contadorTiempo <= limiteTiempo) {
			// ejecutar todos los eventos correspondienes a �this.contadorTiempo�
			for(Evento evento : eventos){
				if(evento.getTiempo() == limiteTiempo){
					try {
						evento.ejecuta(mapa);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new ErrorCarga();
					}
				}
			}
			 // actualizar �mapa�
			this.mapa.actualizar();
			 // escribir el informe en �ficheroSalida�, controlando que no sea null.
			
		 } 

	}
	
	public void insertaEvento(Evento e) {
		 // inserta un evento en �eventos�, controlando que el tiempo de
		 // ejecuci�n del evento sea menor que �contadorTiempo�
			if(e.getTiempo() < this.contadorTiempo){
			this.eventos.add(e);
			}
		}
}
