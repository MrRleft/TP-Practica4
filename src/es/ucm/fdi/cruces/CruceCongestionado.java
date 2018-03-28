package es.ucm.fdi.cruces;

import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.carreteras.CarreteraEntranteConIntervalo;

public class CruceCongestionado extends CruceGenerico<CarreteraEntranteConIntervalo>{

	public CruceCongestionado(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	 protected void actualizaSemaforos() {
		/* - Si no hay carretera con semáforo en verde (indiceSemaforoVerde == -1) entonces se
		 selecciona la carretera que tenga más vehículos en su cola de vehículos.
		 - Si hay carretera entrante "ri" con su semáforo en verde, (indiceSemaforoVerde !=
		 -1) entonces:
		 1. Si ha consumido su intervalo de tiempo en verde ("ri.tiempoConsumido()"):
		 1.1. Se pone el semáforo de "ri" a rojo.
		 1.2. Se inicializan los atributos de "ri".
		 1.3. Se busca la posición "max" que ocupa la primera carretera entrante
		 distinta de "ri" con el mayor número de vehículos en su cola.
		 1.4. "indiceSemaforoVerde" se pone a "max".
		 1.5. Se pone el semáforo de la carretera entrante en la posición "max" ("rj")
		 a verde y se inicializan los atributos de "rj", entre ellos el
		 "intervaloTiempo" a Math.max(rj.numVehiculosEnCola()/2,1).
		 */
	 }

	@Override
	protected CarreteraEntranteConIntervalo CrearCE(Carretera Carr) {
		// TODO Auto-generated method stub
		return new CarreteraEntranteConIntervalo(Carr, 0);
	}

}
