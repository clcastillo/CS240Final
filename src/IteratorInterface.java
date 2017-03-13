
public interface IteratorInterface<T> {
	/**
	 * Detects whether this iterator has gone beyond the last entry in the list.
	 * 
	 * @return true if the iterator has another entry to return when traversing
	 *         the list forward; otherwise returns false
	 */
	public boolean hasNext();

	/**
	 * Retrieves the next entry in the list and advances this iterator by one
	 * position.
	 * 
	 * @return a reference to the next entry in the iteration, if one exists
	 * @throws NoSuchElementException
	 *             if the iterator had reached the end already, that is, if
	 *             hasNext() is false
	 */
	public T next();
	  /** Removes from the list the last entry that either next()
    or previous() has returned.
    Precondition: next() or previous() has been called, but the
          iterator's remove() or add() method has not been called
          since then. That is, you can call remove only once per
          call to next() or previous(). The list has not been altered
          during the iteration except by calls to the iterator's
          remove(), add(), or set() methods.
    @throws IllegalStateException if next() or previous() has not
          been called, or if remove() or add() has been called
          already after the last call to next() or previous()
    @throws UnsupportedOperationException if the iterator does not
          permit a remove operation */
public void remove(); 


} // end ListIterator