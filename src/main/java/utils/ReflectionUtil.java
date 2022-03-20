package utils;

import exceptions.GenericException;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@SuppressWarnings({"unused"})
public class ReflectionUtil {
	public static Field getIdField(Object entity) {
		Class<?> clazz = entity.getClass();
		Field id = null;

		while (nonNull(clazz)) {
			id = getIdField(clazz);
			if (nonNull(id)) break;
			clazz = clazz.getSuperclass();
		}

		return id;
	}

	public static <T> Field getIdField(Class<T> clazz) {
		return Arrays.asList(clazz.getDeclaredFields())
			.parallelStream()
			.filter(ReflectionUtil::haveIdAnnotation)
			.findFirst()
			.orElse(null);
	}

	private static boolean haveIdAnnotation(Field field) {
		return nonNull(field.getAnnotation(Id.class));
	}

	public static Object getValueByField(@NonNull Field field, @NonNull Object entity) throws GenericException {
		try {
			field.setAccessible(true);
			return field.get(entity);
		} catch (IllegalAccessException | IllegalArgumentException e) {
			throw new GenericException(e.getMessage(), e.getCause());
		}
	}

	public static boolean isSerialVersionField(Field field) {
		return field.getName().equals("serialVersionUID");
	}

	public static boolean isFieldNotNull(Field field, Object entity) {
		try {
			return nonNull(getValueByField(field, entity));
		} catch (GenericException e) {
			return false;
		}
	}

	public static boolean isUpdatable(Field field) {
		return (isNull(field.getAnnotation(Column.class)) || field.getAnnotation(Column.class).updatable())
			&& (isNull(field.getAnnotation(JoinColumn.class)) || field.getAnnotation(JoinColumn.class).updatable());
	}

	public static boolean isSubclassIterable(Field field) {
		return Iterable.class.isAssignableFrom(field.getType());
	}

	public static boolean isMappedByField(Field field) {
		return (nonNull(field.getAnnotation(OneToOne.class))
			&& StringUtils.isNullOrBlank(field.getAnnotation(OneToOne.class).mappedBy()))
			|| (nonNull(field.getAnnotation(OneToMany.class))
			&& StringUtils.isNullOrBlank(field.getAnnotation(OneToMany.class).mappedBy()))
			|| (nonNull(field.getAnnotation(ManyToMany.class))
			&& StringUtils.isNullOrBlank(field.getAnnotation(ManyToMany.class).mappedBy()));
	}

	public static boolean isFieldUpdatable(Field field, Object entity) {
		return !ReflectionUtil.isSerialVersionField(field)
			&& !ReflectionUtil.isIdField(field)
			&& ReflectionUtil.isFieldNotNull(field, entity)
			&& ReflectionUtil.isUpdatable(field)
			&& !ReflectionUtil.isSubclassIterable(field)
			&& !ReflectionUtil.isTransient(field)
			&& !ReflectionUtil.isMappedByField(field);
	}

	public static <T> boolean isFieldNull(Field field, T entity) {
		return !isFieldNotNull(field, entity);
	}

	public static boolean isIdField(Field field) {
		return isFieldAnnotatedWith(field, Id.class);
	}

	public static boolean isTransient(Field field) {
		return field.isAnnotationPresent(Transient.class);
	}

	public static boolean isEntity(Field field) {
		return field.getDeclaringClass().isAnnotationPresent(Entity.class);
	}

	public static boolean isEntity(Object object) {
		return object.getClass().isAnnotationPresent(Entity.class);
	}

	public static boolean isEmbeddable(Field field) {
		return field.getDeclaringClass().isAnnotationPresent(Embeddable.class);
	}

	public static boolean isEmbeddable(Object object) {
		return object.getClass().isAnnotationPresent(Embeddable.class);
	}

	private static boolean isFieldAnnotatedWith(Field field, Class<? extends Annotation> clazz) {
		return field.getType().isAnnotationPresent(clazz);
	}
}
