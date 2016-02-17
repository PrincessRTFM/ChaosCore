package princessrtfm.core.logger;


/**
 * @see java.util.logging.Level
 * @since 1.0.0-alpha.1
 */
public class Level extends java.util.logging.Level {
	/**
	 * Logging level for tracing execution path
	 */
	public static final Level TRACE = new Level("TRACE", 600);
	/**
	 * Logging level for a fatal, stop-everything-no-recovery error
	 */
	public static final Level FATAL = new Level("FATAL", Integer.MAX_VALUE);
	private static final long serialVersionUID = -8748561714139060537L;
	protected Level(String name, int value, String resourceBundleName) {
		super(name, value, resourceBundleName);
	}
	protected Level(String name, int value) {
		super(name, value);
	}
}
