package es.ucm.fdi.events;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Exceptions.InsertException;
import es.ucm.fdi.Exceptions.NotFoundException;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Cruce;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Vehiculo;

public class EventoNuevoVehiculo extends Evento{

	 protected String id;
	 protected Integer velocidadMaxima;
	 protected String[] itinerario;

	 public EventoNuevoVehiculo(int tiempo, String id, int velocidadMaxima, String[] itinerario)
	 {
		 super(tiempo);
		 this.id = id;
		 this.velocidadMaxima = velocidadMaxima;
		 this.itinerario = itinerario;
	 }
	
	 @Override
	 public void ejecuta(MapaCarreteras mapa) {
		
		//List<Cruce> iti = ParserCarreteras.parseaListaCruces(this.itinerario,mapa);
		 // si iti es null o tiene menos de dos cruces lanzar excepci�n
		 List<Cruce> iti = this.ayudaCarretera(this.itinerario, mapa);
		 
			 if(this.itinerario == null ){
				 try {
					throw new NotFoundException();
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 else{
				try {
					Vehiculo vehiculo = new Vehiculo(this.id,this.velocidadMaxima,iti);
					try {
						mapa.addVehiculo(id, vehiculo);
					} catch (InsertException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ErrorDeSimulacion e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ErrorCarga e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		 
		 // en otro caso crear el veh�culo y a�adirlo al mapa.
	 }
	 
	 public List<Cruce> ayudaCarretera(String[] stCr,MapaCarreteras mC){
		 List<Cruce> auxCarr = new ArrayList<Cruce>();
		 for (String j : stCr ){
			try {
				Cruce x = mC.getCruce(j);
				auxCarr.add(x);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return auxCarr;
		 
		 
	 }
	 
	 @Override
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
		 System.out.println("[new_vehicle]");
		 System.out.println("time=" + this.tiempo);
		 System.out.println("id= "+ this.id);
		 System.out.println("max_speed= "+ this.velocidadMaxima);
		 System.out.println("itinerary= "+ this.itinerario);
		 return sb.toString();
		 
	 }


}
