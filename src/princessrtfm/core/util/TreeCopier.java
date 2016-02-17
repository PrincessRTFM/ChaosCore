package princessrtfm.core.util;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * {@link FileVisitor} that recursively copies a directory tree
 *
 * @since 1.0.0-alpha.1
 */
// This whole thing is one big hack. I needed to do something, and this was the result.
public class TreeCopier extends SimpleFileVisitor<Path> implements Runnable {
	private final Path dest;
	private final Path src;
	@SuppressWarnings("javadoc")
	public TreeCopier(Path source, Path destination) {
		super();
		src = source.normalize();
		dest = destination.normalize();
	}
	/**
	 * Start copying the source tree to the destination
	 */
	@Override
	public void run() {
		try {
			Files.walkFileTree(src, this);
		}
		catch (IOException e) {
			// Nothing to see here, move along.
		}
	}
	protected String getSrc() {
		// The magnitude of this hack compares favorably with that of the national debt.
		String source = src.toString().replaceAll(Pattern.quote(File.separator), "/");
		if (!source.endsWith("/")) {
			source = source + "/";
		}
		return source.substring(0, source.length()).replaceAll("/", Matcher.quoteReplacement(File.separator));
	}
	protected String getDest() {
		// I literally do not care.
		String target = dest.toString().replaceAll(Pattern.quote(File.separator), "/");
		if (!target.endsWith("/")) {
			target = target + "/";
		}
		return target.substring(0, target.length()).replaceAll("/", Matcher.quoteReplacement(File.separator));
	}
	@SuppressWarnings("javadoc")
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		// *Sigh* more hacks...
		Path target = new File(dir.toString().replaceFirst(Pattern.quote(getSrc()), Matcher.quoteReplacement(getDest()))).toPath();
		Files.createDirectories(target);
		return FileVisitResult.CONTINUE;
	}
	@SuppressWarnings("javadoc")
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		Path target = new File(file.toString().replaceFirst(Pattern.quote(getSrc()), Matcher.quoteReplacement(getDest()))).toPath();
		Files.copy(file, target, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
		return FileVisitResult.CONTINUE;
	}
}
