package princessrtfm.core.trace;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Logger;

import princessrtfm.core.logger.Level;


/**
 * Utilities for creating and representing a stack trace
 *
 * @since 1.0.0-alpha.1
 */
public class StackTrace {
	/**
	 * Generate a stack trace from the perspective of the caller
	 *
	 * @return array of {@link StackTraceElement}
	 */
	public static StackTraceElement[] getTrace() {
		StackTraceElement[] trace = new Exception().getStackTrace();
		StackTraceElement[] ret = new StackTraceElement[trace.length - 1];
		System.arraycopy(trace, 1, ret, 0, ret.length);
		return ret;
	}
	/**
	 * Get a stack trace frame about the caller, which is to say, the method which contains the call
	 * to this method.
	 *
	 * @return a {@link StackTraceElement}
	 */
	public static StackTraceElement self() {
		return getTrace()[1];
	}
	/**
	 * Get a stack trace frame about the caller's parent, which is to say, the method calling the
	 * one which calls this one.
	 *
	 * I'm sorry, it's kind of hard to explain this sort of thing.
	 *
	 * @return a {@link StackTraceElement}
	 */
	public static StackTraceElement parent() {
		return getTrace()[2];
	}
	/**
	 * Get the class of the caller, which is to say, the method which contains the call to this
	 * method.
	 *
	 * @return the caller's fully qualified class name
	 */
	public static String selfClass() {
		return getTrace()[1].getClassName();
	}
	/**
	 * Get the name of the calling method, which is to say, the method which contains the call to
	 * this method.
	 *
	 * @return the caller's method name
	 */
	public static String selfMethodName() {
		return getTrace()[1].getMethodName();
	}
	/**
	 * Get the class of the calling method's caller, which is to say, the method calling the one
	 * which calls this one.
	 *
	 * @see #parent()
	 * @return the caller's parent's fully qualified class name
	 */
	public static String parentClass() {
		return getTrace()[2].getClassName();
	}
	/**
	 * Get the name of the calling method's caller, which is to say, the method calling the one
	 * which calls this one.
	 *
	 * @see #parent()
	 * @return the caller's parent's method name
	 */
	public static String parentMethodName() {
		return getTrace()[2].getMethodName();
	}
	/**
	 * Get a string describing the trace point of the calling method, which is the method calling
	 * tracePoint()
	 *
	 * @return &lt;class>.&lt;method>
	 */
	public static final String tracePoint() {
		return getTrace()[1].getClassName() + "." + getTrace()[1].getMethodName();
	}
	/**
	 * Get a string describing the trace point of the parent method, which is the method calling the
	 * one which calls parentTracePoint()
	 *
	 * @return &lt;class>.&lt;method>
	 */
	public static final String parentTracePoint() {
		return getTrace()[2].getClassName() + "." + getTrace()[2].getMethodName();
	}
	protected final StackTraceElement[] trace;
	/**
	 * Construct a new StackTrace, starting at the caller's PoV. The StackTrace constructor will not
	 * be in the trace. The method which creates this object will be in the trace.
	 *
	 * @since 2.0.0-rc.3
	 */
	public StackTrace() {
		// It's a one so we ignore this constructor call.
		// That makes it act like the caller went straight to <new StackTrace(0)> instead.
		this(1);
	}
	/**
	 * Construct a new StackTrace from the caller's PoV, skipping the given number of frames. Pass
	 * <tt>-1</tt> to include the StackTrace constructor. Pass <tt>0</tt> to include the method
	 * creating this object. Pass <tt>1</tt> to skip the method creating this object and start at
	 * that method's caller. Ad infinitum.
	 *
	 * @param skipFrames
	 *        the number of frames to skip (from the caller's perspective)
	 */
	public StackTrace(int skipFrames) {
		// If you pass -1, you get the frame referencing our constructor.
		// If you pass less, you get an exception.
		if (skipFrames < -1) {
			skipFrames = -1;
		}
		StackTraceElement[] stack = new Throwable().getStackTrace();
		// The +1 is to automatically skip our constructor
		int len = stack.length - (skipFrames + 1);
		if (len < 0) {
			len = 0; // Prevents negative array copy length exceptions in System.arraycopy
		}
		// If we're skipping more frames than are in the trace, take a shortcut.
		if (skipFrames >= stack.length) {
			trace = new StackTraceElement[0];
			return;
		}
		// Save the modified trace
		trace = new StackTraceElement[len];
		System.arraycopy(stack, skipFrames + 1, trace, 0, len);
	}
	/**
	 * Dump the stack trace to STDERR, one frame per line
	 *
	 */
	public void dump() {
		dump(System.err);
	}
	/**
	 * Dump the stack trace to the given {@link OutputStream}, one frame per line
	 *
	 * @param out
	 *        the OutputStream to print the stack trace to
	 */
	public void dump(OutputStream out) {
		dump(new OutputStreamWriter(out));
	}
	/**
	 * Dump the stack trace to the given {@link Writer}, one frame per line
	 *
	 * @param out
	 *        the Writer to print the stack trace to
	 */
	public void dump(Writer out) {
		if (out == null) {
			return;
		}
		BufferedWriter writer = new BufferedWriter(out);
		for (StackTraceElement frame : trace) {
			try {
				writer.write(frame.toString());
				writer.newLine();
				writer.flush();
			}
			catch (IOException e) {
				// Nothing to see here, move along.
			}
		}
	}
	/**
	 * Dump the stack trace to the given {@link Logger}, one frame per record, using
	 * {@link princessrtfm.core.logger.Level#TRACE} as the logging level.
	 *
	 * @param out
	 *        the Logger to print the stack trace to
	 */
	public void dump(Logger out) {
		if (out == null) {
			return;
		}
		for (StackTraceElement frame : trace) {
			out.log(Level.TRACE, frame.toString());
		}
	}
}
