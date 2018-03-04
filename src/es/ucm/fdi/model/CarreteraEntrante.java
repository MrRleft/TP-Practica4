package es.ucm.fdi.model;

import java.util.List;

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
	 }
	 void ponSemaforo(boolean color) {
		 
		 if(color = true) this.semaforo = true;
		 else this.semaforo = false;
	 }

	 public void avanzaPrimerVehiculo() {
	 // coge el primer vehiculo de la cola, lo elimina,
		 this.colaVehiculos.remove(0);
	 // y le manda que se mueva a su siguiente carretera.
	 }
	 
	 @Override
	 public String toString() {
		 
		return null;
	 }
}
