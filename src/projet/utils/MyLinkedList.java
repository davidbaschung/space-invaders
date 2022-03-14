package projet.utils;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedList<T> extends AbstractSequentialList implements Iterator<Node> {

	Node<T> head;
	Node<T> tail;
	Node<T> currentNode;

	@Override
	public Object get(int index) {
//		return super.get(index);
		int i=0;
		Node<T> currentNode = head;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
			i++;
			if (i==index) return i;
		}
		return null;
	}

	int nextIndex(Node<T> node) {
		Node<T> currentNode = head;
		for (int i=0; i<size(); i++) {
			if (currentNode==node) return i+1;
		}
		return -1;
	}

	int previousIndex(Node<T> node) {
		Node<T> currentNode = head;
		for (int i=0; i<size(); i++) {
			if (currentNode==node) return i+1;
		}
		return -1;
	}

	@Override
	public ListIterator listIterator(int index) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		int size=0, i=0;
		Node<T> currentNode = head;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
			size++;
		}
		return size;
	}

	@Override
	public void add(int index, Object element) {
//		super.add(index, element);
		Node newNode = new Node(tail.previous,tail);
		newNode.content = element;
		newNode.previous.next = newNode;
		newNode.next.previous = newNode;
	}

	@Override
	public Object set(int index, Object element) {
//		return super.set(index, element);
		currentNode.content = (T) element;
		return currentNode;
	}

	@Override
	public Object remove(int index) {
//		super.remove(index);
		Node<T> oldNode = (Node) get(index);
		if (oldNode==head && oldNode.next==tail) return oldNode;
		if (oldNode==head) {	// note : if head and tail are removable
			oldNode.next = head;
			oldNode = null;
		} else if (oldNode==tail) {
			oldNode.previous = tail;
			oldNode = null;
		} else {
			oldNode.previous.next = oldNode.next;
			oldNode.next.previous = oldNode.previous;
			oldNode = null;
		}
		return oldNode;
	}

	@Override
	public boolean hasNext() {
		if (currentNode.next != null) {
			return true;
		}
		return false;
	}

	public boolean hasPrevious() {
		if (currentNode.previous != null) {
			return true;
		}
		return false;
	}

	@Override
	public Node next() {
		if (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}

	public Node previous() {
		if (currentNode.next != null) {
			currentNode = currentNode.previous;
		}
		return currentNode;
	}
}
