package com.pauloelienay.slinky.extensions

import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field

fun <T> Class<T>.nonNullFieldsFromEntity(entity: T): List<Field> {
	val fields: MutableList<Field> = mutableListOf()
	this.declaredFields.forEach {
		ReflectionUtils.makeAccessible(it)
		if (ReflectionUtils.getField(it, entity) != null && it.name != "id") fields.add(it)
	}
	return fields
}