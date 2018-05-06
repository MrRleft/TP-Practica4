package es.ucm.fdi.model.carreteras;

import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;

public class CarreteraEntranteConIntervalo extends CarreteraEntrante{

	private int intervaloDeTiempo;  // Tiempo que ha de transcurrir para poner  
										// el sem�foro de la carretera en rojo 
	private int unidadesDeTiempoUsadas; // Se incrementa cada vez que
										// avanza un veh�culo 
	private boolean usoCompleto; // Controla que en cada paso con el sem�foro 
									// en verde, ha pasado un veh�culo 
	private boolean usadaPorUnVehiculo; // Controla que al menos ha pasado un                                    
							// veh�culo mientras el sem�foro estaba // en verde.
	 public CarreteraEntranteConIntervalo(Carretera carretera, int intervalTiempo) {  
		 super(carretera);  
		 this.intervaloDeTiempo =intervalTiempo;
		 this.unidadesDeTiempoUsadas = 0;
		 this.usoCompleto = false;
		 this.usadaPorUnVehiculo = false;
	}  
	 
	 @Override 
	 public void avanzaPrimerVehiculo() throws ErrorDeSimulacion {  
		 // Incrementa unidadesDeTiempoUsadas 
		 // Actualiza usoCompleto:   
		 //   - Si �colaVehiculos� es vac�a, entonces �usoCompleto=false�   7
		 //   - En otro caso saca el primer veh�culo �v� de la �colaVehiculos�,  
		 //     y le mueve a la siguiente carretera (�v.moverASiguienteCarretera()�)    
		 //     Pone �usadaPorUnVehiculo� a true. 
		 this.unidadesDeTiempoUsadas++;
		 if(this.colaVehiculos.size() == 0)
			 this.usoCompleto = false;
		 else {
			this.getColaVehiculos().get(0).moverASiguienteCarretera();
			this.getColaVehiculos().remove(0);
			this.usadaPorUnVehiculo = true;
		 }
	} 
	 
	 public boolean tiempoConsumido() {  
		 // comprueba si se ha agotado el intervalo de tiempo. 
		 // �unidadesDeTiempoUsadas >= �intervaloDeTiempo� 
		 return this.unidadesDeTiempoUsadas >= this.intervaloDeTiempo;
	}
	 
	 public void restartTime() {
		 this.unidadesDeTiempoUsadas = 0;
	 }
	 
	 public boolean usoCompleto() { 
		 return this.usoCompleto;
	 } 	 // m�todo get 
	 public boolean usada() {
		 return this.usadaPorUnVehiculo;
	 }   // m�todo get 
	 
	 public int getInt() {
		 return this.intervaloDeTiempo;
	 }
	 
	 public void setInt(int i) {
		 this.intervaloDeTiempo = i;
	 }
	
	public int getUnidadesDeTiempoUsadas() {
		return unidadesDeTiempoUsadas;
	}

	public void setUnidadesDeTiempoUsadas(int unidadesDeTiempoUsadas) {
		this.unidadesDeTiempoUsadas = unidadesDeTiempoUsadas;
	}
}
	

