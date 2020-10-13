package linear;

import java.util.NoSuchElementException;

public class GenLinkedList<T> {
	Node<T> head;
	int size;
	
	public GenLinkedList() {
		head = null;
		size = 0;
	}
	
	public void addFront(T item) {
		head = new Node<T>(item, head);
		size++;
	}
	
	public T getFront() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException();
		}
		return head.data;
	}

	public T deleteFront() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException();
		}
		T data = head.data;
		head = head.next;
		size--;
		return data;
	}
	
	public boolean search(T target) {
		Node<T> trav = head;
		while (trav != null) {
			if (trav.data.equals(target)) {
				return true;
			}
			trav = trav.next;
		}
		return false;
	}
	
	public void traverse() {
		if (head == null) {
			System.out.println("Empty List");
			return;
		}
		Node<T> trav = head;
		while (trav != null) {
			System.out.print(trav.data);
			if (trav.next != null) {
				System.out.print(" -> ");
			}
			trav = trav.next;
		}
		System.out.println();
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	
	public void reverse() {
		if (head == null) {
			return;
		}
		if (head.next == null) {
			return;
		}
		Node<T> first = null, mid = head, front = head.next;
		while (mid != null) {
			mid.next = first;
			first = mid;
			mid = front;
			if (front != null) {
				front = front.next;
			}
		}
		head = first;
	}
	
	
	// PSET 5 - adaptive shuffle for sequential search
	public boolean moveTowardFront(T target) {
		
	}

}
