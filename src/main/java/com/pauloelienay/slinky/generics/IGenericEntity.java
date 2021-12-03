package com.pauloelienay.slinky.generics;

import java.io.Serializable;

import static java.util.Objects.isNull;

@SuppressWarnings({"unused"})
public interface IGenericEntity<T, S extends Serializable> {
	S getId();

	default boolean isUpdate() {
		return isNull(getId());
	}
}
