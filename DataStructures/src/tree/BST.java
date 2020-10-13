package tree;

import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
	Node root;
	
	private class Node {
		T key;
		Node left;
		Node right;
		
		public Node(T key) {
			this.key = key;
			this.left = null; this.right = null;
		}
	}
	
	public BST() {
		this.root = null;
	}
	
	public T iterSearch(T target) {
		Node curr = root;
		while (curr != null) {
			int cmp = target.compareTo(curr.key);
			if (cmp == 0) {
				return curr.key;
			}
			curr = cmp < 0 ? curr.left : curr.right;
		}
		return null;
	}
	
	public void insert(T key) throws IllegalArgumentException {
		root = insert(root, key);
	}
	
	public Node insert(Node root, T key)  throws IllegalArgumentException {
		if (root == null) {
			return new Node(key);
		}
		int cmp = key.compareTo(root.key);
		if (cmp == 0) {
			throw new IllegalArgumentException("Duplicate key: " + key);
		} else if (cmp < 0) {
			root.left = insert(root.left, key);
		} else {
			root.right = insert(root.right, key);
		}
		return root;
	}
	
	public void iterInsert(T key) throws IllegalArgumentException {
		Node parent = null, curr = root;
		int cmp = 0;
		while (curr != null) {
			cmp = key.compareTo(curr.key);
			if (cmp == 0) throw new IllegalArgumentException("Duplicate key: " + key);
			parent = curr;
			curr = cmp < 0 ? curr.left : curr.right;
		}
		Node temp = new Node(key);
		if (parent == null) {
			root = temp;
			return;
		}
		if (cmp < 0) {
			parent.left = temp;
		} else {
			parent.right = temp;
		}
		
	}
	
	public T search(T target) {
		return search(root, target);
	}
	
	// Returns the object so that it can be used, instead of just the comparison value
	// i.e. imagine a student object, may be compared by id, but you want to use the name of the result
	public T search(Node root, T target) {
		if (root == null) {
			return null;
		}
		int cmp = target.compareTo(root.key);
		if (cmp == 0) {
			return root.key;
		}
		return cmp < 0 ? search(root.left, target) : search(root.right, target);
		
	}
	
	public T recursiveDelete(Node root, T key) {
		if (root == null) {
			throw new NoSuchElementException(key + " is not in the tree");
		}

		
	}
	
	public T delete(T key) {
		if (root == null) { // Tree is empty
			throw new NoSuchElementException(key + " is not in the tree");
		}
		Node target = root, parent = null;
		int cmp = 0;
		while (target != null) {
			cmp = key.compareTo(target.key);
			if (cmp == 0) {
				break; // Target found, hold in target
			}
			parent = target;
			target = cmp < 0 ? target.left : target.right;
		}
		if (target == null) { // Target not found
			throw new NoSuchElementException(key + " is not in the tree");
		}
		if (target.left != null && target.right != null) { // Node has 2 children
			Node nextLargestTrav = target.left; // Go one node left
			parent = target; // Set parent to target in case x.left is the next largest
			while (nextLargestTrav.right != null) { // Continue right to find the largest node in the subtree (inline predecessor)
				parent = nextLargestTrav;
				nextLargestTrav = nextLargestTrav.right;
			}
			target.key = nextLargestTrav.key; // move next largest value in subtree, to the root of the subtree
			target = nextLargestTrav; // leave the old target (to delete) to be handled depending upon if it has 1 or no children..
			// It falls through to next conditionals
		}
		// The following case handles both leaf deletion, as well as node deletion with one child
		// It also deletes the target from prev conditional
		if (parent == null) { // Root is node to delete -- Also handles leaf node by assigning target.left or target.right (both null) to the root.. Root becomes null
			if (target.left != null) {
				root = target.left;
			} else {
				root = target.right;
			}
		}
		Node temp = target.left == null ? target.right : target.left; // handles leaf deletion.. if target.right and left are both null, simply assigns null to the child of the parent (left or right)
		if (target == parent.right) {
			parent.right = temp;
		} else {
			parent.left = temp;
		}
		return target.key;
	}
}
