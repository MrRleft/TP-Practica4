package es.ucm.fdi.model.cruces;



import es.ucm.fdi.model.carreteras.Carretera;
import es.ucm.fdi.model.carreteras.CarreteraEntrante;
import es.ucm.fdi.model.ini.IniSection;
import es.ucm.fdi.model.vehiculos.Vehiculo;


 public class Cruce extends CruceGenerico<CarreteraEntrante> {
	 
	 public Cruce(String id) {
		super(id);
	}
	 
	

	 protected void actualizaSemaforos(){

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
	protected CarreteraEntrante CrearCE(Carretera Carr) {

		return new CarreteraEntrante(Carr);
	}
}

