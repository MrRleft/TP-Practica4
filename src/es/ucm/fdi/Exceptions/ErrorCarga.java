package es.ucm.fdi.Exceptions;

public class ErrorCarga extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ErrorCarga(String S) {
		
		super(S);
		
	}
		
	public ErrorCarga() {
		
		super("Ha habido un problema al cargar un elemento");
	}
	
}
