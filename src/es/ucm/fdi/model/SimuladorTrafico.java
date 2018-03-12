package es.ucm.fdi.model;

import java.io.OutputStream;
import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Exceptions.NotFoundException;
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

			@Override
			public int compare(Evento o1, Evento o2) {
				// TODO Auto-generated method stub
				//Hacer
				return 0;
			}
			 
		 };
		 this.eventos = new SortedArrayList<Evento>(cmp); // estructura ordenada por �tiempo�
	}
	
	public void ejecuta(int pasosSimulacion, OutputStream ficheroSalida) throws ErrorDeSimulacion {
		 int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		 while (this.contadorTiempo <= limiteTiempo) {
			// ejecutar todos los eventos correspondienes a �this.contadorTiempo�
			for(Evento evento : eventos){
				if(evento.getTiempo() == limiteTiempo){
					try {
						evento.ejecuta(mapa);
					} catch (NotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
