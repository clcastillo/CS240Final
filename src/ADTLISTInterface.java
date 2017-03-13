import java.util.Iterator;

/**
 * Interface describing options of a ADT List
 * 
 * @author Carla Castillo
 *
 * @param <T>
 */

public interface ADTLISTInterface<T> {
	/**
	 * Add new item at the bottom
	 * 
	 * @param item
	 * @return boolean
	 */
	public boolean add(T item);

	/**
	 * Add new item in a specific location
	 * 
	 * @param item
	 * @param location
	 * @return boolean
	 */
	public boolean add(T item, int location);

	/**
	 * Remove from existence
	 * 
	 * @param location
	 *            Where in the list it is located
	 * @return boolean
	 */
	public boolean removeItem(int location);

	/**
	 * Remove All Items
	 * 
	 * @return boolean
	 */
	public boolean remove();

	/**
	 * Copy item in list over another
	 * 
	 * @param location
	 * @return boolean
	 */
	public boolean replace(int location, T item);

	/**
	 * Look for a specific item
	 * 
	 * @param item
	 * @return T
	 */
	public T look(int item);

	/**
	 * Look at a copy of list
	 * 
	 * @return T[]
	 */
	public T[] lookAll();

	/**
	 * Looks to see if the item is inside
	 * 
	 * @return boolean
	 */

	public boolean checkforItem(T item);

	/**
	 * Number of items on list
	 * 
	 * @return integer
	 */
	public int getCount();

	/**
	 * Looks to see if the list is empty
	 * 
	 * @return boolean
	 */
	public boolean empty();

	Iterator<T> iterator();

}
