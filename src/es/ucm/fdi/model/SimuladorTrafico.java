package es.ucm.fdi.model;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
				return 0;
			}
			 
		 };
		 this.eventos = new SortedArrayList<Evento>(); // estructura ordenada por “tiempo”
	}
	
	public void ejecuta(int pasosSimulacion, OutputStream ficheroSalida) {
		 int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		 while (this.contadorTiempo <= limiteTiempo) {
			// ejecutar todos los eventos correspondienes a “this.contadorTiempo”
			 // actualizar “mapa”
			 // escribir el informe en “ficheroSalida”, controlando que no sea null.
		 } 

	}
	
	public void insertaEvento(Evento e) {
		 // inserta un evento en “eventos”, controlando que el tiempo de
		 // ejecución del evento sea menor que “contadorTiempo”
			if(e.getTiempo() < this.contadorTiempo){
			this.eventos.add(e);
			}
		}
}
