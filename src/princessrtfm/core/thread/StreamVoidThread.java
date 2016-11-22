package princessrtfm.core.thread;


import java.io.InputStream;


/**
 * Reads text from an {@link InputStream} and ignores it
 *
 * @since 1.0.0-alpha.1
 */
// Because sometimes, you just don't care.
public class StreamVoidThread extends Thread {
	private final InputStream is;
	@SuppressWarnings("javadoc")
	public StreamVoidThread(InputStream stream) {
		super();
		is = stream;
	}
	@SuppressWarnings("javadoc")
	@Override
	public void run() {
		try {
			while (is.read() >= 0) {}
		}
		catch (Exception e) {
			// Nothing to see here, move along.
		}
	}
}
