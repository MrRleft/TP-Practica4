package es.ucm.fdi.carreteras;

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
	}  
	 
	 @Override  protected void avanzaPrimerVehiculo() {  
		 // Incrementa unidadesDeTiempoUsadas 
		 // Actualiza usoCompleto:   
		 //   - Si �colaVehiculos� es vac�a, entonces �usoCompleto=false�   7
		 //   - En otro caso saca el primer veh�culo �v� de la �colaVehiculos�,  
		 //     y le mueve a la siguiente carretera (�v.moverASiguienteCarretera()�)    
		 //     Pone �usadaPorUnVehiculo� a true. 
		 } 
	 
	 public boolean tiempoConsumido() {  
		 // comprueba si se ha agotado el intervalo de tiempo. 
		 // �unidadesDeTiempoUsadas >= �intervaloDeTiempo� 
	}
	 
	 public boolean usoCompleto() {  } 	 // m�todo get 
	 public boolean usada() {}   // m�todo get 
	 
}
	

