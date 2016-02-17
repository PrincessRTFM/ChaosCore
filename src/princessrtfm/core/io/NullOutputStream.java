package princessrtfm.core.io;


import java.io.IOException;
import java.io.OutputStream;


/**
 * Tosses all data sent it's way into the bit bucket
 *
 * @since 1.0.0-alpha.1
 */
public class NullOutputStream extends OutputStream {
	/**
	 * Discards whatever you give it.<br/>
	 * <br/>
	 * Om nom nom.
	 */
	@Override
	public void write(int arg0) throws IOException {}
}
