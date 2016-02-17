package princessrtfm.core.util;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;


/**
 * Java {@link Properties} with additional utilities
 *
 * @since 1.0.0-alpha.1
 */
public class ExtProperties extends Properties {
	private static final String SEP = System.getProperty("line.separator", "\n");
	private static final long serialVersionUID = -7491998209124094139L;
	/**
	 * @return an array of all keys in the properties list, in ascending natural-sorted order
	 */
	public String[] keyArray() {
		String[] k = this.keySet().toArray(new String[] {});
		Arrays.sort(k);
		return k;
	}
	/**
	 * Calls {@link #store(Writer, String)} after wrapping the OutputStream in an
	 * {@link OutputStreamWriter}
	 *
	 * @param out
	 *        - the OutputStream to write to
	 * @param comments
	 *        - the comment to add to the OutputStream (may be <code>null</code>)
	 * @see #store(Writer, String)
	 */
	@Override
	public void store(OutputStream out, String comments) throws IOException {
		store(new OutputStreamWriter(out), comments);
	}
	/**
	 * Writes this object's property list (NOT including the default properties) to the given
	 * Writer. Properties are sorted ascending according to natural-ordering. If comments are
	 * provided, writes them first. The comment string is split on line separators (\r, \r\n, \n)
	 * into individual lines. Each comment line will be prefixed with a hash character ("#", ASCII
	 * 35) and followed by a platform line break.
	 *
	 * @param writer
	 *        - the Writer to write to
	 * @param comments
	 *        - the comment to add to the Writer (may be <code>null</code>)
	 */
	@Override
	public void store(Writer writer, String comments) throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		if (comments == null) {
			comments = "";
		}
		for (String line : comments.split("(?:\r\n?|\n)")) {
			if (!line.startsWith("#") && !line.startsWith("!")) {
				out.append("#");
			}
			out.append(line);
			out.newLine();
		}
		out.append("#" + new Date().toString());
		out.newLine();
		String[] keys = this.keyArray();
		for (final String key : keys) {
			final String val = this.getProperty(key, "");
			if (key.startsWith("#")) {
				out.append("\\");
			}
			out.append(key.replaceAll(" ", "\\ ") + "=" + (val.startsWith(" ") ? val.replaceFirst(" ", "\\ ") : val));
			out.newLine();
		}
		out.flush();
	}
}
