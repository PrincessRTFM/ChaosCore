package princessrtfm.core.struct;


import java.util.List;


/**
 * A {@link List} that supports retrieval of a random element
 *
 * @since 1.0.0-alpha.1
 */
@SuppressWarnings("javadoc")
public interface RandomAccessList<T> extends List<T> {
	/**
	 * Get a random element from the list
	 *
	 * @return a randomly selected element
	 */
	public T get();
}
