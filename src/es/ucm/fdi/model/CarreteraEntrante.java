package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.vehiculos.Vehiculo;

public class CarreteraEntrante {

	 protected Carretera carretera;
	 protected List<Vehiculo> colaVehiculos;
	 protected boolean semaforo; // true=verde, false=rojo

	 
	 public CarreteraEntrante(Carretera carretera) {

		 this.carretera = carretera;
		 this.semaforo = false;
		 this.colaVehiculos = new ArrayList<>();
	 }
	 
	 void ponSemaforo(boolean color) {
		 
		 if(color == true) this.semaforo = true;
		 else this.semaforo = false;
	 }

	 public void avanzaPrimerVehiculo() throws ErrorDeSimulacion {

		if(!this.colaVehiculos.isEmpty()){
			this.colaVehiculos.get(0).moverASiguienteCarretera();
		 	this.colaVehiculos.remove(0);
		}
		  
	 }
	 
	 public void entraVehiculo(Vehiculo vehiculo) throws ErrorDeSimulacion {

			 if(!this.colaVehiculos.contains(vehiculo)){
				 this.colaVehiculos.add(vehiculo);
				 }
			 else {
				 throw new ErrorDeSimulacion("Se ha a�adido el mismo vehiculo dos veces a una carretera entrante");
			 }
		}
	 
	 

	public boolean getSem() {
		return this.semaforo;
	}
}
