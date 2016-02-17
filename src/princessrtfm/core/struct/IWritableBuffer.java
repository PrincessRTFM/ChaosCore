package princessrtfm.core.struct;


/**
 * Indicates that object has an internal buffer that can written to
 *
 * @param <T>
 *        the type of the internal buffer
 * @since 1.0.0-alpha.1
 */
public interface IWritableBuffer<T> {
	/**
	 * Check whether the buffer can be written to
	 *
	 * @return <code>true</code> if there's space in the buffer to write, <code>false</code>
	 *         otherwise
	 */
	public boolean canWrite();
	/**
	 * Add an object to the internal buffer
	 *
	 * @param data
	 *        The object to write to the buffer
	 */
	public void write(T data);
}
