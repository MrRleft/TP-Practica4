package es.ucm.fdi.model.vehiculos;

import java.util.List;
import java.util.Random;

import es.ucm.fdi.model.Exceptions.ErrorCarga;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.cruces.CruceGenerico;
import es.ucm.fdi.model.ini.IniSection;

public class Coche extends Vehiculo {
	
	private int resistenciaKm;
	private double probabilidadDeAveria;
	private int duracionMaxima;
	private long seed;
	private Random numAleatorio;
	private int ultimaAveria;
	
	
	public Coche(String id, int velocidadMaxima, List<CruceGenerico<?>> iti,int resistencia,double probAveria,int duracionMaxima,long seed) throws ErrorCarga {
		super(id, velocidadMaxima, iti);
		// TODO Auto-generated constructor stub
		this.resistenciaKm = resistencia;
		this.probabilidadDeAveria = probAveria;
		this.duracionMaxima = duracionMaxima;
		this.seed = seed;
		this.numAleatorio = new Random(this.seed);
		this.ultimaAveria = 0;
	}

	@Override
	public void avanza() throws ErrorDeSimulacion {
		// TODO Auto-generated method stub
		 // - Si el coche est� averiado poner �kmUltimaAveria� a �kilometraje�.
		 // - Sino el coche no est� averiado y ha recorrido �resistenciakm�, y adem�s
		 // �numAleatorio�.nextDouble() < �probabilidadDeAveria�, entonces
		 // incrementar �tiempoAveria� con �numAleatorio.nextInt(�duracionMaximaAveria�)+1
		 // - Llamar a super.avanza();
		if(this.tiempoAveria > 0)
			this.ultimaAveria = this.kilometraje;
		else if(this.tiempoAveria == 0 && this.kilometraje >= this.resistenciaKm &&
				this.numAleatorio.nextDouble() < this.probabilidadDeAveria){
			this.tiempoAveria = this.numAleatorio.nextInt(duracionMaxima)+1;
		}
		super.avanza();
			

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
		is.setValue("id", this.id);
		is.setValue("type", "car");
		super.completaDetallesSeccion(is);
	}

	
	

}
