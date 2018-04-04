package es.ucm.fdi.vehiculos;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.ObjetoSimulacion;

public class Vehiculo extends ObjetoSimulacion {

	 protected Carretera carretera; // carretera en la que est� el veh�culo
	 protected int velocidadMaxima; // velocidad m�xima
	 protected int velocidadActual; // velocidad actual
	 protected int kilometraje; // distancia recorrida
	 protected int localizacion; // localizaci�n en la carretera
	 protected int tiempoAveria; // tiempo que estar� averiado
	 protected List<CruceGenerico<?>> itinerario; // itinerario a recorrer (m�nimo 2)
	 private boolean EnCruce;
	 private boolean haLlegado;



	 public Vehiculo(String id, int velocidadMaxima, List<CruceGenerico<?>> iti) throws ErrorCarga {
		
		 super(id);
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
		 
		 if(velocidad < 0)
			 this.velocidadActual = 0;
		 if(velocidad > this.velocidadMaxima)
			 this.velocidadActual = this.velocidadMaxima;
		 else
			 this.velocidadActual = velocidad;
	 }
	 
	 @Override
	 protected void completaDetallesSeccion(IniSection is) {

		  is.setValue("speed", this.velocidadActual);
		  is.setValue("kilometrage", this.kilometraje);
		  is.setValue("faulty", this.tiempoAveria);
		  is.setValue("location", this.haLlegado ? "arrived" :
			  	"(" + this.carretera + "," + this.getLocalizacion() + ")");
	 
	 }
	 
	 @Override
	 public void avanza() throws ErrorDeSimulacion {

		 if(!this.haLlegado) {
			 if(this.tiempoAveria > 0) {
				 this.tiempoAveria--;
				 this.velocidadActual = 0;
			 }
			 else {
				 if(!this.EnCruce){
					 if(!LLegoAlCruce()) {
						 this.kilometraje += this.velocidadActual;
						 this.localizacion += this.velocidadActual;
					 }
					 else {
						 this.kilometraje += this.carretera.getLongitud() - this.localizacion;
						 this.localizacion = this.carretera.getLongitud();
						 this.EnCruce = true;
						 this.velocidadActual = 0;
						 this.carretera.entraVehiculoAlCruce(this);

					 }
				 }
			 }
	 	}
	 }
	 
	 public void moverASiguienteCarretera() throws ErrorDeSimulacion{

		 if(!this.haLlegado) {
			 if(this.carretera != null)
				 this.carretera.saleVehiculo(this);
			 
			 if(this.carretera != null && this.carretera.getCruceDest() == this.itinerario.get(this.itinerario.size()-1)) {
				 this.haLlegado = true;
				 this.velocidadActual = 0;
				 this.carretera = null;
				 this.EnCruce = true;
			 }
			 
			 else {
				 this.EnCruce = false;
				 this.carretera = this.calculoSigCarretera();
				 if(this.carretera == null)
					 throw new ErrorDeSimulacion("La Carretera: " + carretera + "del Vehiculo" + id + "No existe");
				 this.carretera.entraVehiculo(this);
				 this.localizacion = 0;	
				 this.velocidadActual = 0;
			 }
		 }
	 }
	
	 
	@Override
	public String getNombreSeccion() {
			return "vehicle_report";
	}
	 
	//Metodos extra creados para facilitarnos la vida considerablemente. Ojo que no es poco lo que facilitan
	protected boolean LLegoAlCruce() {
		
		return (this.localizacion + this.velocidadActual) >= this.carretera.getLongitud();
		
	}
		
	protected Carretera calculoSigCarretera() {
		
		CruceGenerico<?> cruceAct;
		
		if(this.carretera != null) 
			cruceAct = this.carretera.getCruceDest();
		
		else
			cruceAct = this.itinerario.get(0);
		
		int IndexcruceProx = this.itinerario.lastIndexOf(cruceAct)+1;
		CruceGenerico<?> cruceProx = this.itinerario.get(IndexcruceProx);
		return cruceAct.carreteraHaciaCruce(cruceProx);

	}
	
	protected void setVActual(int v) {
		
		if(v > this.velocidadMaxima)
			this.velocidadActual = this.velocidadMaxima;
		else
			this.velocidadActual = v;
		
	}

	public void setAveria(int duration) {
		// TODO Auto-generated method stub
		this.tiempoAveria += duration;

	}
	
	
	
}
