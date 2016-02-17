package princessrtfm.core.thread;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


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
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			while (br.readLine() != null) {}
		}
		catch (Exception e) {
			// Nothing to see here, move along.
		}
	}
}
