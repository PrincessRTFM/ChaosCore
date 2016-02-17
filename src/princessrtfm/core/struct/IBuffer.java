package princessrtfm.core.struct;


/**
 * Indicates that an object has an internal buffer that can be read from and written to
 *
 * @param <T>
 *        the type of the buffer
 * @see IWritableBuffer
 * @see IReadableBuffer
 * @since 1.0.0-alpha.1
 */
public interface IBuffer<T> extends IReadableBuffer<T>, IWritableBuffer<T> {}
