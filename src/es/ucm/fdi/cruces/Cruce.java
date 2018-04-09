package es.ucm.fdi.cruces;



import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.carreteras.CarreteraEntrante;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.vehiculos.Vehiculo;


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

