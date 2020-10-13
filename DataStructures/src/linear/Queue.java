package linear;

import java.util.NoSuchElementException;

public class Queue<T> {
	Node<T> front;
	Node<T> back;
	int size;
	
	public Queue() {
		front = null;
		back = null;
		size = 0;
	}
	
	public void enqueue(T item) {
		Node<T> node = new Node<>(item, null);
		if (isEmpty()) {
			front = node;
		} else {
			back.next = node;
		}
		back = node;
		size++;
	}
	
	public T dequeue() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T item = front.data;
		front = front.next;
		size--;
		return item;
	}
	
	public int size() {
		return size();
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public void traverse() {
		Node<T> trav = front;
		while (trav != null) {
			System.out.print(trav);
			trav = trav.next;
		}
		System.out.println();
	}

}
