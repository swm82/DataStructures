package linear;

import java.util.NoSuchElementException;

public class Stack<T> {
	private Node<T> front;
	private int size;

	public Stack() {
		front = null;
		size = 0;
	}
	
	public void push(T item) {
		front = new Node<T>(item, front);
		size++;
	}
	
	public T pop() throws NoSuchElementException {
		if (front == null) {
			throw new NoSuchElementException("Stack is currently empty");
		}
		T item = front.data;
		front = front.next;
		size--;
		return item;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public T peek() {
		if (front == null) {
			throw new NoSuchElementException("Stack is currently empty");
		}
		return front.data;
	}
	 public void clear() {
		 front = null;
		 size = 0;
	 }
}
