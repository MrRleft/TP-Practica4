package es.ucm.fdi.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class SortedArrayList<E> extends ArrayList<E> {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Comparator<E> cmp;
	
	 public SortedArrayList(Comparator<E> cmp) {
		this.cmp = cmp; 
	    }
	 
	 public  SortedArrayList() {
		this.cmp = null;
		}
	 
	 @Override
	 public boolean add(E e) {
		int idx = 0;
		if (!isEmpty()) {
			idx = findInsertionPoint(e);
		}
		super.add(idx, e);
		return true;
	}
		 
	 
	 
	 protected  int compare(E k1, E k2) {
		return this.cmp.compare(k1, k2);
	}
	 
	 protected  int findInsertionPoint(E o, int low, int high) {

		while (low <= high) {
			int mid = (low + high) >>> 1;
			int delta = compare(get(mid), o);
			if (delta > 0) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
	 
	 public  int findInsertionPoint(E o) {
		return findInsertionPoint(o, 0, size() - 1);
	}
	 
	 @Override
	 public boolean addAll(Collection<? extends E> c) {
		 Iterator<? extends E> i = c.iterator();
		boolean changed = false;
		while (i.hasNext()) {
			boolean ret = add(i.next());
			if (!changed) {
				changed = ret;
			}
		}
		return changed;
	}

	 

}