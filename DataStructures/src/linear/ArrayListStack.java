package linear;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayListStack<T> {
	private ArrayList<T> list;
	
	public ArrayListStack() {
		list = new ArrayList<T>();
	}
	
	public void push(T item) {
		list.add(item);
	}
	
	public T pop() throws NoSuchElementException {
		if (list.size() == 0) {
			throw new NoSuchElementException();
		}
		return list.remove(list.size() - 1);
	}
	
	public T peek() throws NoSuchElementException {
		if (list.size() == 0) {
			throw new NoSuchElementException();
		}
		return list.get(list.size() - 1);
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
