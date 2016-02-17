package princessrtfm.core.io;


import java.io.IOException;
import java.io.Writer;


/**
 * Tosses all data sent it's way into the bit bucket
 *
 * @since 1.0.0-alpha.1
 */
public class NullWriter extends Writer {
	/**
	 * Intentional NOP
	 */
	@Override
	public void close() throws IOException {}
	/**
	 * Intentional NOP
	 */
	@Override
	public void flush() throws IOException {}
	/**
	 * Intentional NOP
	 */
	@Override
	public void write(char[] arg0, int arg1, int arg2) throws IOException {}
}
