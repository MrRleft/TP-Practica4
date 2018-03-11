package es.ucm.fdi.model;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.ini.IniSection;

public class Vehiculo extends ObjetoSimulacion {

	 protected Carretera carretera; // carretera en la que está el vehículo
	 protected int velocidadMaxima; // velocidad máxima
	 protected int velocidadActual; // velocidad actual
	 protected int kilometraje; // distancia recorrida
	 protected int localizacion; // localización en la carretera
	 protected int tiempoAveria; // tiempo que estará averiado
	 protected List<Cruce> itinerario; // itinerario a recorrer (mínimo 2)
	 private boolean EnCruce;
	 private boolean haLlegado;


	 public Vehiculo(String id, int velocidadMaxima, List<Cruce> iti) throws ErrorCarga {
		super(id);
		 // comprobar que la velocidadMaxima es mayor o igual que 0, y
		 // que el itinerario tiene al menos dos cruces.
		 // En caso de no cumplirse lo anterior, lanzar una excepción.
		 // inicializar los atributos teniendo en cuenta los parámetros.
		 // al crear un vehículo su “carretera” será inicalmene “null”.
		if(velocidadMaxima < 0)
			throw new ErrorCarga("La velocidad maxima de " + id + "es menor que 0");
		if(iti.size() < 2)
			throw new ErrorCarga("El numero de cruces de " + id + "es menor que 2");
		this.velocidadMaxima = velocidadMaxima;
		this.itinerario = iti;
		this.kilometraje = 0;
		this.velocidadActual = 0;
		this.localizacion = 0;
		this.tiempoAveria = 0;
		this.haLlegado = false;
		this.EnCruce = false;
	}

	 
	 public int getLocalizacion() {
		 
		 return this.localizacion;
		 
	 }
	 public int getTiempoDeInfraccion() {

		 //Supongo que esta funcion lo que quiere es el tiempo de averia
		 return this.tiempoAveria;
		 
	 }
	 public void setVelocidadActual(int velocidad) {
	  // Si “velocidad” es negativa, entonces la “velocidadActual” es 0.
	  // Si “velocidad” excede a “velocidadMaxima”, entonces la
	  // “velocidadActual” es “velocidadMaxima”
	  // En otro caso, “velocidadActual” es “velocidad”
		 if(velocidad < 0)
			 this.velocidadActual = 0;
		 if(velocidad > this.velocidadMaxima)
			 this.velocidadActual = this.velocidadMaxima;
		 else
			 this.velocidadActual = velocidad;
	 }
	 
	 @Override
	 protected void completaDetallesSeccion(IniSection is) {
			 
		  is.setValue("location", this.haLlegado ? "arrived" :
			  	this.carretera + ":" + this.getLocalizacion());
	 
	 }
	 
	 @Override
	 public void avanza() throws ErrorDeSimulacion {
	  // si el coche está averiado, decrementar tiempoAveria
	  // si el coche está esperando en un cruce, no se hace nada.
	  // en otro caso:
	/*  1. Actualizar su “localizacion”
	  2. Actualizar su “kilometraje”
	  3. Si el coche ha llegado a un cruce (localizacion >= carretera.getLength())
	  3.1. Poner la localización igual a la longitud de la carretera.
	  3.2. Corregir el kilometraje.
	  3.3. Indicar a la carretera que el vehículo entra al cruce.
	  3.4. Marcar que éste vehículo está en un cruce (this.estEnCruce = true)
	 */
		 if(this.tiempoAveria > 0)
			 this.tiempoAveria--;
		 else {
			 if(/*Si no esta esperando en un cruce*/!this.EnCruce){
				 if(!LLegoAlCruce()) {
					 this.kilometraje += this.velocidadActual;
					 this.localizacion += this.velocidadActual;
				 }
				 else {
					 this.kilometraje += this.carretera.getLongitud() - this.localizacion;
					 this.localizacion = this.carretera.getLongitud();
					 this.EnCruce = true;
					 this.carretera.entraVehiculoAlCruce(this);
				 }
			//Preguntar: ¿Algo mas que hacer aqui?
			 }
		 }
	 }
	 
	 public void moverASiguienteCarretera() throws ErrorDeSimulacion{
		 // Si la carretera no es null, sacar el vehículo de la carretera.
		 // Si hemos llegado al último cruce del itinerario, entonces:
	//	 1. Se marca que el vehículo ha llegado (this.haLlegado = true).
		// 2. Se pone su carretera a null.
		// 3. Se pone su “velocidadActual” y “localizacion” a 0.
		 // y se marca que el vehículo está en un cruce (this.estaEnCruce = true).
		 // En otro caso:
		 //1. Se calcula la siguiente carretera a la que tiene que ir.
		// 2. Si dicha carretera no existe, se lanza excepción.
		// 3. En otro caso, se introduce el vehículo en la carretera.
		// 4. Se inicializa su localización.
		 
		 if(this.carretera != null) {
			 this.carretera.saleVehiculo(this);
			 if(this.carretera.getCruceDest() == this.itinerario.get(this.itinerario.size()-1)) {
				 this.haLlegado = true;
				 this.velocidadActual = 0;
				 this.carretera = null;
				 this.EnCruce = true;
			 }
			 else {

				 this.carretera = this.calculoSigCarretera();
				 if(this.carretera == null)
					 throw new ErrorDeSimulacion("La Carretera: " + carretera + "del Vehiculo" + id + "No existe");
				 this.carretera.entraVehiculo(this);
				 this.localizacion = 0;	 
			 }
		 }
	}
	 
	@Override
	public String getNombreSeccion() {
			return "vehicle_report";
	}
	 
	//Metodos extra creados para facilitarnos la vida considerablemente. Ojo que no es poco lo que facilitan
	protected boolean LLegoAlCruce() {
		
		return (this.localizacion + this.velocidadActual) > this.carretera.getLongitud();
		
	}
		
	protected Carretera calculoSigCarretera() {
		
		int i = 0;
		while(this.itinerario.get(i).carreteraEntranteAqui(this.carretera) && (i < this.itinerario.size() -1)  ) {
			i++;
		}
		Cruce Anterior = this.itinerario.get(i) , Proximo = this.itinerario.get(i+1);
		return Anterior.EncuentraCarretera(Proximo);
	}
	
	
	
}
