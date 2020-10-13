package tree;

public class Node<T extends Comparable<T>> {
	// Restricts the data type to those that implement Comparable
	T key;
	Node<T> left;
	Node<T> right;
	
	public Node(T key) {
		this.key = key;
		this.right = null;
		this.left = null;
	}
	
	public T getKey() {
		return key;
	}
}
