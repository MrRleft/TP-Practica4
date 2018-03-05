package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.Carretera;
import es.ucm.fdi.model.Vehiculo;

public class CarreteraEntrante {

	 protected Carretera carretera;
	 protected List<Vehiculo> colaVehiculos;
	 protected boolean semaforo; // true=verde, false=rojo

	 
	 public CarreteraEntrante(Carretera carretera) {
	 // inicia los atributos.
	 // el semáforo a rojo
		 this.carretera = carretera;
		 this.semaforo = false;
		 this.colaVehiculos = new ArrayList<>();
	 }
	 
	 void ponSemaforo(boolean color) {
		 
		 if(color = true) this.semaforo = true;//verde
		 else this.semaforo = false;//rojo
	 }

	 public void avanzaPrimerVehiculo() throws ErrorDeSimulacion {
		 // coge el primer vehiculo de la cola, lo elimina,
		 // y le manda que se mueva a su siguiente carretera.
		 this.colaVehiculos.get(0).moverASiguienteCarretera();
		 this.colaVehiculos.remove(0);
		  
	
		 
	 }
	 
	 @Override
	 public String toString() {
		 
		return null;
	 }
}
