package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

/*Las autopistas contendrán varios carriles para soportar un nivel de tráfico mayor que
las carreteras convencionales. Por lo tanto los vehículos podrán circular a mayor velocidad
incluso aunque haya varios vehículos averiados. Su factor de reducción factorReduccion
es 1 siempre que haya mas carriles que vehículos averiados (i.e., numCarriles >numObstaculos)
y 2 en otro caso.
La velocidad base (velocidadBase) de una autopista también considera el número de
carriles y se define como m´ın(m, m∗l
m´ax(n,1) + 1), donde m es la velocidad máxima, n es el
número de vehículos en la carretera y l es el número de carriles. La división es entera. Las
autopistas se añaden usando el siguiente evento:
*/
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
