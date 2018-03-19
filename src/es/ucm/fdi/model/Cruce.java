package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.vehiculos.Vehiculo;

 public class Cruce extends ObjetoSimulacion {
	 
	 protected int indiceSemaforoVerde; // lleva el �ndice de la carretera entrante
	 // con el sem�foro en verde
	 protected List<CarreteraEntrante> carreterasEntrantes;

	 // para optimizar las b�squedas de las carreterasEntrantes
	 // (IdCarretera, CarreteraEntrante)
	 protected Map<String,CarreteraEntrante> mapaCarreterasEntrantes;
	 protected Map<Cruce,Carretera> parCarreteraCruce;
	 
	 public Cruce(String id) {
		super(id);
		this.indiceSemaforoVerde = -1;
		this.mapaCarreterasEntrantes = new HashMap<>();
		this.parCarreteraCruce = new HashMap<>();
		this.carreterasEntrantes = new ArrayList<>();
	}
	 
	 public Carretera carreteraHaciaCruce(Cruce cruce) {
		return this.parCarreteraCruce.get(cruce);
	 }
	 
	 public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {

		 CarreteraEntrante cE = new CarreteraEntrante(carretera);
		 this.carreterasEntrantes.add(cE);
		 this.mapaCarreterasEntrantes.put(idCarretera, cE);
		 
		}
	public void addCarreteraSalienteAlCruce(Cruce destino, Carretera road) {

		 this.parCarreteraCruce.put(destino, road);
		 
		}
	 public void entraVehiculoAlCruce(String idCarretera, Vehiculo vehiculo) throws ErrorDeSimulacion{

		 this.mapaCarreterasEntrantes.get(idCarretera).entraVehiculo(vehiculo);
		}
	 
	 protected void actualizaSemaforos(){

		 this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(false);
		 this.indiceSemaforoVerde++;
		 if(this.indiceSemaforoVerde == this.carreterasEntrantes.size())
			 this.indiceSemaforoVerde = 0;
		 this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
		 
	 }
	 
		@Override
	 public void avanza() throws ErrorDeSimulacion {
		/*
		 * Aqui lo que hacemos es que si no se ha inicializado todavia ninguna carretera a verde
		 * inicializamos la primera, y luego no hace falta actualizar semaforos porque ya lo hemos hecho al 
		 * principio
		 */
		if (!this.carreterasEntrantes.isEmpty()) {
			boolean ok = true;
			if(this.indiceSemaforoVerde == -1) {
				this.indiceSemaforoVerde = 0;
				this.carreterasEntrantes.get(0).ponSemaforo(true);
				ok = false;
			}
			carreterasEntrantes.get(this.indiceSemaforoVerde).avanzaPrimerVehiculo();
			if(ok)
				this.actualizaSemaforos();
		}
	}
		
	 protected String getNombreSeccion() {
			
		 return "junction_report";
	}
	
	 @Override
	protected void completaDetallesSeccion(IniSection is) {
		String detalles = "";
		for (int i = 0; i <this.carreterasEntrantes.size(); ++i){
			
			detalles += "(" + this.carreterasEntrantes.get(i).carretera.getID() + ",";
			if (this.carreterasEntrantes.get(i).getSem()) 
				detalles += "green,[";
			else
				detalles += "red,[";
			for (Vehiculo v : this.carreterasEntrantes.get(i).colaVehiculos)
				detalles += v.id + ",";
			if(this.carreterasEntrantes.get(i).colaVehiculos.size() > 0)
				detalles = detalles.substring(0, detalles.length()-1);
			detalles = detalles.substring(0, detalles.length() );
			detalles += "])";
			if(i < this.carreterasEntrantes.size()-1)
				detalles += ",";
		}
		detalles = detalles.substring(0, detalles.length());
		is.setValue("queues", detalles);
	}
		
	public boolean carreteraEntranteAqui(Carretera c) {
		return this.mapaCarreterasEntrantes.containsKey(c.getID());	
	}
	
	public Carretera EncuentraCarretera(Cruce proximo) {
		return this.parCarreteraCruce.get(proximo);
	}
}

