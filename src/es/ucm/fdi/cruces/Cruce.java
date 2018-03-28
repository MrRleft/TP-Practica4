package es.ucm.fdi.cruces;



import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.carreteras.CarreteraEntrante;


 public class Cruce extends CruceGenerico<CarreteraEntrante> {
	 
	 public Cruce(String id) {
		super(id);
	}
	 
	

	 protected void actualizaSemaforos(){

		 this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(false);
		 this.indiceSemaforoVerde++;
		 if(this.indiceSemaforoVerde == this.carreterasEntrantes.size())
			 this.indiceSemaforoVerde = 0;
		 this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
		 
	 }
	 

	public boolean carreteraEntranteAqui(Carretera c) {
		return this.mapaCarreterasEntrantes.containsKey(c.getID());	
	}
	
	public Carretera EncuentraCarretera(Cruce proximo) {
		return this.parCarreteraCruce.get(proximo);
	}
}

