package es.ucm.fdi.model;

import java.util.List;
import java.util.Map;

import es.ucm.fdi.ini.IniSection;

abstract public class Cruce extends ObjetoSimulacion {
	 
	 protected int indiceSemaforoVerde; // lleva el índice de la carretera entrante
	 // con el semáforo en verde
	 protected List<CarreteraEntrante> carreterasEntrantes;

	 // para optimizar las búsquedas de las carreterasEntrantes
	 // (IdCarretera, CarreteraEntrante)
	 protected Map<String,CarreteraEntrante> mapaCarreterasEntrantes;
	 protected Map<Cruce,Carretera> parCarreteraCruce;
	 
	 public Cruce(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	 public Carretera carreteraHaciaCruce(Cruce cruce) {
		 // devuelve la carretera que llega a ese cruce desde “this”
		return this.parCarreteraCruce.get(cruce);
	 }
	 public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {
		 // añade una carretera entrante al “mapaCarreterasEntrantes” y
		 // a las “carreterasEntrantes”
		 CarreteraEntrante cE = new CarreteraEntrante(carretera);
		 this.carreterasEntrantes.add(cE);
		 this.mapaCarreterasEntrantes.put(idCarretera, cE);
		 
		}
	 public void addCarreteraSalienteAlCruce(Cruce destino, Carretera road) {
		 // añade una carretera saliente
		 this.parCarreteraCruce.p
		 
		}
	 public void entraVehiculoAlCruce(String idCarretera, Vehiculo vehiculo){
		 // añade el “vehiculo” a la carretera entrante “idCarretera”
		 
		}
	 protected void actualizaSemaforos(){
		 // pone el semáforo de la carretera actual a “rojo”, y busca la siguiente
		 // carretera entrante para ponerlo a “verde”
		}
		@Override
	 public void avanza() {
		// Si “carreterasEntrantes” es vacío, no hace nada.
		 // en otro caso “avanzaPrimerVehiculo” de la carretera con el semáforo verde.
		 // Posteriormente actualiza los semáforos.
		}
		
	 protected String getNombreSeccion() {
			
		}
		@Override
	protected void completaDetallesSeccion(IniSection is) {
		 // genera la sección queues = (r2,green,[]),...
		}
}

