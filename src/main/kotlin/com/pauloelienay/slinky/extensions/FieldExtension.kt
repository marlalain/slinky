package com.pauloelienay.slinky.extensions

import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import javax.persistence.Embedded
import javax.persistence.Id

val Field.isEmbedded: Boolean
	get() = this.isAnnotationPresent(Embedded::class.java)

val Field.isId: Boolean
	get() = this.isAnnotationPresent(Id::class.java)

fun <T> Field.value(entity: T): Any? {
	this.isAccessible = true
	return ReflectionUtils.getField(this, entity)
}

val Field.isFieldNull: Boolean
	get() = this.equals(null)