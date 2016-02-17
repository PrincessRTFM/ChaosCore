package princessrtfm.core.util;


/**
 * Simplifies bitwise utilities which are often used to pass multiple flags in one integer
 */
public class Bitmask {
	protected int mask = 0;
	/**
	 * Create a new Bitmask with the default value of 0
	 */
	public Bitmask() {}
	/**
	 * Create a new Bitmask with the given value
	 *
	 * @param initial
	 *        The initial value of the new Bitmask
	 */
	public Bitmask(int initial) {
		mask = initial;
	}
	/**
	 * Apply the given flag(s) to this object
	 *
	 * @param flags
	 *        The flags to apply
	 * @return <tt>this</tt>
	 */
	public final Bitmask apply(int... flags) {
		for (int flag : flags) {
			mask |= flag;
		}
		return this;
	}
	/**
	 * Remove the given flag(s) from this bitmask
	 *
	 * @param flags
	 *        The flags to remove
	 * @return <tt>this</tt>
	 */
	public final Bitmask remove(int... flags) {
		for (int flag : flags) {
			mask &= ~flag;
		}
		return this;
	}
	/**
	 * Reset the value of this mask to 0
	 *
	 * @return <tt>this</tt>
	 */
	public final Bitmask clear() {
		mask = 0;
		return this;
	}
	/**
	 * Get the value of this mask
	 *
	 * @return The integer value of this bitmask
	 */
	public final int value() {
		return mask;
	}
	@SuppressWarnings("javadoc")
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + mask;
		return result;
	}
	/**
	 * Check if this bitmask equals the given one (assuming another bitmask is passed)
	 */
	@Override
	public boolean equals(Object test) {
		try {
			return this.value() == ((Bitmask) test).value();
		}
		catch (Exception e) {
			return false;
		}
	}
}
