package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public class Cruce extends ObjetoDeSimulacion {
	
	public Cruce(String id) {
		
	}
	
	public Carretera carreteraHaciaCruce(Cruce cruce) {
	 // devuelve la carretera que llega a ese cruce desde “this”
	}
	
	public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {
	 // añade una carretera entrante al “mapaCarreterasEntrantes” y
	 // a las “carreterasEntrantes”
	}
	
	public void addCarreteraSalienteAlCruce(Cruce destino, Carretera road) {
	 // añade una carretera saliente
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
	
	@Override
	protected String getNombreSeccion() {
		
	}
	
	@Override
	protected void completaDetallesSeccion(IniSection is) {
	 // genera la sección queues = (r2,green,[]),...
	}
}
