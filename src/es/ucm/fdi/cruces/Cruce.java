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
	 


	@Override
	protected CarreteraEntrante CrearCE(Carretera Carr) {

		return new CarreteraEntrante(Carr);
	}
}

