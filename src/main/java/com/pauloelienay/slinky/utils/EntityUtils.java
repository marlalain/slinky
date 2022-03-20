package com.pauloelienay.slinky.utils;

import com.pauloelienay.slinky.generics.IGenericEntity;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings({"unused"})
public final class EntityUtils {
	/**
	 * @param entity Entity that can be null.
	 * @param <T> Entity type.
	 * @param <S> Entity ID type.
	 * @return True if the entity or its ID are null.
	 */
	public static <T extends IGenericEntity<T, S>, S extends Serializable> boolean isNull(T entity) {
		return Objects.isNull(entity) || Objects.isNull(entity.getId());
	}

	/**
	 * @param entity Entity that can be null.
	 * @param <T> Entity type.
	 * @param <S> Entity ID type.
	 * @return True if the entity and its ID are not null.
	 */
	public static <T extends IGenericEntity<T, S>, S extends Serializable> boolean nonNull(T entity) {
		return !isNull(entity);
	}
}
