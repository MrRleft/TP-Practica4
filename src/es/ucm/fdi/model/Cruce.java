package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public class Cruce extends ObjetoSimulacion {
	
	public Cruce(String id) {
		super(id);
	}
	
	public Carretera carreteraHaciaCruce(Cruce cruce) {
	 // devuelve la carretera que llega a ese cruce desde �this�
	}
	
	public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {
	 // a�ade una carretera entrante al �mapaCarreterasEntrantes� y
	 // a las �carreterasEntrantes�
	}
	
	public void addCarreteraSalienteAlCruce(Cruce destino, Carretera road) {
	 // a�ade una carretera saliente
	}
	
	public void entraVehiculoAlCruce(String idCarretera, Vehiculo vehiculo){
	 // a�ade el �vehiculo� a la carretera entrante �idCarretera�
	}
	
	protected void actualizaSemaforos(){
	 // pone el sem�foro de la carretera actual a �rojo�, y busca la siguiente
	 // carretera entrante para ponerlo a �verde�
	} 
	
	@Override
	public void avanza() {
	// Si �carreterasEntrantes� es vac�o, no hace nada.
	 // en otro caso �avanzaPrimerVehiculo� de la carretera con el sem�foro verde.
	 // Posteriormente actualiza los sem�foros.
	}
	
	@Override
	protected String getNombreSeccion() {
		
	}
	
	@Override
	protected void completaDetallesSeccion(IniSection is) {
	 // genera la secci�n queues = (r2,green,[]),...
	}
}
