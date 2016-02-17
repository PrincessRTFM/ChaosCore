package princessrtfm.core.struct;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


/**
 * {@link RandomAccessList} implemented with an array
 *
 * @since 1.0.0-alpha.1
 */
@SuppressWarnings("javadoc")
public class RandomAccessArrayList<T> implements RandomAccessList<T> {
	protected Random rand;
	protected final List<T> items;
	// Constructors
	/**
	 * Construct a RandomAccessList with the given random number generator and list. Does not
	 * duplicate the list.
	 *
	 * @param init
	 *        - the list to wrap
	 * @param r
	 *        - the {@link Random} object to use as an RNG
	 */
	public RandomAccessArrayList(List<T> init, Random r) {
		items = init;
		rand = r;
	}
	/**
	 * @param init
	 *        - the list to wrap
	 * @param seed
	 *        - the seed for the RNG
	 */
	public RandomAccessArrayList(List<T> init, long seed) {
		this(init, new Random(seed));
	}
	/**
	 * @param init
	 *        - the list to wrap
	 */
	public RandomAccessArrayList(List<T> init) {
		this(init, new Random());
	}
	/**
	 * @param r
	 *        - the RNG to use
	 */
	public RandomAccessArrayList(Random r) {
		this(new ArrayList<T>(), r);
	}
	/**
	 * @param seed
	 *        - the seed for the RNG
	 */
	public RandomAccessArrayList(long seed) {
		this(new ArrayList<T>(), new Random(seed));
	}
	/**
	 * Wraps an empty list and constructs a new RNG
	 */
	public RandomAccessArrayList() {
		this(new ArrayList<T>(), new Random());
	}
	// Implemented RandomAccessList<T> methods
	public T get() {
		return items.get(rand.nextInt(items.size()));
	}
	// Implemented List<T> methods
	public boolean add(T item) {
		return items.add(item);
	}
	public void add(int index, T item) {
		items.add(index, item);
	}
	public boolean addAll(Collection<? extends T> list) {
		return items.addAll(list);
	}
	public boolean addAll(int index, Collection<? extends T> list) {
		return items.addAll(index, list);
	}
	public void clear() {
		items.clear();
	}
	public boolean contains(Object test) {
		return items.contains(test);
	}
	public boolean containsAll(Collection<?> test) {
		return items.containsAll(test);
	}
	public T get(int index) {
		return items.get(index);
	}
	public int indexOf(Object search) {
		return items.indexOf(search);
	}
	public boolean isEmpty() {
		return items.isEmpty();
	}
	public Iterator<T> iterator() {
		return items.iterator();
	}
	public int lastIndexOf(Object search) {
		return items.lastIndexOf(search);
	}
	public ListIterator<T> listIterator() {
		return items.listIterator();
	}
	public ListIterator<T> listIterator(int index) {
		return items.listIterator(index);
	}
	public boolean remove(Object item) {
		return items.remove(item);
	}
	public T remove(int index) {
		return items.remove(index);
	}
	public boolean removeAll(Collection<?> list) {
		return items.removeAll(list);
	}
	public boolean retainAll(Collection<?> list) {
		return items.retainAll(list);
	}
	public T set(int index, T item) {
		return items.set(index, item);
	}
	public int size() {
		return items.size();
	}
	public List<T> subList(int start, int end) {
		return items.subList(start, end);
	}
	public Object[] toArray() {
		return items.toArray();
	}
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] type) {
		return items.toArray(type);
	}
}
