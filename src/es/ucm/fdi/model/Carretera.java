
package es.ucm.fdi.model;


import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Utils.SortedArrayList;
import es.ucm.fdi.ini.IniSection;

public class Carretera extends ObjetoSimulacion {

	 protected int longitud; // longitud de la carretera
	 protected int velocidadMaxima; // velocidad mÃ¡xima
	 protected Cruce cruceOrigen; // cruce del que parte la carretera
	 protected Cruce cruceDestino; // cruce al que llega la carretera
	 protected List<Vehiculo> vehiculos; // lista ordenada de vehÃ­culos en la
	// carretera (ordenada por localizaciÃ³n)
	 
	 
	 protected Comparator<Vehiculo> comparadorVehiculo; // orden entre vehÃ­culos
	 
	 public Carretera(String id, int length, int maxSpeed, Cruce src, Cruce dest) {
		 super(id);
		 this.longitud = length;
		 this.velocidadMaxima = maxSpeed;
		 this.cruceOrigen = src;
		 this.cruceDestino = dest;
		 this.comparadorVehiculo = new Comparator<Vehiculo>() {
		 
		 	@Override
			public int compare(Vehiculo o1, Vehiculo o2) {
				if(o1.getLocalizacion() > o2.getLocalizacion())
					return -1;
				else if(o1.getLocalizacion() < o2.getLocalizacion())
					return 1;
				else 
					return 0;
			}
		 };
		 this.vehiculos = new SortedArrayList<Vehiculo>(comparadorVehiculo); //ordena vehiculos??

	 }
	 
	
	public void avanza() throws ErrorDeSimulacion {
		
		// calcular velocidad base de la carretera
		 // inicializar obst�culos a 0
		 // Para cada veh�culo de la lista �vehiculos�:
		 //1. Si el veh�culo est� averiado se incrementa el n�mero de obstaculos.
		// 2. Se fija la velocidad actual del veh�culo
		// 3. Se pide al veh�culo que avance.
		 // ordenar la lista de veh�culos 
		int vBase = this.calculaVelocidadBase();
		int obstaculos = 0;
		for( Vehiculo v : vehiculos){
			
			if (v.tiempoAveria > 0) {
				obstaculos++;
			}
			v.setVActual(vBase/this.calculaFactorReduccion(obstaculos));
			v.avanza();
			
		}
		this.vehiculos.sort(this.comparadorVehiculo);
	}
	
	 public void entraVehiculo(Vehiculo vehiculo) {
		// Si el vehÃ­culo no existe en la carretera, se aÃ±ade a la lista de vehÃ­culos y
		 if(!this.vehiculos.contains(vehiculo)){
			 this.vehiculos.add(vehiculo);
			 this.vehiculos.sort(this.comparadorVehiculo); // se ordena la lista.
			 }
	
		 // Si existe no se hace nada.
	}
	
	 public void saleVehiculo(Vehiculo vehiculo) {
		// elimina el vehÃ­culo de la lista de vehÃ­culos
		 vehiculos.remove(vehiculo);
	}
	 
	public void entraVehiculoAlCruce(Vehiculo v) throws ErrorDeSimulacion {
		 // aÃ±ade el vehÃ­culo al â€œcruceDestinoâ€� de la carreteraâ€�

		this.cruceDestino.entraVehiculoAlCruce(this.id, v);

	}
	
	protected int calculaVelocidadBase() {
		
		return  Math.min(this.velocidadMaxima,(this.velocidadMaxima/Math.max(this.vehiculos.size(),1) + 1));
	
	}
	protected int calculaFactorReduccion(int obstaculos) {
		if(obstaculos != 0) return 2;
		else return 1;
	}
	
	protected String getNombreSeccion() {
		return "road_report";
	}
	
	protected void completaDetallesSeccion(IniSection is) {
		 // crea â€œvehicles = (v1,10),(v2,10) â€�
		String key= "";
		if(this.vehiculos.size() != 0)
		//	is.setValue("vehicles",key);
			for(int i = 0; i < this.vehiculos.size();++i){
				if(i!=this.vehiculos.size()-1){
				key += "(" + this.vehiculos.get(i).id+","+ this.vehiculos.get(i).localizacion + ")," ;
				//System.out.println("vehicles" + this.vehiculos.get(i).id);
				}
				else
				key += "(" + this.vehiculos.get(i).id+","+ this.vehiculos.get(i).localizacion + ")" ;
			} 
		is.setValue("vehicles",key );
		//else
			//is.setValue("vehicles","");
		
	}
	
		
	//Metodos extra creados para facilitarnos la vida considerablemente. Ojo que no es poco lo que facilitan
	
	public int getLongitud() {
		return this.longitud;
	}
	
	public Cruce getCruceDest() {
		return this.cruceDestino;
	}

	public String getID(){
		return id; 
	}


	
	
}

