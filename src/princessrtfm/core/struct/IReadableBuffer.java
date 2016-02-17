package princessrtfm.core.struct;


/**
 * Indicates that an object has an internal buffer that can be read from
 *
 * @param <T>
 *        the type of the internal buffer
 * @since 1.0.0-alpha.1
 */
public interface IReadableBuffer<T> {
	/**
	 * Check whether the internal buffer has any data to be read
	 *
	 * @return whether the buffer can be read
	 */
	public boolean canRead();
	/**
	 * Read from the internal buffer
	 *
	 * @return the next object from the buffer
	 */
	public T read();
}
