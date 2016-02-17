package princessrtfm.core.reflect;


import java.util.ArrayList;
import java.util.Iterator;

import princessrtfm.core.util.WrappedResult;


/**
 * TODO: Javadoc
 */
@SuppressWarnings("javadoc")
public class Callbacks extends ArrayList<Callback> {
	public static class CallbackWithResult {
		public final Callback callback;
		public final WrappedResult result;
		protected CallbackWithResult(Callback cb, WrappedResult res) {
			callback = cb;
			result = res;
		}
	}
	private static final long serialVersionUID = -7764797081174041337L;
	public final synchronized CallbackWithResult untilSuccess(Object... args) {
		return untilIsErrorEquals(false, args);
	}
	public final synchronized CallbackWithResult untilError(Object... args) {
		return untilIsErrorEquals(true, args);
	}
	private final synchronized CallbackWithResult untilIsErrorEquals(boolean error, Object... args) {
		Iterator<Callback> iter = this.iterator();
		while (iter.hasNext()) {
			Callback cb = iter.next();
			WrappedResult result = cb.call(args);
			if (result.isError == error) {
				return new CallbackWithResult(cb, result);
			}
		}
		return null;
	}
}
