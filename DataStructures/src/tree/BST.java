package tree;

import java.util.ArrayList;
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
	
	// Iterative search for target
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
	
	// Recursive search for target
	public T search(T target) {
		return search(root, target);
	}
	

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
	

	// Iterative delete
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
	
	// Recursive delete target
	public T recDelete(T key) {
		return recDelete(root, key);
	}
	
	private T recDelete(Node root, T key) {
		Node deletedTree = recursiveDelete(root, key);
		if (deletedTree == null) return null;
		else return deletedTree.key;
	}
	
	private Node recursiveDelete(Node root, T key) {
		if (root == null) {
			throw new NoSuchElementException(key + " is not in the tree");
		}
		int cmp = key.compareTo(root.key);
		if (cmp < 0) {
			root.left = recursiveDelete(root.left, key);
		} else if (cmp > 0) {
			root.right = recursiveDelete(root.right, key);
		} else {
			if (root.right == null) return root.left;
			if (root.left == null) return root.right;
			T x = max(root.left);
			root.left = deleteMax(root.left);
			root.key = x;
		}
		return root;
	}
	
	public T max(Node root) {
		if (root == null) {
			return null;
		}
		if (root.right == null) return root.key;
		return max(root.right);
	}
	
	public Node deleteMax(Node root) {
		if (root == null) return null;
		if (root.right == null) {
			return root.left;
		}
		root.right = deleteMax(root.right);
		return root;
	}
	
	
	// Reverse order of tree
	public void reverse() {
		root = recursiveReverse(root);
	}
	
	public Node recursiveReverse(Node root) {
		if (root == null) {
			return null;
		}
		Node temp = root.left;
		root.left = root.right;
		root.right = temp;
		root.left = recursiveReverse(root.left);
		root.right = recursiveReverse(root.right);
		return root;
	}
	
	
	public void printLeft() {
		Node trav = root;
		while (trav != null) {
			System.out.print(trav.key + " -> ");
			trav = trav.left;
		}
		System.out.println();
	}

	public void printRight() {
		Node trav = root;
		while (trav != null) {
			System.out.print(trav.key + " -> ");
			trav = trav.right;
		}
		System.out.println();
	}
	
	// Accumulates, in a given array list, all entries in a BST whose keys are in a given range,
    // including both ends of the range - i.e. all entries x such that min <= x <= max. 
    // The accumulation array list, result, will be filled with node data entries that make the cut. 
    // The array list is already created (initially empty) when this method is first called.
	public void keysInRange(T min, T max, ArrayList<T> result) {
		keysInRange(root, min, max, result);
	}
	
    public void keysInRange(Node root, T min, T max, ArrayList<T> result) {
        if (root == null) return; 
        int cmpMin = root.key.compareTo(min);
        int cmpMax = root.key.compareTo(max);
        if (cmpMin >= 0 && cmpMax <= 0) result.add(root.key);
        if (cmpMin > 0) keysInRange(root.left, min, max, result);
        if (cmpMax < 0) keysInRange(root.right, min, max, result);
     }
    
    // Find k'th largest entry in BST
    // Requires a rightSize field in Node that holds the number of right child nodes
    public T kthLargest(Node root, int k) {
    	if (root == null) {
    		return null;
    	}
    	if (root.rightSize == k - 1) {
    		return root.key;
    	}
    	if (root.rightSize < k - 1) {
    		return kthLargest(root.left, k - root.rightSize - 1);
    	}
    	return kthLargest(root.right, k);
     }
}
