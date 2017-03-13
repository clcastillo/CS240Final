import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedList<T> implements ADTLISTInterface<T> {
	private Node firstNode;
	private int numberOfEntries = 0;

	public LinkedList() {
		clear();
	}

	public final void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}

	private Node getNodeAt(int givenPosition) {
		assert !empty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;

		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		assert currentNode != null;
		return currentNode;
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
	public boolean add(T item) {
		Node newNode = new Node(item);
		if (empty())
			firstNode = newNode;
		else {
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode);
		}
		numberOfEntries++;

		return true;
	}

	@Override
	public boolean add(T item, int location) {
		boolean isSuccessful = true;
		if ((location >= 1) && (location <= numberOfEntries + 1)) {
			Node newNode = new Node(item);
			if (location == 1) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else {
				Node nodeBefore = getNodeAt(location - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		} else
			isSuccessful = false;
		return isSuccessful;
	}

	@Override
	public boolean removeItem(int location) {

		T result = null;
		if ((location >= 1) && (location <= numberOfEntries)) {
			assert !empty();
			if (location == 1) {
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
			} else {
				Node nodeBefore = getNodeAt(location - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);

				result = nodeToRemove.getData();
			}
			numberOfEntries--;
		}
		return true;
	}

	@Override
	public boolean remove() {
		for (int i = 0; i < numberOfEntries; i++) {
			removeItem(i);
		}
		firstNode = null;
		numberOfEntries = 0;
		return true;
	}

	@Override
	public boolean replace(int location, T item) {

		boolean isSuccessful = true;
		if ((location >= 1) && (location <= numberOfEntries)) {
			assert !empty();
			Node desiredNode = getNodeAt(location);
			desiredNode.setData(item);
		} else
			isSuccessful = false;
		return isSuccessful;
	}

	@Override
	public T look(int item) {
		T result = null;
		if ((item >= 1) && (item <= numberOfEntries)) {
			assert !empty();
			result = getNodeAt(item).getData();
		}
		return result;
	}

	@Override
	public T[] lookAll() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}

	@Override
	public int getCount() {
		return numberOfEntries;
	}

	@Override
	public boolean empty() {
		boolean result;
		if (numberOfEntries == 0) {
			assert firstNode == null;
			result = true;
		} else {
			assert firstNode != null;
			result = false;
		}
		return result;
	}

	public boolean checkforItem(T item) {
		boolean found = false;
		T result = null;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (item.equals(currentNode.getData())) {
				found = true;
				result = currentNode.getData();
			} else
				currentNode = currentNode.getNextNode();
		}
		return found;
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
