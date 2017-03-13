import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.NoSuchElementException;

public class DictionaryVector<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
	private Vector<Entry> dictionary;
	private int numberOfEntries;

	public DictionaryVector() {
		dictionary = new Vector<Entry>();
	}

	public DictionaryVector(int initialCapacity) {
		dictionary = new Vector<Entry>(initialCapacity);
	} // end
		// constructor

	private class Entry {
		private K key;
		private V value;
		private List<V> valueList = new ArrayList<V>();

		private Entry(K searchKey, V dataValue) {
			key = searchKey;
			valueList.add(dataValue);
			// value = dataValue;

		} // end constructor

		private K getKey() {
			return key;
		}

		private List<V> getValue() {
			return valueList;
		} // end getValue

		private void addValue(V newValue) {// rreplace which one on the the list
											// int and the newValue
			valueList.add(newValue);
		} // end setValue
	} // end Entry

	@Override
	public List<V> add(K key, V value) {
		List<V> result = null;
		int keyIndex = locateIndex(key);
		if ((keyIndex < dictionary.size()) && key.equals((dictionary.get(keyIndex)).getKey())) {
			Entry currentEntry = dictionary.get(keyIndex);
			currentEntry.addValue(value);
			result = currentEntry.getValue();

			// currentEntry.setValue(value);
		} else {
			Entry newEntry = new Entry(key, value);
			dictionary.add(keyIndex, newEntry);
		}
		numberOfEntries++;
		return result;

	}

	private int locateIndex(K key) {
		int numberOfEntries = dictionary.size();
		int index = 0;
		while ((index < numberOfEntries) && key.compareTo((dictionary.get(index)).getKey()) > 0)
			index++;
		return index;
	}

	@Override
	public List<V> remove(K key) {

		List<V> list = null;
		Entry entry = null;

		int keyIndex = locateIndex(key);
		if (keyIndex < numberOfEntries) {
			// return the list of Values

			entry = dictionary.get(keyIndex);
			list = entry.getValue();
			// result=dictionary.set(keyIndex, element)
			// result = dictionary[keyIndex].getValue();
			// dictionary at the index
			dictionary.add(keyIndex, dictionary.get(numberOfEntries - 1));
			// dictionary[keyIndex] = dictionary[numberOfEntries - 1];
			numberOfEntries--;
		}
		return list;
	}

	@Override
	public List<V> getValue(K key) {
		int index;
		Entry entry;
		List<V> list;
		index = locateIndex(key);
		entry = dictionary.get(index);
		list = entry.getValue();
		return list;
	}

	@Override
	public boolean contains(K key) {
		boolean result = false;
		// if(locateIndex(key))
		int keyIndex = locateIndex(key);
		if ((keyIndex > dictionary.size()) && !key.equals((dictionary.get(keyIndex)).getKey()))
			return false;
		return true;
	}

	@Override
	public Iterator<K> getKeyIterator() {

		return new KeyIterator();
	}

	private class KeyIterator implements Iterator<K> {
		private Iterator<Entry> traverser;

		private KeyIterator() {
			traverser = dictionary.iterator();
		} // end default constructor

		public boolean hasNext() {
			return traverser.hasNext();
		} // end hasNext

		public K next() {
			Entry nextEntry = traverser.next();
			return nextEntry.getKey();
		} // end next

		public void remove() {
			throw new UnsupportedOperationException();
		} // end remove
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	private class ValueIterator implements Iterator<V> {
		// private Iterator< Entry< V> entries = entrySet().iterator();
		private Iterator<Entry> traverser;

		public boolean hasNext() {
			return traverser.hasNext();
		}

		public V next() {
			Entry nextEntry = traverser.next();
			// return nextEntry.getValue();
			List<V> list = nextEntry.getValue();
			Iterator<V> list2 = list.iterator();
			return list2.next();
			// Iterator<Entry> iter;
			// return nextEntry.getKey();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public boolean isEmpty() {
		return dictionary.isEmpty();
	}

	@Override
	public int getSize() {
		return dictionary.size();
	}

	@Override
	public void clear() {
		dictionary.clear();
	}

}
