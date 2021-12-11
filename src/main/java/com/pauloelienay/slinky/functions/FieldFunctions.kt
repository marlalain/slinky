package com.pauloelienay.slinky.functions

import com.pauloelienay.slinky.extensions.isFieldNull
import java.lang.reflect.Field
import kotlin.streams.toList

fun <T : Any> nonNullFields(entity: T): List<Field> {
	entity::class.java.declaredFields.toList().parallelStream()
		.forEach { it.isAccessible = true }
	return entity::class.java.declaredFields.toList()
		.parallelStream()
		.filter { it.isFieldNull }
		.toList()
}