package es.ucm.fdi.model.events;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorCarga;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.Exceptions.InsertException;
import es.ucm.fdi.model.Exceptions.NotFoundException;
import es.ucm.fdi.model.cruces.CruceGenerico;
import es.ucm.fdi.model.vehiculos.Vehiculo;

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
	 public void ejecuta(MapaCarreteras mapa) throws ErrorCarga, ErrorDeSimulacion {
		
		//List<Cruce> iti = ParserCarreteras.parseaListaCruces(this.itinerario,mapa);
		 // si iti es null o tiene menos de dos cruces lanzar excepci�n
		 List<CruceGenerico<?>> iti = this.ayudaCarretera(this.itinerario, mapa);
		 
			 if(this.itinerario == null ){
				throw new ErrorCarga("Error al cargar el itinerario de " + id);
			 }
			 else{
				Vehiculo vehiculo = new Vehiculo(this.id,this.velocidadMaxima,iti);
				try {
					mapa.addVehiculo(id, vehiculo);
				} catch (InsertException e) {
					// TODO Auto-generated catch block
					throw new ErrorCarga("Problema al aniadir " + id + "Al mapa de carreteras");
				}
			 }
		 
		 // en otro caso crear el veh�culo y a�adirlo al mapa.
	 }
	 
	 public List<CruceGenerico<?>> ayudaCarretera(String[] stCr,MapaCarreteras mC) throws ErrorCarga{
		 List<CruceGenerico<?>> auxCarr = new ArrayList<>();
		 for (String j : stCr ){
			try {
				CruceGenerico<?> x = mC.getCruce(j);
				auxCarr.add(x);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				throw new ErrorCarga("Problemas al a�adir el itinerario");
			}
		 }
		return auxCarr;
	 }
	 
	 @Override
	 public String toString() {
		String elRetornable = "";
		String newline = System.getProperty("line.separator");
		elRetornable += "[new_vehicle]" + newline;
		//elRetornable += "time=" + this.tiempo + newline;
		//elRetornable += "id= "+ this.id + newline;
		//elRetornable += "max_speed= "+ this.velocidadMaxima + newline;
		//elRetornable += "itinerary= "+ this.itinerario + newline;
		return elRetornable;
		 
	 }


}
