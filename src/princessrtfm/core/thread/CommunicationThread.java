package princessrtfm.core.thread;


import java.util.ArrayDeque;
import java.util.Deque;


/**
 * A {@link Thread} that can be passed objects while running.
 *
 * @param <T>
 *        the type of objects this thread will be passed
 * @since 1.0.0-alpha.1
 */
public class CommunicationThread<T> extends Thread {
	private Deque<T> input = new ArrayDeque<T>();
	private Deque<T> output = new ArrayDeque<T>();
	/**
	 * Send the thread an object. Can be called while the thread is running or before it starts.
	 *
	 * @param toSend
	 *        the object to pass to the thread
	 */
	public void sendInput(T toSend) {
		input.addLast(toSend);
	}
	protected T getInput() {
		return input.pollFirst();
	}
	protected boolean hasInput() {
		return !input.isEmpty();
	}
	protected void sendOutput(T toSend) {
		output.addLast(toSend);
	}
	/**
	 * Get output from the thread
	 *
	 * @return the object the thread is passing out
	 */
	public T getOutput() {
		return output.pollFirst();
	}
	/**
	 * Check whether the thread has any output in the queue
	 *
	 * @return <code>true</code> if the thread has output in the queue, <code>false</code> otherwise
	 */
	public boolean hasOutput() {
		return !output.isEmpty();
	}
}
