package es.ucm.fdi.cruces;

public class CruceCircular extends CruceGenerico{
	
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
		// TODO Auto-generated method stub
		
	}

}
