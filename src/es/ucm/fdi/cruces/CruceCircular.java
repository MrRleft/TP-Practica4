package es.ucm.fdi.cruces;

import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.carreteras.CarreteraEntranteConIntervalo;

public class CruceCircular extends CruceGenerico<CarreteraEntranteConIntervalo>{
	
	private int minValorIntervalo;
	private int maxValorIntervalo;

	public CruceCircular(String id, int minValorIntervalo, int maxValorIntervalo) {
		super(id);
		this.maxValorIntervalo = maxValorIntervalo;
		this.minValorIntervalo = minValorIntervalo;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void actualizaSemaforos() {
	
		/*
	 - Si no hay carretera con sem�foro en verde (indiceSemaforoVerde == -1) entonces se
	 selecciona la primera carretera entrante (la de la posici�n 0) y se pone su
	 sem�foro en verde.
	 - Si hay carretera entrante "ri" con su sem�foro en verde, (indiceSemaforoVerde !=
	 -1) entonces:
	 1. Si ha consumido su intervalo de tiempo en verde ("ri.tiempoConsumido()"):
	 1.1. Se pone el sem�foro de "ri" a rojo.
	 1.2. Si ha sido usada en todos los pasos (�ri.usoCompleto()�), se fija
	 el intervalo de tiempo a ... Sino, si no ha sido usada
	 (�!ri.usada()�) se fija el intervalo de tiempo a ...
	 1.3. Se coge como nueva carretera con sem�foro a verde la inmediatamente
	 Posterior a �ri�.
	*/
		
	}

	@Override
	protected CarreteraEntranteConIntervalo CrearCE(Carretera Carr) {
		// TODO Auto-generated method stub
		return new CarreteraEntranteConIntervalo(Carr, 0);
	}

}
