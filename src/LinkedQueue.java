import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedQueue<T> implements QueueInterface<T>, Iterable<T>  {
	private Node firstNode;
	private Node lastNode;

	public LinkedQueue() {
		firstNode = null;
		lastNode = null;
	}

	public void enqueue(T newEntry) {
		Node newNode = new Node(newEntry, null);
		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		lastNode = firstNode;

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

	@Override
	public T dequeue() {
		T front = null;
		if (!isEmpty()) {
			front = firstNode.getData();
			firstNode = firstNode.getNextNode();
			if (firstNode == null)
				lastNode = null;
		}
		return front;
	}

	@Override
	public T getFront() {
		T front = null;
		if (!isEmpty())
			front = firstNode.getData();
		return front;
	}

	@Override
	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}

	@Override  
	public void clear() {
		firstNode = null;
		lastNode = null;
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
        return new ListIterator<T>(firstNode);

	}

	
}
