package projet.utils;

public class GenericLinkedList {
	int count;
	Node head;
	Node tail;

	public GenericLinkedList() {
		head = new Node(null,null);
		tail = new Node(null,null);
		head.next = tail;
		tail.previous = head;
		count=2;
	}

	<T> void addNode(T element) {
		Node bob = new Node(tail.previous,tail);
		bob.content = element;
		bob.previous.next = bob;
		bob.next.previous = bob;
		count++;
	}

	void removeNode(Node bob) {
		if (bob==head && bob.next==tail) return;
		if (bob==head) {	// note : if head and tail are removable
			bob.next = head;
			bob = null;
		} else if (bob==tail) {
			bob.previous = tail;
			bob = null;
		} else {
			bob.previous.next = bob.next;
			bob.next.previous = bob.previous;
			bob = null;
		}
		count--;
	}
}
