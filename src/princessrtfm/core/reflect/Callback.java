package princessrtfm.core.reflect;


import java.lang.reflect.Method;

import princessrtfm.core.util.WrappedResult;


/**
 * TODO: Javadoc
 */
@SuppressWarnings("javadoc")
public class Callback {
	public final Object target;
	public final Method method;
	public Callback(Object object, Method meth) {
		target = object;
		method = meth;
	}
	public boolean isValid() {
		return method != null;
	}
	public WrappedResult call(Object... args) {
		if (!isValid()) {
			return WrappedResult.wrapError(null);
		}
		try {
			return WrappedResult.wrapSuccess(method.invoke(target, args));
		}
		catch (Exception e) {
			return WrappedResult.wrapError(e);
		}
	}
	public Callback force(boolean flag) {
		method.setAccessible(flag);
		return this;
	}
	public Callback force() {
		return force(true);
	}
	public String className() {
		return method.getDeclaringClass().getCanonicalName();
	}
	public String methodName() {
		return method.getName();
	}
	public String fullName() {
		return className() + "." + methodName();
	}
	public String declaration() {
		return method.toGenericString();
	}
}
