package es.ucm.fdi.constructores;

import es.ucm.fdi.events.Evento;
import es.ucm.fdi.ini.IniSection;

public abstract class ConstructorEventos {

	 // cada clase dará los valores correspondientes a estos atributos
	 // en la constructora
	 protected String etiqueta; // etiqueta de la entrada (“new_road�?, etc..)
	 protected String[] claves; // campos de la entrada (“time�?, “vehicles�?, etc.)
	 
	 public ConstructorEventos() {
		 this.etiqueta = null;
		 this.claves = null;
	 }

	 public abstract Evento parser(IniSection section);
	 

	 protected static String identificadorValido(IniSection seccion, String clave) {
		 String s = seccion.getValue(clave);
		 if (!esIdentificadorValido(s))
			 throw new IllegalArgumentException("El valor " + s + " para " + clave +
			 " no es un ID valido");
		 else return s;
	}

		// identificadores v�lidos
		// s�lo pueden contener letras, n�meros y subrayados
	 private static boolean esIdentificadorValido(String id) {
			
			return id != null && id.matches("[a-z0-9_]+");
	 }
	 
	 protected static int parseaInt(IniSection seccion, String clave) {
		 String v = seccion.getValue(clave);
		 if (v == null)
		 throw new IllegalArgumentException("Valor inexistente para la clave: " +
		  clave);
		 else return Integer.parseInt(seccion.getValue(clave));
		 }
	 
	 protected static int parseaInt(IniSection seccion, String clave, int valorPorDefecto) {
		  String v = seccion.getValue(clave);
		  return (v != null) ? Integer.parseInt(seccion.getValue(clave)) :
		  valorPorDefecto;
	 }
	 
	 protected static int parseaIntNoNegativo(IniSection seccion, String clave,
			 int valorPorDefecto) {
		  
		 int i = ConstructorEventos.parseaInt(seccion, clave, valorPorDefecto);
		 if (i < 0)
			 throw new IllegalArgumentException("El valor " + i + " para " + clave +
					 " no es un ID valido");
		 else 
			 return i;
	 }

	 protected static double parseaDouble(IniSection seccion,String clave) {
		  
		 double i = Double.parseDouble(clave);
		 if (i < 0)
			 throw new IllegalArgumentException("El valor " + i + " para " + clave +
					 " no es un ID valido");
		 else 
			 return i;
	 }
	 
	 protected static long parseaLong(IniSection seccion,String clave) {
		  
		 long i = Long.parseLong(clave);
		 if (i < 0)
			 throw new IllegalArgumentException("El valor " + i + " para " + clave +
					 " no es un ID valido");
		 else 
			 return i;
	 }
	 
	 protected static String[] toStringToList(IniSection seccion,String clave) {
		 
		 String v = seccion.getValue(clave);
		 String[] vlist = v.split(",");
		 for (String i: vlist)
			 if (!esIdentificadorValido(i))
				 throw new IllegalArgumentException("El valor " + i + " para " + clave +
				 " no es un ID valido");
		 return vlist;
	 }

	
}