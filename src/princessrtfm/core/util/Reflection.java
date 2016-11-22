package princessrtfm.core.util;


import java.lang.annotation.Annotation;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Static utility methods for working with reflection
 *
 * @since 1.0.0-alpha.1
 */
public class Reflection {
	/**
	 * Retrieve the name of the thread with the given ID, or <tt>&lt;unknown thread&gt;</tt> if it
	 * wasn't found.
	 *
	 * @param threadID
	 *        the ID of the thread to find
	 * @return name of the thread with the given ID, or <tt>&lt;unknown thread&gt;</tt>
	 */
	public static String getPrettyThreadName(long threadID) {
		ThreadInfo info = ManagementFactory.getThreadMXBean().getThreadInfo(threadID);
		if (info == null) {
			return "<unknown thread>";
		}
		return info.getThreadName();
	}
	/**
	 * @param target
	 *        the {@link Class} object to examine
	 * @return <code>true</code> if the class represented by the given object has any (direct or
	 *         indirect) annotations, <code>false</code> otherwise
	 */
	public static boolean isAnnotated(Class<?> target) {
		return isAnnotated(target, false);
	}
	/**
	 * @param target
	 *        the {@link Class} object to examine
	 * @param requireDirect
	 *        if <code>true</code>, ignores inherited annotations
	 * @return <code>true</code> if the class represented by the given object has any annotations,
	 *         <code>false</code> otherwise
	 */
	public static boolean isAnnotated(Class<?> target, boolean requireDirect) {
		Annotation[] annos;
		if (requireDirect) {
			annos = target.getDeclaredAnnotations();
		}
		else {
			annos = target.getAnnotations();
		}
		return annos.length > 0;
	}
	/**
	 * @param target
	 *        the {@link Method} object to examine
	 * @return <code>true</code> if the method represented by the given object has any (direct or
	 *         indirect) annotations, <code>false</code> otherwise
	 */
	public static boolean isAnnotated(Method target) {
		return isAnnotated(target, false);
	}
	/**
	 * @param target
	 *        the {@link Method} object to examine
	 * @param requireDirect
	 *        if <code>true</code>, ignores inherited annotations
	 * @return <code>true</code> if the method represented by the given object has any annotations,
	 *         <code>false</code> otherwise
	 */
	public static boolean isAnnotated(Method target, boolean requireDirect) {
		Annotation[] annos;
		if (requireDirect) {
			annos = target.getDeclaredAnnotations();
		}
		else {
			annos = target.getAnnotations();
		}
		return annos.length > 0;
	}
	/**
	 * @param target
	 *        the {@link Field} object to examine
	 * @return <code>true</code> if the field represented by the given object has any (direct or
	 *         indirect) annotations, <code>false</code> otherwise
	 */
	public static boolean isAnnotated(Field target) {
		return isAnnotated(target, false);
	}
	/**
	 * @param target
	 *        the {@link Field} object to examine
	 * @param requireDirect
	 *        if <code>true</code>, ignores inherited annotations
	 * @return <code>true</code> if the field represented by the given object has any annotations,
	 *         <code>false</code> otherwise
	 */
	public static boolean isAnnotated(Field target, boolean requireDirect) {
		Annotation[] annos;
		if (requireDirect) {
			annos = target.getDeclaredAnnotations();
		}
		else {
			annos = target.getAnnotations();
		}
		return annos.length > 0;
	}
	/**
	 * Attempt to invoke a static method on a given class by reflecting the class/method. This a
	 * convenience method for when you don't care what the method returns.
	 *
	 * @param className
	 *        the name of the class declaring the desired method
	 * @param methodName
	 *        the name of the method to call
	 * @param args
	 *        any arguments to pass to the method
	 * @return the {@link Exception} thrown while trying to statically call the given method, or
	 *         <code>null</code> if no error occurred
	 */
	public static Exception call(String className, String methodName, Object... args) {
		try {
			Class<?> clazz = Class.forName(className);
			Class<?>[] types = new Class<?>[args.length];
			for (int i = 0; i < args.length; ++i) {
				types[i] = args[i].getClass();
			}
			Method target = clazz.getDeclaredMethod(methodName, types);
			target.invoke(null, args);
			return null;
		}
		catch (Exception e) {
			return e;
		}
	}
	/**
	 * Try to retrieve the value of a static field with the given name from the given class
	 *
	 * @param className
	 *        the name of the declaring class to examine
	 * @param fieldName
	 *        the name of the field to examine
	 * @return a {@link WrappedResult} containing either the value of the field or an
	 *         {@link Exception} explaining why the value could not be returned
	 */
	public static WrappedResult value(String className, String fieldName) {
		try {
			Class<?> clazz = Class.forName(className);
			Field field = clazz.getField(fieldName);
			WrappedResult result = WrappedResult.wrapSuccess(field.get(null));
			return result;
		}
		catch (Exception e) {
			return WrappedResult.wrapError(e);
		}
	}
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
	 * Reflection.isObjectAssignableToType(String.class, null) == true<br/>
	 * Reflection.isObjectAssignableToType(int.class, null) == false<br/>
	 * Reflection.isObjectAssignableToType(Throwable.class, new Exception()) == true<br/>
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
