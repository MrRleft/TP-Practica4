package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

/*Por los caminos es más difícil conducir que por las carreteras convencionales en el
caso de que haya vehículos averiados. Su factor de reducción (factorReduccion) será 1
más el número de coches averiados. Su velocidad base (velocidadBase) se define como la
velocidad máxima de la carretera. Los caminos se añaden utilizando el siguiente evento:*/
public class Camino extends Carretera {
	public Camino(String id, int length, int maxSpeed, Cruce src, Cruce dest) {
		super(id, length, maxSpeed, src, dest);
		// TODO Auto-generated constructor stub
	}
@Override
	protected int calculaVelocidadBase() {
		// TODO Auto-generated method stub
		return this.velocidadMaxima;
	}

	@Override
	protected int calculaFactorReduccion(int obstaculos) {
		return 1 + obstaculos;
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {
		// TODO Auto-generated method stub
		is.setValue("src", this.cruceOrigen);
		is.setValue("dest", this.cruceDestino);
		is.setValue("max_speed", this.velocidadMaxima);
		is.setValue("length", this.longitud);
		is.setValue("type", "dirt");
	}
}
