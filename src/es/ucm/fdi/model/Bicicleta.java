package es.ucm.fdi.model;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.ini.IniSection;

public class Bicicleta extends Vehiculo {

	//private String tipo;
	public Bicicleta(String id, int velocidadMaxima, List<Cruce> iti) throws ErrorCarga {
		super(id, velocidadMaxima, iti);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {
		// TODO Auto-generated method stub
		super.completaDetallesSeccion(is);
		is.setValue("itinerary", this.itinerario);
		is.setValue("max_speed", this.velocidadMaxima);
		
	}

	@Override
	public void avanza() throws ErrorDeSimulacion {
		// TODO Auto-generated method stub
		if(this.velocidadActual > this.velocidadMaxima/2){
			this.setAveria(this.tiempoAveria);//Aqui no va tiempoAveria pero no se que poner
		}
		super.avanza();
	}

	@Override
	public String getNombreSeccion() {
		// TODO Auto-generated method stub
		return super.getNombreSeccion();
	}
	
	

}
