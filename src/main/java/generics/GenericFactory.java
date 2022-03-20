package generics;

import exceptions.GenericException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@SuppressWarnings({"unused"})
public class GenericFactory {
	@SuppressWarnings({"unchecked"})
	public static <T> T getInstance(T entity) throws GenericException {
		Class<T> clazz = (Class<T>) entity.getClass();
		return getInstance(entity, clazz);
	}

	public static <T> T getInstance(T entity, Class<T> clazz) throws GenericException {
		if (isNull(clazz) && nonNull(entity)) {
			throw new GenericException("Could not create a new instance of class " + entity.getClass().getName());
		} else if (isNull(clazz)) {
			throw new GenericException("Could not create a new instance of class");
		}

		Constructor<T> constructor = getConstructor(entity, clazz);

		return getNewInstance(entity, constructor);
	}

	public static <T> Constructor<T> getConstructor(T entity, Class<T> clazz) throws GenericException {
		Constructor<T> constructor;

		Field[] fieldList = entity.getClass().getDeclaredFields();
		Class<?>[] classes = Arrays.stream(fieldList)
			.map(Field::getType)
			.toArray(Class<?>[]::new);

		try {
			constructor = clazz.getConstructor(classes);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new GenericException(e.getMessage(), e.getCause());
		}

		return constructor;
	}

	private static <T> T getNewInstance(T entity, Constructor<T> constructor) throws GenericException {
		T newInstance;

		try {
			List<Field> fields = List.of(entity.getClass().getDeclaredFields());
			Object[] values = fieldsToValues(fields, entity);

			newInstance = constructor.newInstance(values);
		} catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException | InstantiationException e) {
			throw new GenericException(e.getMessage(), e.getCause());
		}

		return newInstance;
	}

	private static <T> Object[] fieldsToValues(List<Field> fields, T entity) {
		return fields.stream()
			.map(field -> fieldToValue(field, entity))
			.toArray(Object[]::new);
	}

	private static <T> Object fieldToValue(Field field, T entity) {
		try {
			field.setAccessible(true);
			return field.get(entity);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
