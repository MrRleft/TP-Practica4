package es.ucm.fdi.main;

enum ModoEjecucion {
	 
	BATCH("batch"), GUI("gui"), ERR("error");
	
	private String descModo;
	
	private ModoEjecucion(String modeDesc) {
	
		descModo = modeDesc;
	
	}
	
	public String getModelDesc() {
		
		return descModo;
	
	}

	public static ModoEjecucion parser(String g) {
		ModoEjecucion e;
		
		if (g == null || g.equals(ModoEjecucion.BATCH.descModo))
			e = ModoEjecucion.BATCH;
		else if (g != null && g.equals(ModoEjecucion.GUI.descModo))
			e = ModoEjecucion.GUI;
		else 
			e = ModoEjecucion.ERR;
		return e;
	}
	
}
