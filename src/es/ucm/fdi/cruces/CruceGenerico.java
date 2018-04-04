package es.ucm.fdi.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.carreteras.CarreteraEntrante;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.ObjetoSimulacion;
import es.ucm.fdi.vehiculos.Vehiculo;

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
	
	protected abstract void completaDetallesSeccion(IniSection is);
	 abstract protected void actualizaSemaforos();
		
}