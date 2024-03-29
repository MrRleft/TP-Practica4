package es.ucm.fdi.model.events;

import java.util.List;

import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorCarga;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.Exceptions.InsertException;
import es.ucm.fdi.model.cruces.CruceGenerico;
import es.ucm.fdi.model.vehiculos.Coche;

public class EventoNuevoCoche extends EventoNuevoVehiculo {
	
	private int resistencia;
	private double probAveria;
	private int duracionMaxima;
	private long semilla;
	
	public EventoNuevoCoche(int tiempo, String id, int velocidadMaxima, String[] itinerario,int resistencia,double probAveria,int duracionMaxima,long seed) {
		super(tiempo, id, velocidadMaxima, itinerario);
		// TODO Auto-generated constructor stub
		this.resistencia = resistencia;
		this.probAveria = probAveria;
		this.duracionMaxima = duracionMaxima;
		this.semilla = seed;
	}
	
	

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorCarga, ErrorDeSimulacion {
		// TODO Auto-generated method stub
		List<CruceGenerico<?>> iti = this.ayudaCarretera(this.itinerario, mapa);
		if(this.itinerario == null ){
			throw new ErrorCarga("Error al cargar el itinerario de " + id);
		 }
		 else{
			
			Coche coche = new Coche(id, velocidadMaxima, iti, resistencia, probAveria, duracionMaxima, semilla);
			try {
				mapa.addVehiculo(this.id, coche);
			} catch (InsertException e) {
				// TODO Auto-generated catch block
				throw new ErrorDeSimulacion("No se ha podido aniadir el vehiculo id " + this.id);
			}
		 }

	}

	@Override
	public List<CruceGenerico<?>> ayudaCarretera(String[] stCr, MapaCarreteras mC) throws ErrorCarga {
		// TODO Auto-generated method stub
		return super.ayudaCarretera(stCr, mC);
	}

	@Override
	public String toString() {
		String elRetornable = "";
		String newline = System.getProperty("line.separator");
		elRetornable += super.toString();
		elRetornable += "type="+ "car" + newline;
		//elRetornable += "resistance="+ this.resistencia + newline;
		//elRetornable += "fault_probability=" +this.probAveria + newline;
		//elRetornable += "max_fault_duration=" + this.duracionMaxima + newline;
		//elRetornable += "seed" + this.semilla + newline;
		
		return elRetornable ;
	}

}
