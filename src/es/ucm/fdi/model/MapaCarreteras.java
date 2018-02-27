package es.ucm.fdi.model;


import java.util.ArrayList;
import java.util.List;

public class MapaCarreteras {

	
	 private List<Carretera> carreteras;
	 private List<Cruce> cruces;
	 private List<Vehiculo> vehiculos;
	
	 public MapaCarreteras() {
		 // inicializa los atributos a sus constructoras por defecto.
		 // Para carreteras, cruces y veh�culos puede usarse ArrayList.
		 // Para los mapas puede usarse HashMap
		}
	
	 public void addCruce(String idCruce, Cruce cruce) {
		 // comprueba que �idCruce� no existe en el mapa.
		 // Si no existe, lo a�ade a �cruces� y a �mapaDeCruces�.
		 // Si existe lanza una excepci�n.
	}
	
	 public void addVehiculo(String idVehiculo,Vehiculo vehiculo) {
		 // comprueba que �idVehiculo� no existe en el mapa.
		 // Si no existe, lo a�ade a �vehiculos� y a �mapaDeVehiculos�,
		 // y posteriormente solicita al vehiculo que se mueva a la siguiente
		 // carretera de su itinerario (moverASiguienteCarretera).
		 // Si existe lanza una excepci�n.
	}
	
	 public void addCarretera(String idCarretera, Cruce origen, Carretera carretera,
			 Cruce destino) {
			 // comprueba que �idCarretera� no existe en el mapa.
			 // Si no existe, lo a�ade a �carreteras� y a �mapaDeCarreteras�,
			 // y posteriormente actualiza los cruces origen y destino como sigue:
			 // - A�ade al cruce origen la carretera, como �carretera saliente�
			 // - A�ade al crude destino la carretera, como �carretera entrante�
			 // Si existe lanza una excepci�n.
			}
	 
	 public String generateReport(int time) {
		 String report = "";
		 // genera informe para cruces
		 // genera informe para carreteras
		 // genera informe para vehiculos
		return report;
	}
	 
	public void actualizar() {
		 // llama al m�todo avanza de cada cruce
		 // llama al m�todo avanza de cada carretera
		}
	
	public Cruce getCruce(String id) {
		return null;
		 // devuelve el cruce con ese �id� utilizando el mapaDeCruces.
		 // sino existe el cruce lanza excepci�n.
		}
	
	public Vehiculo getVehiculo(String id) {
		return null;
		 // devuelve el veh�culo con ese �id� utilizando el mapaDeVehiculos.
		 // sino existe el veh�culo lanza excepci�n.
		}
	
	public Carretera getCarretera(String id) {
		return null;
		 // devuelve la carretera con ese �id� utilizando el mapaDeCarreteras.
		 // sino existe la carretra lanza excepci�n.
		}
}
