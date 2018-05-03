package es.ucm.fdi.model.carreteras;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.vehiculos.Vehiculo;

public class CarreteraEntrante {

	 private Carretera carretera;
	 protected List<Vehiculo> colaVehiculos;
	 protected boolean semaforo; // true=verde, false=rojo

	 
	 public CarreteraEntrante(Carretera carretera) {

		 this.setCarretera(carretera);
		 this.semaforo = false;
		 this.setColaVehiculos(new ArrayList<>());
	 }
	 
	 public void ponSemaforo(boolean color) {
		 
		 if(color == true) this.semaforo = true;
		 else this.semaforo = false;
	 }

	 public void avanzaPrimerVehiculo() throws ErrorDeSimulacion {

		if(!this.getColaVehiculos().isEmpty()){
			this.getColaVehiculos().get(0).moverASiguienteCarretera();
		 	this.getColaVehiculos().remove(0);
		}
		  
	 }
	 
	 public void entraVehiculo(Vehiculo vehiculo) throws ErrorDeSimulacion {

			 if(!this.getColaVehiculos().contains(vehiculo)){
				 this.getColaVehiculos().add(vehiculo);
				 }
			 else {
				 throw new ErrorDeSimulacion("Se ha a�adido el mismo vehiculo dos veces a una carretera entrante");
			 }
		}
	 
	 

	public boolean getSem() {
		return this.semaforo;
	}

	public Carretera getCarretera() {
		return carretera;
	}

	public void setCarretera(Carretera carretera) {
		this.carretera = carretera;
	}

	public List<Vehiculo> getColaVehiculos() {
		return colaVehiculos;
	}

	public void setColaVehiculos(List<Vehiculo> colaVehiculos) {
		this.colaVehiculos = colaVehiculos;
	}

	public boolean tieneSemaforoVerde() {
		// TODO Auto-generated method stub
		return this.semaforo == true;
	}


	
}
