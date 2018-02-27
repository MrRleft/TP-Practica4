package es.ucm.fdi.Exceptions;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super("No se ha encontrado un elemento");	
	}
	
	public NotFoundException(String s) {
		super(s);
	}
	
	
	
}
