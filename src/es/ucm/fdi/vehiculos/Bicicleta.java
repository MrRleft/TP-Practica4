package es.ucm.fdi.vehiculos;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Cruce;

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
		//is.setValue("itinerary", this.itinerario);
		is.setValue("max_speed", this.velocidadMaxima);
		is.setValue("type", "bike");
		
	}

	

	@Override
	public void setAveria(int duration) {
		// TODO Auto-generated method stub
		if (this.velocidadActual >= this.velocidadMaxima / 2) 
			super.setAveria(duration);
	}

	@Override
	public String getNombreSeccion() {
		// TODO Auto-generated method stub
		return super.getNombreSeccion();
	}
	
	

}
