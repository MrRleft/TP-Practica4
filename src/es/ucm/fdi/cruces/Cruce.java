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
	 


	 protected void completaDetallesSeccion(IniSection is) {
			String detalles = "";
			for (int i = 0; i <this.carreterasEntrantes.size(); ++i){
				
				detalles += "(" + this.carreterasEntrantes.get(i).getCarretera().getID() + ",";
				if (this.carreterasEntrantes.get(i).getSem()) 
					detalles += "green,[";
				else
					detalles += "red,[";
				for (Vehiculo v : this.carreterasEntrantes.get(i).getColaVehiculos())
					detalles += v.getId() + ",";
				if(this.carreterasEntrantes.get(i).getColaVehiculos().size() > 0)
					detalles = detalles.substring(0, detalles.length()-1);
				detalles = detalles.substring(0, detalles.length() );
				detalles += "])";
				if(i < this.carreterasEntrantes.size()-1)
					detalles += ",";
			}
			detalles = detalles.substring(0, detalles.length());
			is.setValue("queues", detalles);
		}
		 
	@Override
	protected CarreteraEntrante CrearCE(Carretera Carr) {

		return new CarreteraEntrante(Carr);
	}
}

