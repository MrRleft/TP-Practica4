package es.ucm.fdi.vehiculos;

import java.util.List;
import java.util.Random;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.cruces.Cruce;
import es.ucm.fdi.ini.IniSection;

public class Coche extends Vehiculo {
	
	private int resistenciaKm;
	private double probabilidadDeAveria;
	private int duracionMaxima;
	private long seed;
	private Random numAleatorio;
	//private String tipo;

	public Coche(String id, int velocidadMaxima, List<Cruce> iti,int resistencia,double probAveria,int duracionMaxima,long seed) throws ErrorCarga {
		super(id, velocidadMaxima, iti);
		// TODO Auto-generated constructor stub
		this.resistenciaKm = resistencia;
		this.probabilidadDeAveria = probAveria;
		this.duracionMaxima = duracionMaxima;
		this.seed = seed;
		this.numAleatorio = new Random(this.seed);
	}

	@Override
	public void avanza() throws ErrorDeSimulacion {
		// TODO Auto-generated method stub
		if(this.tiempoAveria == 0 && this.kilometraje >= this.resistenciaKm && this.numAleatorio.nextDouble()< this.probabilidadDeAveria ){
		this.setAveria(numAleatorio.nextInt(this.duracionMaxima + 1));
		super.avanza();
		}
	}

	@Override
	public String getNombreSeccion() {
		// TODO Auto-generated method stub
		return super.getNombreSeccion();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {
		// TODO Auto-generated method stub
		//is.setValue("type", this.tipo);
		is.setValue("itinerary", this.itinerario);
		is.setValue("max_speed", this.velocidadMaxima);
		is.setValue("resistance", this.resistenciaKm);
		is.setValue("fault_probability", this.probabilidadDeAveria);
		is.setValue("max_fault_duration", this.duracionMaxima);
		is.setValue("seed", this.seed);
		is.setValue("type", "car");
	}

	
	

}
