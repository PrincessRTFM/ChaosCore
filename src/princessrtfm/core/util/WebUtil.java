package princessrtfm.core.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


/**
 * General internet utilities
 *
 * @since 1.0.0-alpha.1
 */
public abstract class WebUtil {
	/**
	 * Retrieves a file from a web server and writes it to disk. Supports binary files.
	 *
	 * @param source
	 *        the {@link URL} to download
	 * @param dest
	 *        the {@link File} to write to
	 * @throws IOException
	 *         if the file cannot be downloaded/written
	 */
	public static void downloadToFile(URL source, File dest) throws IOException {
		final InputStream input = source.openStream();
		byte[] buffer = new byte[4096];
		int n = -1;
		final OutputStream output = new FileOutputStream(dest);
		while ( (n = input.read(buffer)) != -1) {
			output.write(buffer, 0, n);
		}
		input.close();
		output.close();
	}
}
