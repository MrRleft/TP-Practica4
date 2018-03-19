package es.ucm.fdi.carreteras;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Cruce;


public class Autopista extends Carretera {
	private int carriles;

	public Autopista(String id, int length, int maxSpeed, Cruce src, Cruce dest,int carriles) {
		super(id, length, maxSpeed, src, dest);
		// TODO Auto-generated constructor stub
		this.carriles = carriles;
	}

	@Override
	protected int calculaFactorReduccion(int obstaculos) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		is.setValue("src", this.cruceOrigen);
		is.setValue("dest",this.cruceDestino);
		is.setValue("max_speed",this.velocidadMaxima);
		is.setValue("length", this.longitud);
		is.setValue("type", "bike");
		is.setValue("lanes",this.carriles);
		
	}
	
	

}
