import java.util.Iterator;
import java.util.NoSuchElementException;

public final class LinkedStack<T> implements StackInterface<T> {
	private Node topNode;

	public LinkedStack() {
		topNode = null;
	}

	private class Node {
		private T data;
		private Node next;

		private Node(T dataPortion) {
			this(dataPortion, null);
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNextNode() {
			return next;
		}

		public void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}

	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	}

	public T pop() {
		T top = peek();
		if (topNode != null)
			topNode = topNode.getNextNode();

		return top;
	}

	public T peek() {
		T top = null;
		if (topNode != null)
			top = topNode.getData();

		return top;
	}

	public boolean isEmpty() {
		return topNode == null;
	}

	public void clear() {
		topNode = null;
	}

	 private class ListIterator<T> implements Iterator<T> {
	        private Node current;

	        public ListIterator(Node first) {
	            current = first;
	        }

	        public boolean hasNext() {
	            return current != null;
	        }

	        public void remove() {
	            throw new UnsupportedOperationException();
	        }

	        public T next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            T item = (T) current.data;
	            current = current.next; 
	            return item;
	        }
	    }

	@Override
	public Iterator<T> iterator() {
       return new ListIterator<T>(topNode);

	}

}