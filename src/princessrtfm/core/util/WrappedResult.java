package princessrtfm.core.util;


/**
 * Class representing the result of a method call with may fail with an exception, or succeed and
 * return some object.
 *
 */
public final class WrappedResult {
	/**
	 * The result of the method call. May be null, depending on the return value of the method
	 * called. If {@link #isError} is <code>true</code>, this is likely an {@link Exception} object.
	 */
	public final Object result;
	/**
	 * If <code>true</code>, this object represents an error. The {@link #result} field is likely an
	 * {@link Exception} object.
	 */
	public final boolean isError;
	/**
	 * Wrap the given object. The second argument indicates whether it is the result of an error.
	 *
	 * @param obj
	 *        The object to wrap
	 * @param err
	 *        Flag indicating whether this result is an error
	 */
	public WrappedResult(Object obj, boolean err) {
		result = obj;
		isError = err;
	}
	/**
	 * Wrap an error for returning
	 *
	 * @param obj
	 *        the object, probably an {@link Exception}, to be wrapped
	 * @return a new WrappedResult object representing the given object as an error
	 */
	public static final WrappedResult wrapError(Object obj) {
		return new WrappedResult(obj, true);
	}
	/**
	 * Wrap success for returning
	 *
	 * @param obj
	 *        the object to be wrapped
	 * @return a new WrappedResult object representing the given object as a successful return value
	 */
	public static final WrappedResult wrapSuccess(Object obj) {
		return new WrappedResult(obj, false);
	}
}
