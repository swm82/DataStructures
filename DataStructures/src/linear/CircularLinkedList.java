package linear;

public class CircularLinkedList<T> {
	Node<T> back;
	int size;
	public CircularLinkedList() {
		back = null;
		size = 0;
	}
	
	public void addToBack(T item) {
		Node<T> node = new Node<T>(item, null);
		if (back == null) {
			back = node;
			node.next = back;
		} else {
			node.next = back.next;
			back.next = node;
			back =  node;
		}
	}
	
	public void addToFront(T item) {
		Node<T> node = new Node<T>(item, null);
		if (back == null) {
			back = node;
			node.next = back;
		} else if (back.next == back) {
			back.next = node;
			node.next = back;
		} else {
			node.next = back.next;
			back.next = node;
		}

	}
	
	public void traverse() {
		if (back == null) {
			System.out.println("Empty List");
		} else if (back.next == back) {
			System.out.println(back);
		} else {
			Node<T> trav = back.next;
			while (trav != back) {
				System.out.print(trav.data + " -> ");
				trav = trav.next;
			}
			System.out.println(back);
		}
	}
	
	public void addAll(T... items) {
		for (T item : items) {
			addToBack(item);
		}
	}
	
	public void clearList() {
		back = null;
	}
	
	
	// Delete first occurrence of item
	public boolean deleteFirst(T target) {
		if (back == null) {
			return false;
		}
		Node<T> front = back.next;
		Node<T> prev = null;
		while (front != back) {
			if (front.data.equals(target)) {
				if (prev == null) {
					back.next = front.next;
				} else {
					prev.next = front.next;
				}

				return true;
			}
			prev = front;
			front = front.next;	
		}
		if (front.data.equals(target)) {
			prev.next = front.next;
			back = prev;
			return true;
		}
		return false;
	}
	
	// Add item after first occurrence of target.  Return boolean if successful
	public boolean addAfter(T target, T newItem) {
		if (back == null) {
			return false;
		}
		Node<T> front = back.next;
		while (front != back) {
			if (front.data.equals(target)) {
				Node<T> node = new Node<T>(newItem, front.next);
				front.next = node;
				return true;
			}
			front = front.next;
		}
		if (front.data.equals(target)) {
			Node<T> node = new Node<T>(newItem, front.next);
			front.next = node;
			back = node;
			return true;
		}
		return false;
	}
	
	// TODO:
	// Recursively delete all occurences of target
	public Node<T> deleteAllOccurencesRecursive(T target) {
		return null;
	}
	
}
