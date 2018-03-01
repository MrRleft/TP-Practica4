package es.ucm.fdi.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class SortedArrayList<E> extends ArrayList<E> {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Comparator<E> cmp;
	 public SortedArrayList(Comparator<E> cmp) {
		 
	 }
	 @Override
	 public boolean add(E e) {
	 // programar la inserci�n ordenada
	 }
	 @Override
	 public boolean addAll(Collection<? extends E> c) {
	 // programar inserci�n ordenada (invocando a add)
	 }
	 // sobreescribir los m�todos que realizan operaciones de
	 // inserci�n basados en un �ndice para que lancen excepcion.
	 // Ten en cuenta que esta operaci�n romper�a la ordenaci�n.

	 // estos m�todos son add(int index, E element),
	 // addAll(int index, Colection<? Extends E>) y E set(int index, E element).

}