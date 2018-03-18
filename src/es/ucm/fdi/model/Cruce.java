package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.ini.IniSection;

 public class Cruce extends ObjetoSimulacion {
	 
	 protected int indiceSemaforoVerde; // lleva el �ndice de la carretera entrante
	 // con el sem�foro en verde
	 protected List<CarreteraEntrante> carreterasEntrantes;

	 // para optimizar las b�squedas de las carreterasEntrantes
	 // (IdCarretera, CarreteraEntrante)
	 protected Map<String,CarreteraEntrante> mapaCarreterasEntrantes;
	 protected Map<Cruce,Carretera> parCarreteraCruce;
	 
	 public Cruce(String id) {
		super(id);
		this.indiceSemaforoVerde = -1;
		this.mapaCarreterasEntrantes = new HashMap<>();
		this.parCarreteraCruce = new HashMap<>();
		this.carreterasEntrantes = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	 public Carretera carreteraHaciaCruce(Cruce cruce) {
		 // devuelve la carretera que llega a ese cruce desde �this�
		return this.parCarreteraCruce.get(cruce);
	 }
	 public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {
		 // a�ade una carretera entrante al �mapaCarreterasEntrantes� y
		 // a las �carreterasEntrantes�
		 CarreteraEntrante cE = new CarreteraEntrante(carretera);
		 this.carreterasEntrantes.add(cE);
		 this.mapaCarreterasEntrantes.put(idCarretera, cE);
		 
		}
	 public void addCarreteraSalienteAlCruce(Cruce destino, Carretera road) {
		 // a�ade una carretera saliente
		 this.parCarreteraCruce.put(destino, road);
		 
		}
	 public void entraVehiculoAlCruce(String idCarretera, Vehiculo vehiculo) throws ErrorDeSimulacion{
		 // a�ade el �vehiculo� a la carretera entrante �idCarretera�
		 this.mapaCarreterasEntrantes.get(idCarretera).entraVehiculo(vehiculo);
		}
	 protected void actualizaSemaforos(){
		 // pone el sem�foro de la carretera actual a �rojo�, y busca la siguiente
		 // carretera entrante para ponerlo a �verde�
		 if(this.indiceSemaforoVerde == -1) {
				this.indiceSemaforoVerde = 0;
				this.carreterasEntrantes.get(0).ponSemaforo(true);
			}
		 else {
			 this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(false);
			 this.indiceSemaforoVerde++;
			 if(this.indiceSemaforoVerde == this.carreterasEntrantes.size())
				 this.indiceSemaforoVerde = 0;
			 this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
		 }
	 }
	 
		@Override
	 public void avanza() throws ErrorDeSimulacion {
		// Si �carreterasEntrantes� es vac�o, no hace nada.
		 // en otro caso �avanzaPrimerVehiculo� de la carretera con el sem�foro verde.
		 // Posteriormente actualiza los sem�foros.
			if (!this.carreterasEntrantes.isEmpty()) {
				this.actualizaSemaforos();
				carreterasEntrantes.get(this.indiceSemaforoVerde).avanzaPrimerVehiculo();
				
			}
		}
		
	 protected String getNombreSeccion() {
			
		 return "junction_report";
		}
		@Override
	protected void completaDetallesSeccion(IniSection is) {
			String detalles = "";
		//	is.setValue("id", this.id);
			//is.setValue("time",0);//arreglar
			for (int i = 0; i <this.carreterasEntrantes.size(); ++i){
				detalles += "(" + this.carreterasEntrantes.get(i).carretera.getID() + ",";
				if (this.carreterasEntrantes.get(i).getSem()) detalles += "green,[";
				else detalles += "red,[";
				for (Vehiculo v : this.carreterasEntrantes.get(i).colaVehiculos)
					detalles += v.id + ",";
				if(this.carreterasEntrantes.get(i).colaVehiculos.size() > 0)
					detalles = detalles.substring(0, detalles.length()-1);
				detalles = detalles.substring(0, detalles.length() );
				detalles += "])";
				if(i < this.carreterasEntrantes.size()-1)
					detalles += ",";
			}
			detalles = detalles.substring(0, detalles.length());
			is.setValue("queues", detalles);
		}
	public boolean carreteraEntranteAqui(Carretera c) {
		
		return this.mapaCarreterasEntrantes.containsKey(c.getID());	
	}
	public Carretera EncuentraCarretera(Cruce proximo) {
		// TODO Auto-generated method stub
		return this.parCarreteraCruce.get(proximo);
	}
}

/*Bucle for que recorre todo un mapa
 * for(Map.Entry<String, CarreteraEntrante> entry : mapaCarreterasEntrantes.entrySet()) {
			entry.avanza();		
				}
 */

