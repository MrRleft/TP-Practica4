package es.ucm.fdi.model;
//Hola esto es una prueba
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


import es.ucm.fdi.ini.IniSection;

public class Carretera {
	 private String id;
	 protected int longitud; // longitud de la carretera
	 protected int velocidadMaxima; // velocidad mÃ¡xima
	 protected Cruce cruceOrigen; // cruce del que parte la carretera
	 protected Cruce cruceDestino; // cruce al que llega la carretera
	 protected List<Vehiculo> vehiculos; // lista ordenada de vehÃ­culos en la
	// carretera (ordenada por localizaciÃ³n)
	 
	 protected Comparator<Vehiculo> comparadorVehiculo; // orden entre vehÃ­culos
	 
	 public Carretera(String id, int length, int maxSpeed, Cruce src, Cruce dest) {
		 this.id  = id;
		 this.longitud = length;
		 this.velocidadMaxima = maxSpeed;
		 this.cruceOrigen = src;
		 this.cruceDestino = dest;
		 Collections.sort(vehiculos,comparadorVehiculo); //ordena vehiculos??

	 }
	 
	
	public void avanza() {
		calculaVelocidadBase();
		//poner aqui la vel de cada vehiculo
		//llamar a avanza de vehiculos
	}
	
	 public void entraVehiculo(Vehiculo vehiculo) {
		// Si el vehÃ­culo no existe en la carretera, se aÃ±ade a la lista de vehÃ­culos y
		 // se ordena la lista.
		 // Si existe no se hace nada.
	}
	
	 public void saleVehiculo(Vehiculo vehiculo) {
		// elimina el vehÃ­culo de la lista de vehÃ­culos
	}
	 
	public void entraVehiculoAlCruce(Vehiculo v) {
		 // aÃ±ade el vehÃ­culo al â€œcruceDestinoâ€� de la carreteraâ€�
		this.cruceDestino.entraVehiculoAlCruce(this.id, v);
	}
	
	protected int calculaVelocidadBase() {
		int resultado;
		return resultado = Math.min(this.velocidadMaxima,(this.velocidadMaxima/Math.max(this.vehiculos.size(),1)));
	
	}
	protected int calculaFactorReduccion(int obstaculos) {
		
		if(obstaculos != 0) return 2;
		else return 1;
	}
	
	@Override
	protected String getNombreSeccion() {
		
		
			
	}
	
		@Override
	protected void completaDetallesSeccion(IniSection is) {
		 // crea â€œvehicles = (v1,10),(v2,10) â€�
		}
	
		
	//Metodos extra creados para facilitarnos la vida considerablemente. Ojo que no es poco lo que facilitan
	
	public int getLongitud() {
		return this.longitud;
	}
	
	public Cruce getCruceDest() {
		
		return this.cruceDestino;
	}
}
