package es.ucm.fdi.carreteras;


import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.ini.IniSection;


public class Camino extends Carretera {
	
	public Camino(String id, int length, int maxSpeed, CruceGenerico<?> src, CruceGenerico<?> dest) {
		super(id, length, maxSpeed, src, dest);	
	}
	
	@Override
	protected int calculaVelocidadBase() {
		return this.velocidadMaxima;
	}

	@Override
	protected int calculaFactorReduccion(int obstaculos) {
		return 1 + obstaculos;
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {

		is.setValue("src", this.cruceOrigen);
		is.setValue("dest", this.cruceDestino);
		is.setValue("max_speed", this.velocidadMaxima);
		is.setValue("length", this.longitud);
		is.setValue("type", "dirt");
	}
}
