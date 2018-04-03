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
	 - Si no hay carretera con semáforo en verde (indiceSemaforoVerde == -1) entonces se
	 selecciona la primera carretera entrante (la de la posición 0) y se pone su
	 semáforo en verde.
	 - Si hay carretera entrante "ri" con su semáforo en verde, (indiceSemaforoVerde !=
	 -1) entonces:
	 1. Si ha consumido su intervalo de tiempo en verde ("ri.tiempoConsumido()"):
	 1.1. Se pone el semáforo de "ri" a rojo.
	 1.2. Si ha sido usada en todos los pasos (“ri.usoCompleto()”), se fija
	 el intervalo de tiempo a ... Sino, si no ha sido usada
	 (“!ri.usada()”) se fija el intervalo de tiempo a ...
	 1.3. Se coge como nueva carretera con semáforo a verde la inmediatamente
	 Posterior a “ri”.
	*/
		if(this.indiceSemaforoVerde == -1) {
			this.carreterasEntrantes.get(0).ponSemaforo(true);
			this.indiceSemaforoVerde = 0;		
		}
		else {
			if(!this.carreterasEntrantes.get(this.indiceSemaforoVerde).tiempoConsumido()) {
				this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(false);
				this.carreterasEntrantes.get(this.indiceSemaforoVerde).restartTime();
				int nuevoInt;
			
				if(this.carreterasEntrantes.get(this.indiceSemaforoVerde).usoCompleto()) 
					//m´ın(intervaloDeTiempo + 1, maxValorIntervalo)
					nuevoInt = Math.min(this.carreterasEntrantes.get(this.indiceSemaforoVerde).getInt() + 1,
							this.maxValorIntervalo);
				else 
					//m´ax(intervaloDeTiempo − 1, minValorIntervalo).
					nuevoInt = Math.max(this.carreterasEntrantes.get(this.indiceSemaforoVerde).getInt() - 1,
							this.minValorIntervalo);
				this.carreterasEntrantes.get(this.indiceSemaforoVerde).setInt(nuevoInt);
				this.indiceSemaforoVerde++;
				this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
			}
		}
	}

	@Override
	protected CarreteraEntranteConIntervalo CrearCE(Carretera Carr) {
		// TODO Auto-generated method stub
		return new CarreteraEntranteConIntervalo(Carr, 0);
	}

}
