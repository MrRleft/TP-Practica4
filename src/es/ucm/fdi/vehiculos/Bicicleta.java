package es.ucm.fdi.vehiculos;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Cruce;

public class Bicicleta extends Vehiculo {


	public Bicicleta(String id, int velocidadMaxima, List<Cruce> iti) throws ErrorCarga {
		super(id, velocidadMaxima, iti);
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {
		is.setValue("type", "bike");
		super.completaDetallesSeccion(is);
	}

	

	@Override
	public void setAveria(int duration) {
		if (this.velocidadActual >= this.velocidadMaxima / 2) 
			super.setAveria(duration);
	}

	@Override
	public String getNombreSeccion() {
		return super.getNombreSeccion();
	}
	
	

}
