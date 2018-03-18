package es.ucm.fdi.events;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Exceptions.InsertException;
import es.ucm.fdi.model.Coche;
import es.ucm.fdi.model.Cruce;
import es.ucm.fdi.model.MapaCarreteras;

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
	public void ejecuta(MapaCarreteras mapa) throws ErrorCarga, ErrorDeSimulacion, InsertException {
		// TODO Auto-generated method stub
		List<Cruce> iti = this.ayudaCarretera(this.itinerario, mapa);
		if(this.itinerario == null ){
			throw new ErrorCarga("Error al cargar el itinerario de " + id);
		 }
		 else{
			Coche coche = new Coche(id, duracionMaxima, iti, duracionMaxima, probAveria, duracionMaxima, semilla);
			mapa.addVehiculo(this.id, coche);
		 }

	}

	@Override
	public List<Cruce> ayudaCarretera(String[] stCr, MapaCarreteras mC) throws ErrorCarga {
		// TODO Auto-generated method stub
		return super.ayudaCarretera(stCr, mC);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// TODO Auto-generated method stub
		super.toString();
		System.out.println("type="+ "car");
		System.out.println("resistance="+ this.resistencia);
		System.out.println("fault_probability=" +this.probAveria);
		System.out.println("max_fault_duration=" + this.duracionMaxima);
		System.out.println("seed" + this.semilla);
		
		return sb.toString() ;
	}

}
