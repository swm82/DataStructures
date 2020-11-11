package tree;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {
	private ArrayList<T> items;
	private int n = 0;
	
	public Heap() {
		items = new ArrayList<T>();
		items.add(null);
	}
	
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public void insert(T item) {
		items.add(item);
		n++;
		swim(n);
		
	}
	
	public T delete() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty Queue");
		}
		T max = items.get(1);
		swap(1, n);
		items.remove(n);
		n--;
		sink(1);
		return max;	
		
	}
	
	private void swim(int child) {
		while (child > 1) {
			int parent = child/2;
			if (less(parent, child)) {
				swap(parent, child);
				child = parent;
			} else {
				break;
			}
		}
	}
	
	private void sink(int k) {
		int parent = k;
		while (2*parent <= n) {
			int child = 2*parent;
			if (child < n && less(child, child + 1)) {
				child++;
			}
			if (!less(parent, child)) break;
			swap(parent, child);
			parent = child;
		}
	}
	
	private boolean less(int parent, int child) {
		if (items.get(parent).compareTo(items.get(child)) < 0) {
			return  true;
		}
		return false;
	}
	
	private void swap(int parent, int child) {
		T temp = items.get(parent);
		items.set(parent, items.get(child));
		items.set(child, temp);
	}
	
	public String toString() {
		return items.toString();
	}

}
