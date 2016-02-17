package princessrtfm.core.util;


import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;

import princessrtfm.core.logger.MagicFormatter;
import princessrtfm.core.logger.MagicFormatter.FormatCode;
import princessrtfm.core.logger.Level;
import princessrtfm.core.trace.StackTrace;


/**
 * A class intended to make it easier to log certain things, like stack traces.
 *
 * @since 1.0.0-alpha.1
 */
public class UtilityLogger {
	/**
	 * The logger used by this object when no other logger is provided
	 *
	 * @see #DEFAULT_HANDLER
	 * @see #DEFAULT_FORMATTER
	 */
	public static final Logger DEFAULT_LOGGER = Logger.getLogger("UtilityLogger");
	/**
	 * The default handler uses a {@link MagicFormatter}, and this is the default format code for
	 * those who want to copy it
	 *
	 * @see FormatCode
	 */
	public static final String DEFAULT_FORMAT_CODE = "[&" + FormatCode.LOGGER_NAME + "&/&" + FormatCode.LEVEL_NAME + "&] [Thread&" + FormatCode.SOURCE_THREAD_ID + "&/&" + FormatCode.SOURCE_THREAD_NAME + "&] [&" + FormatCode.ISO_STAMP + "&] &" + FormatCode.LOG_MESSAGE + "&";
	/**
	 * The {@link MagicFormatter} used by the default handler
	 *
	 * @see #DEFAULT_FORMAT_CODE
	 */
	public static final Formatter DEFAULT_FORMATTER = new MagicFormatter(DEFAULT_FORMAT_CODE, "&", false);
	/**
	 * The handler used by the default logging object
	 *
	 * @see #DEFAULT_FORMATTER
	 */
	public static final Handler DEFAULT_HANDLER = new ConsoleHandler();
	static {
		DEFAULT_HANDLER.setFormatter(DEFAULT_FORMATTER);
		DEFAULT_HANDLER.setLevel(Level.ALL);
		DEFAULT_LOGGER.addHandler(DEFAULT_HANDLER);
		DEFAULT_LOGGER.setUseParentHandlers(false);
		DEFAULT_LOGGER.setLevel(Level.ALL);
	}
	/**
	 * The target of all logging performed by this object
	 */
	public Logger LOG;
	/**
	 * Use the default {@link Logger} to log all messages from this object
	 *
	 * @see #DEFAULT_LOGGER
	 * @see #DEFAULT_HANDLER
	 * @see #DEFAULT_FORMATTER
	 */
	public UtilityLogger() {
		LOG = DEFAULT_LOGGER;
	}
	/**
	 * Use the given {@link Logger} to log all messages from this object
	 *
	 */
	public UtilityLogger(@SuppressWarnings("javadoc") Logger target) {
		LOG = target;
	}
	/**
	 * Log the name of the current method, with class name
	 *
	 * @see StackTrace#parentClass()
	 * @see StackTrace#parentMethodName()
	 */
	public void trace() {
		if (LOG == null)
			return;
		// If I had a reason to, it would be POSSIBLE (probably) to reflect the caller, and get more
		// information about them. I might be able to get the number of arguments, the types of
		// each, the return type...
		// Eh, I'll put it in another class. Maybe use it here, maybe not.
		String full = StackTrace.parentClass() + "." + StackTrace.parentMethodName() + "()";
		LOG.log(Level.TRACE, full);
	}
	/**
	 * Dump the stack trace leading up to the current method
	 *
	 * @see StackTrace
	 */
	public void traceLong() {
		if (LOG == null)
			return;
		// The one is so we skip the creation (the call from traceLong() to the constructor) but not
		// ourself (the call from whoever invoked us to traceLong())
		// Stack traces are hard to talk about :(
		StackTrace trace = new StackTrace(1);
		trace.dump(LOG);
	}
	/**
	 * Log a FINEST message
	 *
	 * @param msg
	 *        the message to log
	 */
	// TRIVIA: I almost named this method paranoid after an old IRC client I used to use. It allowed
	// you to set the level of messages it dumped to various levels, and the one to include
	// everything was called Paranoid.
	public void whisper(Object msg) {
		if (LOG == null)
			return;
		LOG.finest(String.valueOf(msg));
	}
	/**
	 * Log a FINER message
	 *
	 * @param msg
	 *        the message to log
	 */
	public void mutter(Object msg) {
		if (LOG == null)
			return;
		LOG.finer(String.valueOf(msg));
	}
	/**
	 * Log a FINE message
	 *
	 * @param msg
	 *        the message to log
	 */
	public void verbose(Object msg) {
		if (LOG == null)
			return;
		LOG.fine(String.valueOf(msg));
	}
	/**
	 * Log an INFO message
	 *
	 * @param msg
	 *        The message to log
	 */
	public void echo(Object msg) {
		if (LOG == null)
			return;
		LOG.info(String.valueOf(msg));
	}
	/**
	 * Log a WARNING message
	 *
	 * @param msg
	 *        The message to log
	 */
	public void warn(Object msg) {
		if (LOG == null)
			return;
		LOG.warning(String.valueOf(msg));
	}
	/**
	 * Log a SEVERE message
	 *
	 * @param msg
	 *        The message to log
	 */
	public void problem(Object msg) {
		if (LOG == null)
			return;
		LOG.severe(String.valueOf(msg));
	}
	/**
	 * Log a FATAL message
	 *
	 * @param msg
	 *        the message to log
	 * @see princessrtfm.core.logger.Level#FATAL
	 */
	public void die(Object msg) {
		if (LOG == null)
			return;
		LOG.log(Level.FATAL, String.valueOf(msg));
	}
	/**
	 * Log a thrown exception as a WARNING, with a short message about the problem
	 *
	 * @param mini
	 *        a short message to log with the exception
	 * @param t
	 *        the exception to log
	 */
	public void thrown(String mini, Throwable t) {
		if (LOG == null)
			return;
		LOG.warning(mini + " [" + t + "]");
	}
	/**
	 * Log a thrown exception as SEVERE, with a short message about the problem
	 *
	 * @param mini
	 *        a short message to log with the exception
	 * @param t
	 *        the exception to log
	 */
	public void exception(String mini, Throwable t) {
		if (LOG == null)
			return;
		LOG.severe(mini + " [" + t + "]");
	}
	/**
	 * Log a thrown exception as FATAL, with a short message about the problem
	 *
	 * @param mini
	 *        a short message to log with the exception
	 * @param t
	 *        the exception to log
	 * @see princessrtfm.core.logger.Level#FATAL
	 */
	public void error(String mini, Throwable t) {
		if (LOG == null)
			return;
		LOG.log(Level.FATAL, mini, t);
	}
}
