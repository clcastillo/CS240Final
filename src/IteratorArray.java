import java.util.NoSuchElementException;

public class IteratorArray<T> implements IteratorInterface<T> {
	private ListInterface<T> list;
	private int nextPosition;
	private boolean wasNextCalled;

	public IteratorArray(ListInterface<T> aList) {
		list = aList;
		nextPosition = 0;
		wasNextCalled = false;
	}

	public boolean hasNext() {
		return nextPosition < list.getLength();
	}

	public T next() {
		if (hasNext()) {
			wasNextCalled = true;
			nextPosition++;
			return list.getEntry(nextPosition);
		} else
			throw new NoSuchElementException("Illegal call to next(); " + "iterator is after end of list.");
	}

	public void remove() {
		if (wasNextCalled) {
			list.remove(nextPosition);
			nextPosition--;
		} else
			throw new IllegalStateException("Illegal call to remove(); " + "next() was not called.");
	}
}
