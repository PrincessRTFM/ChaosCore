package princessrtfm.core.util;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * General string utilities
 *
 * @since 1.0.0-alpha.1
 */
public abstract class StringUtil {
	/**
	 * Regex used to check if a string represents boolean <code>true</code>
	 */
	public static final String TRUE_PAT_STR = "(?:true|yes|on|1|enable|allow|permit)";
	/**
	 * Regex used to check if a string represents boolean <code>false</code>
	 */
	public static final String FALSE_PAT_STR = "(?:false|no|off|0|disable|disallow|forbid|deny)";
	/**
	 * Regex used to identify a range of integers
	 */
	public static final String INT_RANGE_STR = "^(?<start>-?\\d+)\\s*-\\s*(?<end>-?\\d+)$";
	/**
	 * {@link Pattern} to identify a string that represents boolean <code>true</code>
	 */
	public static final Pattern TRUE = Pattern.compile(TRUE_PAT_STR, Pattern.CASE_INSENSITIVE);
	/**
	 * {@link Pattern} to identify a string that represents boolean <code>false</code>
	 */
	public static final Pattern FALSE = Pattern.compile(FALSE_PAT_STR, Pattern.CASE_INSENSITIVE);
	/**
	 * {@link Pattern} to identify a range of integers, positive or negative
	 */
	public static final Pattern INT_RANGE = Pattern.compile(INT_RANGE_STR);
	/**
	 * Convenience method for determining if a string represents boolean <code>true</code>
	 *
	 * @param test
	 *        the string to check
	 * @return whether the string represents boolean <code>true</code>
	 */
	public static boolean isTrue(String test) {
		return TRUE.matcher(test).matches();
	}
	/**
	 * Convenience method for determining if a string represents boolean <code>false</code>
	 *
	 * @param test
	 *        the string to check
	 * @return whether the string represents boolean <code>false</code>
	 */
	public static boolean isFalse(String test) {
		return FALSE.matcher(test).matches();
	}
	/**
	 * Convenience method for determining if a string represents a boolean
	 *
	 * @param test
	 *        the string to check
	 * @return whether the string represents a boolean
	 */
	public static boolean isBoolean(String test) {
		return isTrue(test) || isFalse(test);
	}
	/**
	 * Calls {@link #expandIntegerList(String, String)} with a default output delimiter of a comma
	 * followed by a space: '<tt>,&nbsp;</tt>'
	 *
	 * @param condensed
	 *        the list of integers to enumerate
	 * @return a string containing an expanded and sorted list of the input integers
	 */
	public static String expandIntegerList(final String condensed) {
		// Just a wrapper...
		// Times like this I kinda wish Java allowed default values on methods, so I didn't have to
		// overload everything :/
		return expandIntegerList(condensed, ", ");
	}
	/**
	 * Takes a list of integers (positive or negative) in a string, all delimited by commas, and
	 * does the following:
	 * <ol>
	 * <li>Expands ranges (separated by a hyphen) into a list of discreet integers</li>
	 * <li>Orders the entire list from least to greatest</li>
	 * <li>Joins the list into a string using the given delimiter</li>
	 * </ol>
	 * Duplicates are not included in the returned string.
	 *
	 * @param condensed
	 *        the string containing the list of integers to enumerate
	 * @param delimOut
	 *        the delimiter for the output string
	 * @return a string containing an expanded and sorted list of the input integers
	 */
	public static String expandIntegerList(final String condensed, final String delimOut) {
		Set<Integer> all = new HashSet<Integer>();
		String[] parts = condensed.split(",");
		for (String section : parts) {
			section = section.trim();
			if (section.isEmpty())
				continue;
			if (section.matches("^-?\\d+$")) {
				// Congratulations, it's a number!
				// More importantly, it's a SINGLE number.
				try {
					all.add(Integer.parseInt(section, 10));
				}
				catch (NumberFormatException e) {
					// Okay, maybe it's NOT a single number.
					// Dunno what I should do here.
					// Not even sure what I can do here.
					// Let's just sweep it under the rug.
				}
			}
			else {
				// Let us assume, for the moment, that this is a range of numbers.
				// If we are correct, it should look like this: 20000 - 20050
				// The space is optional, as you can see by the regex definition.
				// And yes, those are ports used by Team Fortress 2.
				Matcher range = INT_RANGE.matcher(section);
				if (range.matches()) {
					// Bases besides 10 are not supported.
					// I mean, I might be able to, but... why?
					// And, for that matter, how would you indicate in your list what base to use?
					// It'd needlessly complicate the syntax, and my code.
					// So, I'm going with the age-old fallback:
					// It's an exercise for the reader!
					int low = Integer.parseInt(range.group("start"), 10);
					int high = Integer.parseInt(range.group("end"), 10);
					if (low > high) {
						int highReally = low;
						int lowReally = high;
						low = lowReally;
						high = highReally;
					}
					else if (low == high) {
						all.add(low); // Or all.add(high), take your pick.
						break;
					}
					for (int current = low; current < high; ++current) {
						all.add(current);
					}
				}
				else {
					// Yeah, no. I can't help you here.
					throw new NumberFormatException("Can't parse '" + section + "' as single integer or range");
				}
			}
		}
		Integer[] numbers = all.toArray(new Integer[] {});
		Arrays.sort(numbers);
		StringBuilder expanded = new StringBuilder();
		for (Integer number : numbers) {
			expanded.append(delimOut + String.valueOf(number));
		}
		return expanded.toString().substring(delimOut.length());
	}
	/**
	 * Check if a string comparator is valid for use with {@link #compare(double, String, double)}
	 *
	 * @param comparison
	 *        The string representing a mathematical comparison
	 * @return <code>true</code> if the string is recognized, <code>false</code> otherwise
	 */
	public static boolean validComparator(String comparison) {
		switch (comparison) {
			case "=":
			case "==":
			case "===":
			case ">":
			case ">=":
			case "=>":
			case "<":
			case "<=":
			case "=<":
			case "!":
			case "!=":
			case "!==":
			case "=!=":
			case "=!":
			case "==!":
			case "~":
			case "~=":
			case "~==":
			case "=~=":
			case "=~":
			case "==~":
				return true;
			default:
				return false;
		}
	}
	/**
	 * Compare two numbers based on a comparison string
	 *
	 * @param a
	 *        The left side of the comparison
	 * @param comparison
	 *        A string representing a mathematical test (==, >, >=, <, <=, !=, or similar)
	 * @param b
	 *        The right side of the comparison
	 * @return The value of the comparison string applied to the two doubles
	 */
	public static boolean compare(double a, String comparison, double b) {
		switch (comparison) {
			case "=":
			case "==":
			case "===": // *shrug*
				return a == b;
			case ">":
				return a > b;
			case ">=":
			case "=>": // *another shrug*
				return a >= b;
			case "<":
				return a < b;
			case "<=":
			case "=<":
				return a <= b;
			case "!":
			case "!=":
			case "!==":
			case "=!=":
			case "=!":
			case "==!":
			case "~":
			case "~=":
			case "~==":
			case "=~=":
			case "=~":
			case "==~":
				return a != b;
			default:
				return false;
		}
	}
}
