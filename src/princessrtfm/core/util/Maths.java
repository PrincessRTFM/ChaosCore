package princessrtfm.core.util;


/**
 * General math utilities missing from the builtin library
 */
public class Maths {
	/**
	 * Returns the factorial of the given number
	 *
	 * @param from
	 *        The number to get the factorial of
	 * @return The factorial of <tt>from</tt>
	 * @throws ArithmeticException
	 *         if the process overflows a <tt>long</tt>
	 */
	public static final long factorial(final long from) {
		if (from <= 1) {
			return 1;
		}
		long result = from;
		long current = from;
		while (current > 1) {
			result = Math.multiplyExact(result, --current);
		}
		return result;
	}
	/**
	 * Returns the number of possible combinations (ordering-insensitive) of <tt>n</tt> items taken
	 * <tt>r</tt> at a time
	 *
	 * @param n
	 *        The total number of items
	 * @param r
	 *        The number of items to choose at one time
	 * @return The number of possible combinations
	 * @throws ArithmeticException
	 *         if the process overflows a <tt>long</tt>
	 */
	public static final long nCr(long n, long r) {
		return factorial(n) / Math.multiplyExact(factorial(n - r), factorial(r));
	}
	/**
	 * Returns the number of possible permutations (ordering-sensitive) of <tt>n</tt> items taken
	 * <tt>r</tt> at a time
	 *
	 * @param n
	 *        The total number of items
	 * @param r
	 *        The number of items to choose at one time
	 * @return The number of possible permutations
	 * @throws ArithmeticException
	 *         if the process overflows a <tt>long</tt>
	 */
	public static final long nPr(long n, long r) {
		return factorial(n) / factorial(n - r);
	}
}
