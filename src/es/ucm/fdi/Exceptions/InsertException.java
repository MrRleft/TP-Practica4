package es.ucm.fdi.Exceptions;

public class InsertException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InsertException() {
		
		super("Problema al insertar algo");
	}
	
	public InsertException(String string) {
		
		super(string);
	}

}
