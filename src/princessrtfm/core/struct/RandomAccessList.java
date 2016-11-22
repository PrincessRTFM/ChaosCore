package princessrtfm.core.struct;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


/**
 * A simple {@link IRandomAccessList} implementation
 *
 * @since 1.0.0-rc.3
 */
@SuppressWarnings("javadoc")
public class RandomAccessList<T> implements IRandomAccessList<T> {
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
	public RandomAccessList(List<T> init, Random r) {
		items = init;
		rand = r;
	}
	/**
	 * @param init
	 *        - the list to wrap
	 * @param seed
	 *        - the seed for the RNG
	 */
	public RandomAccessList(List<T> init, long seed) {
		this(init, new Random(seed));
	}
	/**
	 * @param init
	 *        - the list to wrap
	 */
	public RandomAccessList(List<T> init) {
		this(init, new Random());
	}
	/**
	 * @param r
	 *        - the RNG to use
	 */
	public RandomAccessList(Random r) {
		this(new ArrayList<T>(), r);
	}
	/**
	 * @param seed
	 *        - the seed for the RNG
	 */
	public RandomAccessList(long seed) {
		this(new ArrayList<T>(), new Random(seed));
	}
	/**
	 * Wraps an empty list and constructs a new RNG
	 */
	public RandomAccessList() {
		this(new ArrayList<T>(), new Random());
	}
	// Implemented RandomAccessList<T> methods
	@Override
	public T get() {
		return items.get(rand.nextInt(items.size()));
	}
	// Implemented List<T> methods
	@Override
	public boolean add(T item) {
		return items.add(item);
	}
	@Override
	public void add(int index, T item) {
		items.add(index, item);
	}
	@Override
	public boolean addAll(Collection<? extends T> list) {
		return items.addAll(list);
	}
	@Override
	public boolean addAll(int index, Collection<? extends T> list) {
		return items.addAll(index, list);
	}
	@Override
	public void clear() {
		items.clear();
	}
	@Override
	public boolean contains(Object test) {
		return items.contains(test);
	}
	@Override
	public boolean containsAll(Collection<?> test) {
		return items.containsAll(test);
	}
	@Override
	public T get(int index) {
		return items.get(index);
	}
	@Override
	public int indexOf(Object search) {
		return items.indexOf(search);
	}
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}
	@Override
	public Iterator<T> iterator() {
		return items.iterator();
	}
	@Override
	public int lastIndexOf(Object search) {
		return items.lastIndexOf(search);
	}
	@Override
	public ListIterator<T> listIterator() {
		return items.listIterator();
	}
	@Override
	public ListIterator<T> listIterator(int index) {
		return items.listIterator(index);
	}
	@Override
	public boolean remove(Object item) {
		return items.remove(item);
	}
	@Override
	public T remove(int index) {
		return items.remove(index);
	}
	@Override
	public boolean removeAll(Collection<?> list) {
		return items.removeAll(list);
	}
	@Override
	public boolean retainAll(Collection<?> list) {
		return items.retainAll(list);
	}
	@Override
	public T set(int index, T item) {
		return items.set(index, item);
	}
	@Override
	public int size() {
		return items.size();
	}
	@Override
	public List<T> subList(int start, int end) {
		return items.subList(start, end);
	}
	@Override
	public Object[] toArray() {
		return items.toArray();
	}
	@Override
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] type) {
		return items.toArray(type);
	}
}
