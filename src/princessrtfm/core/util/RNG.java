package princessrtfm.core.util;


import java.util.Random;


/**
 * Improved random number generator using {@link Random}
 *
 * @since 1.0.0-alpha.1
 */
public class RNG extends Random {
	private static final long serialVersionUID = 6771018105844837746L;
	/**
	 * Default lower bound for random ints
	 */
	public final int MIN;
	/**
	 * Default upper bound for random ints
	 */
	public final int MAX;
	/**
	 * Initialize the RNG with all default values
	 *
	 */
	public RNG() {
		this(Datetime.now().date.getTime());
	}
	/**
	 * Initialize with a custom seed
	 *
	 * @param seed
	 *        The seed for this RNG
	 */
	public RNG(long seed) {
		this(seed, 1, 6);
	}
	/**
	 * Initialize with a custom seed and specific default bounds for random ints
	 *
	 * @param seed
	 *        The seed for this RNG
	 * @param minimum
	 *        The default lower bound for random ints
	 * @param maximum
	 *        The default upper bound for random ints
	 */
	public RNG(long seed, int minimum, int maximum) {
		super(seed);
		MIN = minimum;
		MAX = maximum;
	}
	/**
	 * Get a random integer within the default bounds
	 *
	 * @return random int
	 */
	public int getInt() {
		return getInt(MIN, MAX);
	}
	/**
	 * Get a random integer within the given upper bound and the default lower bound
	 *
	 * @param high
	 *        the upper bound for this int
	 * @return random int
	 */
	public int getInt(int high) {
		return getInt(MIN, high);
	}
	/**
	 * Get a random integer within the given bounds
	 *
	 * @param low
	 *        the lower bound for this int
	 * @param high
	 *        the upper bound for this int
	 * @return random int
	 */
	public int getInt(int low, int high) {
		if (high == low) {
			return 0;
		}
		if (high - low < 0) {
			int tl = low;
			int th = high;
			low = th;
			high = tl;
		}
		return nextInt( (high - low) + 1) + low;
	}
	/**
	 * Get a random double within the default bounds
	 *
	 * @return random double
	 */
	public double getDouble() {
		return getDouble(MIN, MAX);
	}
	/**
	 * Get a random double within the given upper bound and the default lower bound
	 *
	 * @param high
	 *        upper bound
	 * @return random double
	 */
	public double getDouble(double high) {
		return getDouble(MIN, high);
	}
	/**
	 * Get a random double within the given bounds
	 *
	 * @param low
	 *        lower bound
	 * @param high
	 *        upper bound
	 * @return random double
	 */
	public double getDouble(double low, double high) {
		if (high == low) {
			return 0;
		}
		if (high - low < 0) {
			double tl = low;
			double th = high;
			low = th;
			high = tl;
		}
		return low + (high - low) * nextDouble();
	}
}
