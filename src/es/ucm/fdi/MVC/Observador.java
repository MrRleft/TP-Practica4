package es.ucm.fdi.MVC;

public interface Observador<T> {
	 
	public void addObservador(T o);
	public void removeObservador(T o);

}
