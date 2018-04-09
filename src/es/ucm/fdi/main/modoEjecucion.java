package es.ucm.fdi.main;

enum ModoEjecucion {
	 
	BATCH("batch"), GUI("gui");
	
	private String descModo;
	
	private ModoEjecucion(String modeDesc) {
	
		descModo = modeDesc;
	
	}
	
	private String getModelDesc() {
		
		return descModo;
	
	}
	
}
