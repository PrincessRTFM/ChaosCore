package princessrtfm.core.reflect;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import princessrtfm.core.util.WrappedResult;


/**
 * Represents a {@link Method} object, complete with invocation target and arguments
 */
public class Wrapper implements Runnable {
	/**
	 * Represents an exception caught while attempting to execute the wrapped method. This exception
	 * indicates that the wrapped method <b>was</b> called, and threw the wrapped exception
	 * (&lt;WrappedMethodException>.{@link #cause}) while executing.
	 */
	public static class WrappedMethodException extends RuntimeException {
		private static final long serialVersionUID = -315023057975758648L;
		/**
		 * The object that the method was invoked on (may be null)
		 */
		public final Object target;
		/**
		 * The method that threw the exception (can never be null)
		 */
		public final Method method;
		/**
		 * The arguments given to the method when it threw the exception (may be empty, but not
		 * null)
		 */
		public final Object[] args;
		/**
		 * The exception that was caught during execution
		 */
		public final Exception cause;
		/**
		 * @param thrown
		 *        The thrown exception
		 * @param targetObject
		 *        The invocation target
		 * @param function
		 *        The method that threw the exception
		 * @param params
		 *        The arguments to the method when it threw the exception
		 */
		public WrappedMethodException(Exception thrown, Object targetObject, Method function, Object... params) {
			if (thrown == null) {
				throw new NullPointerException("Cannot wrap null exception");
			}
			if (function == null) {
				throw new NullPointerException("Wrapped method must not be null");
			}
			cause = thrown;
			target = targetObject;
			method = function;
			args = params;
		}
		/**
		 * @param thrown
		 *        The thrown exception
		 * @param targetObject
		 *        The invocation target
		 * @param function
		 *        The method that threw the exception
		 */
		public WrappedMethodException(Exception thrown, Object targetObject, Method function) {
			this(thrown, targetObject, function, new Object[] {});
		}
		/**
		 * Returns a message describing the caught exception. It will include the exception class,
		 * exception message, wrapped method's declaring class, wrapped method's name, wrapped
		 * method's argument types, and invocation target's class. The invocation target's class may
		 * be the string <code>"null"</code> if the invocation target was <code>null</code>.
		 *
		 * @return A message in the form of <b>
		 *         <tt>Caught {exception_class} [{exception_message}] while executing &lt;{method_declaring_class}&gt;.{method_name}({method_argument_types}) on {invocation_target_class}</tt>
		 *         </b>
		 */
		@Override
		public String getMessage() {
			StringBuffer sb = new StringBuffer();
			StringBuffer params = new StringBuffer();
			for (Object arg : args) {
				params.append(", ");
				params.append(arg.getClass().getCanonicalName());
			}
			sb.append("Caught ");
			sb.append(cause.getClass().getCanonicalName());
			sb.append(" [");
			sb.append(cause.getLocalizedMessage());
			sb.append("] while executing <");
			sb.append(method.getDeclaringClass().getCanonicalName());
			sb.append(">.");
			sb.append(method.getName());
			sb.append("(");
			try {
				sb.append(params.toString().substring(2));
			}
			catch (IndexOutOfBoundsException e) {
				// Nothing to see here, move along.
			}
			sb.append(") on <");
			if (target == null) {
				sb.append("null");
			}
			else {
				sb.append(target.getClass().getCanonicalName());
			}
			sb.append(">");
			return sb.toString();
		}
	}
	/**
	 * Represents an exception caught while attempting to execute the wrapped method. This exception
	 * indicates that the wrapped method was <b>not</b> called.
	 */
	public static class FailedToInvokeException extends RuntimeException {
		private static final long serialVersionUID = 4402563577442318077L;
		/**
		 * The object that the method was invoked on (may be null)
		 */
		public final Object target;
		/**
		 * The method that threw the exception (can never be null)
		 */
		public final Method method;
		/**
		 * The arguments given to the method when it threw the exception (may be empty, but not
		 * null)
		 */
		public final Object[] args;
		/**
		 * The exception that was caught while attempting to execute the wrapped method
		 */
		public final Throwable cause;
		/**
		 * @param thrown
		 *        The thrown exception
		 * @param targetObject
		 *        The invocation target
		 * @param function
		 *        The method that was to be executed
		 * @param params
		 *        The arguments that would have been passed to the method
		 */
		public FailedToInvokeException(Throwable thrown, Object targetObject, Method function, Object... params) {
			if (thrown == null) {
				throw new NullPointerException("Cannot wrap null exception");
			}
			if (function == null) {
				throw new NullPointerException("Wrapped method must not be null");
			}
			cause = thrown;
			target = targetObject;
			method = function;
			args = params;
		}
		/**
		 * @param thrown
		 *        The thrown exception
		 * @param targetObject
		 *        The invocation target
		 * @param function
		 *        The method that was to be executed
		 */
		public FailedToInvokeException(Throwable thrown, Object targetObject, Method function) {
			this(thrown, targetObject, function, new Object[] {});
		}
		/**
		 * Returns a message describing the caught exception. It will include the exception class,
		 * exception message, wrapped method's declaring class, wrapped method's name, wrapped
		 * method's argument types, and invocation target's class. The invocation target's class may
		 * be the string <code>"null"</code> if the invocation target was <code>null</code>.
		 *
		 * @return A message in the form of <b>
		 *         <tt>Failed to call &lt;{method_declaring_class}&gt;.{method_name}({method_argument_types}) on &lt;{invocation_target_class}&gt; due to {caught_exception_class} [{caught_exception_message}]</tt>
		 *         </b>
		 */
		@Override
		public String getMessage() {
			StringBuffer sb = new StringBuffer();
			StringBuffer params = new StringBuffer();
			for (Object arg : args) {
				params.append(", ");
				params.append(arg.getClass().getCanonicalName());
			}
			sb.append("Failed to call <");
			sb.append(method.getDeclaringClass().getCanonicalName());
			sb.append(">.");
			sb.append(method.getName());
			sb.append("(");
			try {
				sb.append(params.toString().substring(2));
			}
			catch (IndexOutOfBoundsException e) {
				// Nothing to see here, move along.
			}
			sb.append(") on <");
			if (target == null) {
				sb.append("null");
			}
			else {
				sb.append(target.getClass().getCanonicalName());
			}
			sb.append("> due to ");
			sb.append(cause.getClass().getCanonicalName());
			sb.append(" [");
			sb.append(cause.getLocalizedMessage());
			sb.append("]");
			return sb.toString();
		}
	}
	/**
	 * The wrapped method
	 */
	public final Method method;
	/**
	 * The invocation target for the wrapped method
	 */
	public final Object target;
	/**
	 * The arguments to pass to the wrapped method (may be empty, never null)
	 */
	// This is protected not public because we validate the arguments when they're set.
	// We need to make sure they match what the method expects.
	protected Object[] args;
	private WrappedResult retVal;
	/**
	 * @param targetObject
	 *        The invocation target
	 * @param function
	 *        The {@link Method} object
	 */
	public Wrapper(Object targetObject, Method function) {
		target = targetObject;
		method = function;
		args = new Object[] {};
	}
	/**
	 * @param targetObject
	 *        The invocation target
	 * @param function
	 *        The {@link Method} object
	 * @param params
	 *        The arguments to the method
	 */
	public Wrapper(Object targetObject, Method function, Object... params) {
		this(targetObject, function);
		setArgs(params);
	}
	/**
	 * Execute the wrapped method on the stored invocation target.<br/>
	 * <br/>
	 * If the method cannot be called, the exception indicating why will be saved. If the method
	 * throws an exception during execution, it will be saved. If the method is successfully called,
	 * AND it completes normally, the return value will be saved. Only the most recent status will
	 * be stored, each execution overwrites the last status.
	 *
	 * @see #getReturnValue()
	 */
	@Override
	public synchronized void run() {
		try {
			retVal = WrappedResult.wrapSuccess(method.invoke(target, args));
		}
		// IllegalAccessException, IllegalArgumentException, and InvocationTargetException are
		// from failure to invoke method.
		// Anything else is from the method itself.
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			StackTraceElement f = e.getStackTrace()[0];
			if ( (f.getClassName() + "." + f.getMethodName()).startsWith(this.getClass().getCanonicalName() + ".run")) {
				retVal = WrappedResult.wrapError(new WrappedMethodException(e, target, method, args));
			}
			else {
				retVal = WrappedResult.wrapError(new FailedToInvokeException(e, target, method, args));
			}
		}
		catch (Exception e) {
			retVal = WrappedResult.wrapError(new WrappedMethodException(e, target, method, args));
		}
	}
	/**
	 * @return The array of arguments to pass to the method
	 */
	public synchronized Object[] getArgs() {
		return args;
	}
	/**
	 * Sets the arguments to pass to the method. Must be an array, though it may be empty.
	 *
	 * @param params
	 *        The method arguments
	 * @return <code>true</code> if successful, <code>false</code> otherwise.
	 */
	public synchronized boolean setArgs(Object[] params) {
		if (params == null) {
			return false;
		}
		args = params;
		return true;
	}
	/**
	 * Get the status from the last call to {@link #run()}. Will be <code>null</code> if this
	 * <tt>Wrapper</tt> has never been run.<br/>
	 * <br/>
	 * If the method completed normally, the return value will be saved. If it failed to invoke, the
	 * wrapped object will be a {@link FailedToInvokeException}. If the method was successfully
	 * invoked, but threw an exception during execution, the wrapped object will be a
	 * {@link WrappedMethodException}.
	 *
	 * @return The saved status from the last run
	 * @see WrappedResult
	 */
	public synchronized WrappedResult getReturnValue() {
		return retVal;
	}
	/**
	 * Check whether the last call was successful
	 *
	 * @return <code>true</code> <b>iff</b> this method has been called (see {@link #run()}) and it
	 *         executed normally
	 */
	public synchronized boolean success() {
		return (retVal != null) && !retVal.isError;
	}
}
