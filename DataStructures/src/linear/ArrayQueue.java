package linear;

public class ArrayQueue<T> {
	private T[] queue;
	private int front;
	private int back;
	private int size;
	private int capacity;
	
	public ArrayQueue(int capacity) {
		queue = (T[]) new Object[capacity];
		this.capacity = capacity;
		size = 0;
		front = -1;
		back = -1;
	}
	
	public void enqueue(T item) {
		if (isFull()) {
			System.out.println("FULL");
			return;
		} else if (isEmpty() ) {
			front = 0;
			back = 0;
		} else if (back == capacity - 1) {
			back = 0;
		} else {
			back++;
		}
		queue[back] = item;
		size++;
	}
	
	public T dequeue() {
		if (isEmpty()) {
			System.out.println("empty");
			return null;
		}
		T item = queue[front];
		if (front == back) {
			front = -1;
			back = -1;
		} else if (front == capacity -1) {
			front = 0;
		} else  {
			front++;
		}
		size--;
		return item;
	}
	
	public void traverse() {
		if (isEmpty()) {
			return;
		}
		int trav = front;
		while (trav != back) {
			System.out.print(queue[trav] + " - ");
			if (trav == capacity - 1) {
				trav = 0;
			} else {
				trav++;
			}
		}
		System.out.println(queue[trav]);
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == capacity;
	}
	
	public int size() {
		return size;
	}
}
