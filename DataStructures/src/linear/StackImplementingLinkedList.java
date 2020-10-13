package linear;

import java.util.NoSuchElementException;

public class StackImplementingLinkedList<T> {
	private GenLinkedList<T> list;
	
	public StackImplementingLinkedList() {
		list = new GenLinkedList<>();
	}
	
	public T peek() throws NoSuchElementException {
		if (list.isEmpty()) {
			throw new NoSuchElementException();
		}
		return list.getFront();
	}
	
	public void push(T item) {
		list.addFront(item);
	}
	
	public T pop() throws NoSuchElementException {
		if (list.isEmpty()) {
			throw new NoSuchElementException();
		}
		return list.deleteFront();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
