package es.ucm.fdi.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.Exceptions.ErrorCarga;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.Exceptions.InsertException;
import es.ucm.fdi.Exceptions.NotFoundException;
import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.cruces.Cruce;
import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.vehiculos.Vehiculo;

public class MapaCarreteras {

	//Preguntar si se pueden quitar las listas pues quedan obsoletas
	 private List<Carretera> carreteras;
	 private List<CruceGenerico<?>> cruces;
	 private List<Vehiculo> vehiculos;
	
	 private Map<String, Carretera> mapaDeCarreteras;
	 private Map<String, CruceGenerico<?>> mapaDeCruces;
	 private Map<String, Vehiculo> mapaDeVehiculos;
	
	 public MapaCarreteras() {
		 // inicializa los atributos a sus constructoras por defecto.
		 // Para carreteras, cruces y vehÃ­culos puede usarse ArrayList.
		 // Para los mapas puede usarse HashMap
		 //Preguntar como inicializar los arraylist/diccionarios, y preguntar si se pueden eliminar los array list
		this.carreteras = new ArrayList<>();
		this.cruces = new ArrayList<>();
		this.vehiculos = new ArrayList<>();
		this.mapaDeCarreteras = new HashMap<>();
		this.mapaDeCruces = new HashMap<>();
		this.mapaDeVehiculos = new HashMap<>();
		 	
		}
	
	 public void addCruce(String idCruce, CruceGenerico<?> cruce) throws InsertException {
		 // comprueba que â€œidCruceâ€� no existe en el mapa.
		 // Si no existe, lo aÃ±ade a â€œcrucesâ€� y a â€œmapaDeCrucesâ€�.
		 // Si existe lanza una excepciÃ³n.
		 if(mapaDeCruces.containsKey(idCruce))
			 throw new InsertException("Se ha añadido dos veces el cruce " + idCruce);
		 this.mapaDeCruces.put(idCruce, cruce);
		 this.cruces.add(cruce);
	}
	
	 public void addVehiculo(String idVehiculo,Vehiculo vehiculo) throws InsertException, ErrorDeSimulacion {
		 // comprueba que â€œidVehiculoâ€� no existe en el mapa.
		 // Si no existe, lo aÃ±ade a â€œvehiculosâ€� y a â€œmapaDeVehiculosâ€�,
		 // y posteriormente solicita al vehiculo que se mueva a la siguiente
		 // carretera de su itinerario (moverASiguienteCarretera).
		 // Si existe lanza una excepciÃ³n.
		 if(mapaDeVehiculos.containsKey(idVehiculo))
			 throw new InsertException("Se ha añadido dos veces el Vehiculo" + idVehiculo);
		 this.mapaDeVehiculos.put(idVehiculo, vehiculo);
		 this.vehiculos.add(vehiculo);
		 vehiculo.moverASiguienteCarretera();
		 
		 
		 
	}
	
	 public void addCarretera(String idCarretera, CruceGenerico<?> origen, Carretera carretera,
			 CruceGenerico<?> destino) throws InsertException {
			 // comprueba que â€œidCarreteraâ€� no existe en el mapa.
			 // Si no existe, lo aÃ±ade a â€œcarreterasâ€� y a â€œmapaDeCarreterasâ€�,
			 // y posteriormente actualiza los cruces origen y destino como sigue:
			 // - AÃ±ade al cruce origen la carretera, como â€œcarretera salienteâ€�
			 // - AÃ±ade al crude destino la carretera, como â€œcarretera entranteâ€�
			 // Si existe lanza una excepciÃ³n.
		 if(mapaDeCarreteras.containsKey(idCarretera))
			 throw new InsertException("Se ha añadido dos veces la carretera " + idCarretera);
		 this.mapaDeCarreteras.put(idCarretera, carretera);
		 this.carreteras.add(carretera);
		 origen.addCarreteraSalienteAlCruce(destino, carretera);
		 destino.addCarreteraEntranteAlCruce(idCarretera, carretera);
		 
		 
	 }
	 
	 
	 public List<CruceGenerico<?>> getCruces() {
		return cruces;
	}

	public List<Carretera> getCarreteras() {
		return carreteras;
	}

	public String generateReport(int time) {
		 //Preguntar como va el report.
		
	//	 String report = ("# ******* step "+ time +" *******\r\n");
		 String report = "";
		 time++;
	//	 report += System.lineSeparator();		 // genera informe para cruces
		 for(int i = 0; i < this.cruces.size();++i){
			 report += this.cruces.get(i).generaInforme(time);
			 report += System.lineSeparator();
		 }
		 // genera informe para carreteras
		 for(int j = 0; j < this.carreteras.size();++j){
			 report += this.carreteras.get(j).generaInforme(time);
			 report += System.lineSeparator();
		 }
		 // genera informe para vehiculos
		 for(int w = 0; w < this.vehiculos.size();++w){
			 report += this.vehiculos.get(w).generaInforme(time);
			 report += System.lineSeparator();
		 }
		return report;
	}
	 
	public void actualizar() throws ErrorDeSimulacion {
		 // llama al mÃ©todo avanza de cada cruce
		 // llama al mÃ©todo avanza de cada carretera
		
		for (int i = 0; i < carreteras.size(); i++)
			carreteras.get(i).avanza();
		for(int i = 0; i < cruces.size(); i++)
			cruces.get(i).avanza();

		}
	
	public CruceGenerico<?> getCruce(String id) throws NotFoundException {

		 // devuelve el cruce con ese â€œidâ€� utilizando el mapaDeCruces.
		 // sino existe el cruce lanza excepciÃ³n
		if(!mapaDeCruces.containsKey(id))
			throw new NotFoundException("No se ha encontrado el cruce: " + id );
		return mapaDeCruces.get(id);
	}
	
	public Vehiculo getVehiculo(String id) throws NotFoundException {
		
		 // devuelve el vehÃ­culo con ese â€œidâ€� utilizando el mapaDeVehiculos.
		 // sino existe el vehÃ­culo lanza excepciÃ³n.
		if(!mapaDeVehiculos.containsKey(id))
			throw new NotFoundException("No se ha encontrado el vehiculo: " + id);
		return mapaDeVehiculos.get(id);
	}
		
	
	public Carretera getCarretera(String id) throws NotFoundException {
		 // devuelve la carretera con ese â€œidâ€� utilizando el mapaDeCarreteras.
		 // sino existe la carretra lanza excepciÃ³n.
		if(!mapaDeCarreteras.containsKey(id))
			throw new NotFoundException("No se ha encontrado la carretera: " + id);
		return mapaDeCarreteras.get(id);
		}
	
	public void SetAveria(String id, int duration) throws ErrorCarga {
		
		Vehiculo v = mapaDeVehiculos.get(id);
		if(v == null)
			throw new ErrorCarga("No se han podido colocar las averias correctamente");
		v.setAveria(duration);
	}
}
