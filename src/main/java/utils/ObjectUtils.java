package utils;

import java.util.function.Supplier;

import static java.util.Objects.isNull;

/**
 * Util for dealing with objects.
 */
@SuppressWarnings({"unused"})
public final class ObjectUtils {
	/**
	 * @param supplier A supplier that can throw a NullPointerException when accessed.
	 * @return True if the supplier found a null value.
	 */
	public static boolean safeNullEval(Supplier<?> supplier) {
		try {
			var object = supplier.get();
			return isNull(object);
		} catch (NullPointerException e) {
			return true;
		}
	}

	/**
	 * @param supplier A supplier that can throw a NullPointerException when accessed.
	 * @return True if the supplier couldn't find a null value.
	 */
	public static boolean safeNonNullEval(Supplier<?> supplier) {
		return !safeNullEval(supplier);
	}

	/**
	 * @param supplier A supplier that can throw a NullPointerException when accessed.
	 * @param <T> An object that can be null.
	 * @return Either returns the object or null (if the object was null).
	 */
	public static <T> T safeEval(Supplier<T> supplier) {
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return null;
		}
	}
}
