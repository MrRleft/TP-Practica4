package es.ucm.fdi.carreteras;

import es.ucm.fdi.cruces.Cruce;
import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.ini.IniSection;


public class Autopista extends Carretera {
	private int carriles;

	public Autopista(String id, int length, int maxSpeed, CruceGenerico<?> src, CruceGenerico<?> dest,int carriles) {
		
		super(id, length, maxSpeed, src, dest);
		this.carriles = carriles;
	}

	@Override
	protected int calculaFactorReduccion(int obstaculos) {

		if(this.carriles > obstaculos) return 1;
		else return 2;
	}

	private int ayudaBase(){
		return (this.velocidadMaxima * this.carriles)/Math.max(this.vehiculos.size(),1);
	}
	
	@Override
	protected int calculaVelocidadBase() {
		return Math.min(this.velocidadMaxima, ayudaBase()+1);
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {
		is.setValue("type", "lanes");
		super.completaDetallesSeccion(is);
	}
	
	

}
