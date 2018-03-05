package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public abstract class ObjetoSimulacion {

	 protected String id;
	 
	 public ObjetoSimulacion(String id) {
		 this.id = id;
	 }
	 public String getId() {
		return id;
	 }

	 @Override
	 public String toString() {
		return id;
		 
	 }

	 public String generaInforme(int tiempo) {
		 IniSection is = new IniSection(this.getNombreSeccion());
		 is.setValue("id", this.id);
		 is.setValue("time", tiempo);
		 this.completaDetallesSeccion(is);
		 return is.toString();
	}
	
	protected abstract void completaDetallesSeccion(IniSection is);
	
	public abstract String getNombreSeccion();
		// los m�todos getNombreSeccion y completaDetallesSeccion
		// tendr�n que implementarlos cada subclase de ObjetoSimulacion
	
	 public abstract void avanza();
	 
	 


}
