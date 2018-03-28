package es.ucm.fdi.carreteras;

public class CarreteraEntranteConIntervalo extends CarreteraEntrante{

	private int intervaloDeTiempo;  // Tiempo que ha de transcurrir para poner  
										// el semáforo de la carretera en rojo 
	private int unidadesDeTiempoUsadas; // Se incrementa cada vez que
										// avanza un vehículo 
	private boolean usoCompleto; // Controla que en cada paso con el semáforo 
									// en verde, ha pasado un vehículo 
	private boolean usadaPorUnVehiculo; // Controla que al menos ha pasado un                                    
							// vehículo mientras el semáforo estaba // en verde.
	 public CarreteraEntranteConIntervalo(Carretera carretera, int intervalTiempo) {  
		 super(carretera);  
	}  
	 
	 @Override  protected void avanzaPrimerVehiculo() {  
		 // Incrementa unidadesDeTiempoUsadas 
		 // Actualiza usoCompleto:   
		 //   - Si “colaVehiculos” es vacía, entonces “usoCompleto=false”   7
		 //   - En otro caso saca el primer vehículo “v” de la “colaVehiculos”,  
		 //     y le mueve a la siguiente carretera (“v.moverASiguienteCarretera()”)    
		 //     Pone “usadaPorUnVehiculo” a true. 
		 } 
	 
	 public boolean tiempoConsumido() {  
		 // comprueba si se ha agotado el intervalo de tiempo. 
		 // “unidadesDeTiempoUsadas >= “intervaloDeTiempo” 
	}
	 
	 public boolean usoCompleto() {  } 	 // método get 
	 public boolean usada() {}   // método get 
	 
}
	

