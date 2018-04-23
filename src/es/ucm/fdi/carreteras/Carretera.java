
package es.ucm.fdi.carreteras;


import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Utils.SortedArrayList;
import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.ObjetoSimulacion;
import es.ucm.fdi.vehiculos.Vehiculo;

public class Carretera extends ObjetoSimulacion {

	 protected int longitud; // longitud de la carretera
	 protected int velocidadMaxima; // velocidad mÃ¡xima
	 protected CruceGenerico<?> cruceOrigen; // cruce del que parte la carretera
	 protected CruceGenerico<?> cruceDestino; // cruce al que llega la carretera
	 protected List<Vehiculo> vehiculos; // lista ordenada de vehÃ­culos en la
	// carretera (ordenada por localizaciÃ³n)
	 
	 
	 protected Comparator<Vehiculo> comparadorVehiculo; // orden entre vehÃ­culos
	 
	 public Carretera(String id, int length, int maxSpeed, CruceGenerico<?> src, CruceGenerico<?> dest) {
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
		
		
		int vBase = this.calculaVelocidadBase();
		int obstaculos = 0;
		for( Vehiculo v : vehiculos){
			
			if (v.getTiempoDeInfraccion() > 0) {
				obstaculos++;
			}
			v.setVelocidadActual(vBase/this.calculaFactorReduccion(obstaculos));
			v.avanza();
			
		}
		this.vehiculos.sort(this.comparadorVehiculo);
	}
	
	 public void entraVehiculo(Vehiculo vehiculo) {
		
		 if(!this.vehiculos.contains(vehiculo)){
			 this.vehiculos.add(vehiculo);
			 this.vehiculos.sort(this.comparadorVehiculo); // se ordena la lista.
			 }
	
		 
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

		String key= "";
		if(this.vehiculos.size() != 0)

			for(int i = 0; i < this.vehiculos.size();++i){
				if(i!=this.vehiculos.size()-1){
				key += "(" + this.vehiculos.get(i).getId()+","+ this.vehiculos.get(i).getLocalizacion() + ")," ;
				}
				else
				key += "(" + this.vehiculos.get(i).getId()+","+ this.vehiculos.get(i).getLocalizacion() + ")" ;
			} 
		is.setValue("state",key );		
	}
	
		
	//Metodos extra creados para facilitarnos la vida considerablemente. Ojo que no es poco lo que facilitan
	
	public int getLongitud() {
		return this.longitud;
	}
	
	public CruceGenerico<?> getCruceDest() {
		return this.cruceDestino;
	}

	public String getID(){
		return id; 
	}


	public List<Vehiculo> getVehiculos() {
		// TODO Auto-generated method stub
		return this.vehiculos;
	}


	public CruceGenerico<?> getCruceOrigen() {
		// TODO Auto-generated method stub
		return this.getCruceOrigen();
	}


	
	
}

