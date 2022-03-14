package projet.utils;

class Node<T> {
	Node previous;
	Node next;
	T content;

	public Node(Node previous, Node next) {
		this.previous = previous;
		this.next = next;
	}
}