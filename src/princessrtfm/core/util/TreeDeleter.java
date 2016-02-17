package princessrtfm.core.util;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * {@link FileVisitor} that recursively deletes a directory tree
 *
 * @since 1.0.0-alpha.1
 */
public class TreeDeleter extends SimpleFileVisitor<Path> implements Runnable {
	private final Path target;
	@SuppressWarnings("javadoc")
	public TreeDeleter(Path target) {
		this.target = target;
	}
	/**
	 * Start deleting the tree
	 */
	@Override
	public void run() {
		try {
			Files.walkFileTree(target, this);
		}
		catch (IOException e) {
			// Nothing to see here, move along.
		}
	}
	@SuppressWarnings("javadoc")
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		Files.delete(file);
		return FileVisitResult.CONTINUE;
	}
	@SuppressWarnings("javadoc")
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		Files.delete(dir);
		return FileVisitResult.CONTINUE;
	}
}
