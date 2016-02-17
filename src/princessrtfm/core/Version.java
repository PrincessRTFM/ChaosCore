package princessrtfm.core;


import java.util.regex.Pattern;


/**
 * Implements Semantic Versioning 2.0.0 (see <a href="http://semver.org/">http://semver.org/</a>).
 * <tt>Version</tt> objects can be compared to determine whether one version is earlier than
 * another.
 */
// Why the name ChaosCore?
// Simple.
// Have you _seen_ my code? It's all chaos.
public class Version implements Comparable<Version> {
	/**
	 * The version of this copy of ChaosCore
	 */
	public static final Version CHAOSCORE_VERSION = new Version(1, 0, 0, "", "");
	/**
	 * Thrown by {@link Version#require(Version)} if this copy of ChaosCore is not at least the
	 * requested version
	 */
	public static class MinimumVersionNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 6835195625575229320L;
		protected final Version needed;
		@SuppressWarnings("javadoc")
		public MinimumVersionNotFoundException(Version minimum) {
			super();
			needed = minimum;
		}
		@SuppressWarnings("javadoc")
		@Override
		public String getMessage() {
			if (needed == null) {
				return "Required ChaosCore version not available, this is " + Version.CHAOSCORE_VERSION + ", required version is unknown";
			}
			else {
				return "Required ChaosCore version " + needed + " not availble, this is " + Version.CHAOSCORE_VERSION;
			}
		}
	}
	/**
	 * The major version number represented by this Version object
	 */
	public final int MAJOR;
	/**
	 * The minor version number represented by this Version object
	 */
	public final int MINOR;
	/**
	 * The patch version number represented by this Version object
	 */
	public final int PATCH;
	/**
	 * The prerelease data represented by this Version object
	 */
	public final String PRE_DATA;
	/**
	 * The build metadata represented by this Version object
	 */
	public final String META;
	protected static final String REGEX_NUMERIC_IDENT = "(?:[1-9]\\d+|[0-9])";
	protected static final String REGEX_REQUIRED_CONTENT = REGEX_NUMERIC_IDENT + "\\." + REGEX_NUMERIC_IDENT + "\\." + REGEX_NUMERIC_IDENT;
	private Version(int major, int minor, int patch, String pre, String meta) {
		MAJOR = major;
		MINOR = minor;
		PATCH = patch;
		PRE_DATA = pre == null ? "" : pre;
		META = meta == null ? "" : meta;
	}
	/**
	 * Check if this copy of ChaosCore is at least at the given version, AND the same major version.
	 * This is because the major version number is changed always and only with
	 * backwards-incompatible changes to the code.
	 *
	 * @param min
	 *        the minimum version desired
	 * @return <code>true</code> if this copy of ChaosCore is at least the given version and the
	 *         same major version, <code>false</code> otherwise
	 */
	public static final boolean atLeast(Version min) {
		// Make sure that we are at least the version requested, but NOT a different major version.
		// Major version number is incremented whenever a backwards INCOMPATIBLE change is made.
		// This means that asking for at least 1.2.5-alpha will return true on 1.3.2, or 1.2.5-beta,
		// or even 1.2.5, but not on 1.0.0 or 2.1.3
		return (CHAOSCORE_VERSION.MAJOR == min.MAJOR) && (CHAOSCORE_VERSION.compareTo(min) >= 0);
	}
	/**
	 * Throws a {@link MinimumVersionNotFoundException} if the current copy of ChaosCore is not at
	 * least the given version
	 *
	 * @param min
	 *        the minimum version desired
	 * @throws MinimumVersionNotFoundException
	 *         if the required version of ChaosCore is not available
	 */
	public static final void require(Version min) throws MinimumVersionNotFoundException {
		if (!atLeast(min)) {
			throw new MinimumVersionNotFoundException(min);
		}
	}
	/**
	 * Checks to see whether a version string is valid
	 *
	 * @param verStr
	 *        the version string to check
	 * @return whether or not the given version string is valid
	 */
	public static boolean isValid(String verStr) {
		return parse(verStr) != null;
	}
	/**
	 * Construct a version object from the individual parts
	 *
	 * @param major
	 *        the major version number
	 * @param minor
	 *        the minor version number
	 * @param patch
	 *        the patch version number
	 * @param pre
	 *        the prerelease data
	 * @param meta
	 *        the build metadata
	 * @return a <tt>Version</tt> object with the given parts if valid, <code>null</code> otherwise
	 */
	public static Version create(int major, int minor, int patch, String pre, String meta) {
		pre = pre != null ? pre.trim() : "";
		meta = meta != null ? meta.trim() : "";
		if (!validateDotDelimitedAlphanumericIdentifier(pre)) {
			return null;
		}
		if (!validateDotDelimitedAlphanumericIdentifier(meta)) {
			return null;
		}
		return new Version(major, minor, patch, pre, meta);
	}
	/**
	 * Construct a version object from the individual parts
	 *
	 * @param major
	 *        the major version number
	 * @param minor
	 *        the minor version number
	 * @param patch
	 *        the patch version number
	 * @param pre
	 *        the prerelease data
	 * @since 1.0.0-alpha.3
	 * @return a <tt>Version</tt> object with the given parts if valid, <code>null</code> otherwise
	 */
	public static Version create(int major, int minor, int patch, String pre) {
		return create(major, minor, patch, pre, "");
	}
	/**
	 * Construct a version object from the individual parts
	 *
	 * @param major
	 *        the major version number
	 * @param minor
	 *        the minor version number
	 * @param patch
	 *        the patch version number
	 * @since 1.0.0-alpha.3
	 * @return a <tt>Version</tt> object with the given parts if valid, <code>null</code> otherwise
	 */
	public static Version create(int major, int minor, int patch) {
		return create(major, minor, patch, "");
	}
	/**
	 * Construct a version object from the individual parts
	 *
	 * @param major
	 *        the major version number
	 * @param minor
	 *        the minor version number
	 * @since 1.0.0-alpha.3
	 * @return a <tt>Version</tt> object with the given parts if valid, <code>null</code> otherwise
	 */
	public static Version create(int major, int minor) {
		return create(major, minor, 0);
	}
	/**
	 * Construct a version object from the individual parts
	 *
	 * @param major
	 *        the major version number
	 * @since 1.0.0-alpha.3
	 * @return a <tt>Version</tt> object with the given parts if valid, <code>null</code> otherwise
	 */
	public static Version create(int major) {
		return create(major, 0);
	}
	/**
	 * Construct a version object from a string
	 *
	 * @param verStr
	 *        the version string to represent
	 * @return a <tt>Version</tt> object representing the given string if valid, <code>null</code>
	 *         otherwise
	 */
	public static Version parse(String verStr) {
		int ma = 0;
		int mi = 0;
		int pa = 0;
		String pr = "";
		String me = "";
		if (Pattern.matches("^" + REGEX_REQUIRED_CONTENT + "$", verStr)) {
			String[] mmp = verStr.split("\\.");
			ma = Integer.parseInt(mmp[0]);
			mi = Integer.parseInt(mmp[1]);
			pa = Integer.parseInt(mmp[2]);
		}
		else if (Pattern.matches("^" + REGEX_REQUIRED_CONTENT + "-[^+]+$", verStr)) {
			String[] t = verStr.split("-", 2);
			if (!validateDotDelimitedAlphanumericIdentifier(t[1])) {
				return null;
			}
			String[] mmp = t[0].split("\\.");
			ma = Integer.parseInt(mmp[0]);
			mi = Integer.parseInt(mmp[1]);
			pa = Integer.parseInt(mmp[2]);
			pr = t[1];
		}
		else if (Pattern.matches("^" + REGEX_REQUIRED_CONTENT + "\\+.+$", verStr)) {
			String[] t = verStr.split("\\+", 2);
			if (!validateDotDelimitedAlphanumericIdentifier(t[1])) {
				return null;
			}
			String[] mmp = t[0].split("\\.");
			ma = Integer.parseInt(mmp[0]);
			mi = Integer.parseInt(mmp[1]);
			pa = Integer.parseInt(mmp[2]);
			me = t[1];
		}
		else if (Pattern.matches("^" + REGEX_REQUIRED_CONTENT + "-[^+]+\\+.+$", verStr)) {
			String[] t1 = verStr.split("-", 2);
			String[] t2 = t1[1].split("\\+", 2);
			if (!validateDotDelimitedAlphanumericIdentifier(t2[0])) {
				return null;
			}
			if (!validateDotDelimitedAlphanumericIdentifier(t2[1])) {
				return null;
			}
			String[] mmp = t1[0].split("\\.");
			ma = Integer.parseInt(mmp[0]);
			mi = Integer.parseInt(mmp[1]);
			pa = Integer.parseInt(mmp[2]);
			pr = t2[0];
			me = t2[1];
		}
		else {
			return null;
		}
		return new Version(ma, mi, pa, pr, me);
	}
	/**
	 * Convert the version data into a string representation. The returned string can be passed to
	 * {@link #parse(String)} to re-create this object.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(MAJOR + "." + MINOR + "." + PATCH);
		if (!PRE_DATA.isEmpty()) {
			sb.append("-" + PRE_DATA);
		}
		if (!META.isEmpty()) {
			sb.append("+" + META);
		}
		return sb.toString();
	}
	protected static boolean validateDotDelimitedAlphanumericIdentifier(String test) {
		test = test.trim();
		if (test.isEmpty()) {
			return true;
		}
		String[] segments = test.split("\\.");
		for (String pred : segments) {
			if (Pattern.matches("^\\d+$", pred)) {
				if (!Pattern.matches("^" + REGEX_NUMERIC_IDENT + "$", pred)) {
					return false;
				}
			}
			else if (!Pattern.matches("^[a-zA-Z0-9-]+$", pred)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Compare this version to another version to determine which one is earlier. Note that build
	 * metadata is NOT considered when comparing.
	 *
	 * @param test
	 *        the Version object to compare to
	 * @return -1 if this object is earlier, 0 if the two are the same, 1 if this version is later
	 */
	@Override
	public int compareTo(Version test) {
		if (this.equals(test)) {
			return 0;
		}
		if (this.MAJOR < test.MAJOR) {
			return -1;
		}
		if (this.MAJOR > test.MAJOR) {
			return 1;
		}
		if (this.MINOR < test.MINOR) {
			return -1;
		}
		if (this.MINOR > test.MINOR) {
			return 1;
		}
		if (this.PATCH < test.PATCH) {
			return -1;
		}
		if (this.PATCH > test.PATCH) {
			return 1;
		}
		if (!this.PRE_DATA.isEmpty() && test.PRE_DATA.isEmpty()) {
			return -1;
		}
		if (this.PRE_DATA.isEmpty() && !test.PRE_DATA.isEmpty()) {
			return 1;
		}
		String[] mpre = this.PRE_DATA.split("\\.");
		String[] opre = test.PRE_DATA.split("\\.");
		for (int i = 0; i < mpre.length; ++i) {
			String m = mpre[i];
			String o;
			try {
				o = opre[i];
			}
			catch (ArrayIndexOutOfBoundsException e) {
				return 1;
			}
			if (Pattern.matches("^\\d+$", m)) {
				if (Pattern.matches("^\\d+$", o)) {
					int mi = Integer.parseInt(m);
					int oi = Integer.parseInt(o);
					if (mi < oi) {
						return -1;
					}
					if (mi > oi) {
						return 1;
					}
				}
				else {
					return -1;
				}
			}
			else if (Pattern.matches("^\\d+$", o)) {
				return 1;
			}
			else {
				if (m.compareTo(o) < 0) {
					return -1;
				}
				if (m.compareTo(o) > 0) {
					return 1;
				}
			}
		}
		return 0;
	}
	/**
	 * Generate a unique int representing this version object. All parts are considered, including
	 * build metadata
	 *
	 * @return a unique identifier for this exact version string
	 */
	public int uniqueId() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + MAJOR;
		result = (prime * result) + ( (META == null) ? 0 : META.hashCode());
		result = (prime * result) + MINOR;
		result = (prime * result) + PATCH;
		result = (prime * result) + ( (PRE_DATA == null) ? 0 : PRE_DATA.hashCode());
		return result;
	}
	/**
	 * Generate a hash code representing this version object. All parts are considered, EXCEPT build
	 * metadata
	 *
	 * @return a hash code, unique to the version this object represents
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + MAJOR;
		result = (prime * result) + MINOR;
		result = (prime * result) + PATCH;
		result = (prime * result) + ( (PRE_DATA == null) ? 0 : PRE_DATA.hashCode());
		return result;
	}
	/**
	 * Compare this version to another object. If the target is another <tt>Version</tt> object, all
	 * fields EXCEPT build metadata will be checked.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (! (obj instanceof Version)) {
			return false;
		}
		Version other = (Version) obj;
		if (MAJOR != other.MAJOR) {
			return false;
		}
		if (MINOR != other.MINOR) {
			return false;
		}
		if (PATCH != other.PATCH) {
			return false;
		}
		if (PRE_DATA == null) {
			if (other.PRE_DATA != null) {
				return false;
			}
		}
		else if (!PRE_DATA.equals(other.PRE_DATA)) {
			return false;
		}
		return true;
	}
}
