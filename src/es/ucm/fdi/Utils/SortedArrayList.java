package es.ucm.fdi.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class SortedArrayList<E> extends ArrayList<E> {
	 
	private Comparator<E> cmp;
	 public SortedArrayList(Comparator<E> cmp) {
		 
	 }
	 @Override
	 public boolean add(E e) {
	 // programar la inserción ordenada
	 }
	 @Override
	 public boolean addAll(Collection<? extends E> c) {
	 // programar inserción ordenada (invocando a add)
	 }
	 // sobreescribir los métodos que realizan operaciones de
	 // inserción basados en un índice para que lancen excepcion.
	 // Ten en cuenta que esta operación rompería la ordenación.

	 // estos métodos son add(int index, E element),
	 // addAll(int index, Colection<? Extends E>) y E set(int index, E element).

}