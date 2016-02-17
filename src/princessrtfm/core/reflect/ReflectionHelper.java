package princessrtfm.core.reflect;


/**
 * General reflection methods, intended to simplify reflection tasks
 */
public abstract class ReflectionHelper {
	/**
	 * Returns <code>true</code> if the following conditions are all met:
	 * <ol>
	 * <li>the first <tt>Class&lt;?></tt> object is not null</li>
	 * <li>the second <tt>Class&lt;?></tt> object is not null</li>
	 * <li>the second <tt>Class&lt;?></tt> object is the same as, or a subclass/implementor of, the
	 * first <tt>Class&lt;?></tt> object</li>
	 * </ol>
	 *
	 * @param first
	 *        The first class object (this method will return <code>true</code> if it is the
	 *        superclass or an implemented interface of the second)
	 * @param second
	 *        The second class object (this method will return <code>true</code> if it extends or
	 *        implements the first)
	 * @return <code>true</code> <b>iff</b> neither <tt>Class&lt;?></tt> object is null, and the
	 *         second extends/implements the first
	 */
	public static boolean doesSecondExtendFirst(Class<?> first, Class<?> second) {
		if ( (first == null) || (second == null)) {
			return false;
		}
		return first.isAssignableFrom(second);
	}
	/**
	 * Returns <code>true</code> if the following conditions are all met:
	 * <ol>
	 * <li>the <tt>Class&lt;?></tt> object is not null</li>
	 * <li>the <tt>Object</tt> object can be assigned to a variable with the type represented by the
	 * <tt>Class&lt;?></tt> object</li>
	 * </ol>
	 * It should be noted that if the <tt>Object</tt> is <code>null</code>, this method will return
	 * <code>true</code> <b>iff</b> the <tt>Class&lt;?></tt> is NOT primitive.<br/>
	 * <br/>
	 * For example:<br/>
	 * <tt>
	 * ReflectionHelper.isObjectAssignableToType(String.class, null) == true<br/>
	 * ReflectionHelper.isObjectAssignableToType(int.class, null) == false<br/>
	 * ReflectionHelper.isObjectAssignableToType(Throwable.class, new Exception()) == true<br/>
	 * </tt>
	 *
	 * @param type
	 *        The <tt>Class&lt;?></tt> object representing the type to check the object against
	 * @param obj
	 *        The object to check against the <tt>Class&lt;?></tt> object
	 * @return <code>true</code> <b>iff</b> the <tt>Object</tt> is not null, and can be assigned to
	 *         a variable with the type represented by the <tt>Class&lt;?></tt> object.
	 */
	public static boolean isObjectAssignableToType(Class<?> type, Object obj) {
		if (type == null) {
			return false;
		}
		else if (obj == null) {
			return !type.isPrimitive();
		}
		return doesSecondExtendFirst(type, obj.getClass());
	}
}
