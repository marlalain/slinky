package com.pauloelienay.slinky.extensions

import java.lang.reflect.Field
import org.springframework.util.ReflectionUtils

fun <T> Class<T>.nonNullFieldsFromEntity(entity: T): List<Field> {
	val fields: MutableList<Field> = mutableListOf()
	this.declaredFields.forEach {
		ReflectionUtils.makeAccessible(it)
		if (ReflectionUtils.getField(it, entity) != null && it.name != "id") fields.add(it)
	}
	return fields
}