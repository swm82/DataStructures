package linear;

import java.util.NoSuchElementException;

public class CircularQueue<T> {
	private Node rear;
	private int size;
	
	public CircularQueue() {
		rear = null;
		size = 0;
	}

	private class Node {
		T item;
		Node next;
	}
	
	public void enqueue(T item) {
		if (rear == null) {
			rear = new Node();
			rear.item = item;
			rear.next = rear;
		} else {
			Node node = new Node();
			node.item = item;
			node.next = rear.next;
			rear.next = node;
			rear = node;
		}
		size++;
		
	}
	
	public T dequeue() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T item = rear.next.item;
		if (rear.next == rear) {
			rear = null;
		} else {
			rear.next = rear.next.next;
		}
		size--;
		return item;
	}
	
	public boolean isEmpty() {
		return rear == null;
		
	}
	
	public int size() {
		int count = 0;
		if (rear == null) {
			return count;
		}
		Node trav = rear.next;
		while (trav != rear) {
			count++;
		}
		return ++count;
	}
	
	public void traverse() {
		if (isEmpty()) {
			System.out.println("EMPTY");
			return;
		}
		Node trav = rear.next;
		while (trav != rear) {
			System.out.print(trav.item + " -> ");
			trav = trav.next;
		}
		System.out.println(trav.item);
	}
	
	public CircularQueue<T> evenSplit() {
		int originalSize = size;
		CircularQueue<T> temp = new CircularQueue<T>();
		int i = 1;
		while (i <= originalSize) {
			T item = dequeue();
			if (i % 2 == 0) {
				temp.enqueue(item);
			} else {
				enqueue(item);
			}
			i++;
		}
		return temp;
	}

}
