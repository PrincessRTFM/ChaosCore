package princessrtfm.core.thread;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Passes text from an {@link InputStream} to an {@link OutputStream}
 *
 * @since 1.0.0-alpha.1
 */
public class StreamPassThread extends Thread {
	private final InputStream from;
	private final OutputStream to;
	@SuppressWarnings("javadoc")
	public StreamPassThread(InputStream is) {
		this(is, System.out);
	}
	@SuppressWarnings("javadoc")
	public StreamPassThread(InputStream is, OutputStream os) {
		super();
		from = is;
		to = os;
	}
	@SuppressWarnings("javadoc")
	@Override
	public void run() {
		int read = 0;
		do {
			try {
				read = from.read();
				to.write(read);
			}
			catch (IOException e) {
				// Nothing to see here, move along.
			}
		}
		while (read >= 0);
	}
}
