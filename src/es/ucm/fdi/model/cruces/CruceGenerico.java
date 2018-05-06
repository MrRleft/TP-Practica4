package es.ucm.fdi.model.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.model.ObjetoSimulacion;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.carreteras.Carretera;
import es.ucm.fdi.model.carreteras.CarreteraEntrante;
import es.ucm.fdi.model.ini.IniSection;
import es.ucm.fdi.model.vehiculos.Vehiculo;

public abstract class CruceGenerico<T extends  CarreteraEntrante> extends ObjetoSimulacion  {

	protected int indiceSemaforoVerde; // lleva el �ndice de la carretera entrante
	 // con el sem�foro en verde
	protected List<T> carreterasEntrantes;

	// para optimizar las b�squedas de las carreterasEntrantes
	// (IdCarretera, CarreteraEntrante)
	protected Map<String,T> mapaCarreterasEntrantes;
	protected Map<CruceGenerico<?>,Carretera> parCarreteraCruce;
	 
	public CruceGenerico(String id) {
		super(id);
		this.indiceSemaforoVerde = -1;
		this.mapaCarreterasEntrantes = new HashMap<>();
		this.parCarreteraCruce = new HashMap<>();
		this.carreterasEntrantes = new ArrayList<>();
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
				this.actualizaSemaforos();
				ok = false;
			}
			carreterasEntrantes.get(this.indiceSemaforoVerde).avanzaPrimerVehiculo();
			if(ok)
				this.actualizaSemaforos();
		}
	}

		 
	 public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {
		 T cE = CrearCE(carretera);
		 this.carreterasEntrantes.add(cE);
		 this.mapaCarreterasEntrantes.put(idCarretera, cE);
	}
	 
	 protected abstract T CrearCE(Carretera Carr);
	 
	 public void addCarreteraSalienteAlCruce(CruceGenerico<?> destino, Carretera road) {

		 this.parCarreteraCruce.put(destino, road);
	
	 }
	 

	public boolean carreteraEntranteAqui(Carretera c) {
		return this.mapaCarreterasEntrantes.containsKey(c.getID());	
	}
	
	public Carretera EncuentraCarretera(Cruce proximo) {
		return this.parCarreteraCruce.get(proximo);
	}
	 
	 public void entraVehiculoAlCruce(String idCarretera, Vehiculo vehiculo) throws ErrorDeSimulacion{
		 this.mapaCarreterasEntrantes.get(idCarretera).entraVehiculo(vehiculo);
	}
		 
	 public Carretera carreteraHaciaCruce(CruceGenerico<?> cruceProx) {
			return this.parCarreteraCruce.get(cruceProx);
	 }
	 
	 protected String getNombreSeccion() {
			
		 return "junction_report";
	}
	
	protected void completaDetallesSeccion(IniSection is) {
		String detalles = "";
		for (int i = 0; i <this.carreterasEntrantes.size(); ++i){
			
			detalles += "(" + this.carreterasEntrantes.get(i).getCarretera().getID() + ",";
			if (this.carreterasEntrantes.get(i).getSem()) 
				detalles += "green,[";
			else
				detalles += "red,[";
			for (Vehiculo v : this.carreterasEntrantes.get(i).getColaVehiculos())
				detalles += v.getId() + ",";
			if(this.carreterasEntrantes.get(i).getColaVehiculos().size() > 0)
				detalles = detalles.substring(0, detalles.length()-1);
			detalles = detalles.substring(0, detalles.length() );
			detalles += "])";
			if(i < this.carreterasEntrantes.size()-1)
				detalles += ",";
		}
		detalles = detalles.substring(0, detalles.length());
		is.setValue("queues", detalles);
	}
	 abstract protected void actualizaSemaforos();

	public List<T> getCarreteras() {

		return this.carreterasEntrantes;
	}

	public String getGreenRoad() {
		
		
		String key = "[";
		if(this.indiceSemaforoVerde != -1) {
			CarreteraEntrante semVerde = this.carreterasEntrantes.get(this.indiceSemaforoVerde);
			key += "(" +semVerde.getID() + "green" + "[" + semVerde.getIDCars() + "])]" ;
		}
		return key;
	}

	public Object getRedRoads() {

		String key = "[";
		for(CarreteraEntrante RedSems : this.carreterasEntrantes) {
			if(!RedSems.getSem())
				key += "(" + RedSems.getID() + "red" + "[" + RedSems.getIDCars() + "])" ;
		}
		key += "]";
		return key;
	}	
}
