package com.pauloelienay.slinky.extensions

import java.lang.reflect.Field
import java.util.Objects.isNull
import javax.persistence.Embedded
import javax.persistence.Entity
import org.springframework.util.ReflectionUtils

val Field.isEmbedded: Boolean
	get() = this.isAnnotationPresent(Embedded::class.java)

val Field.isFromEntity: Boolean
	get() = this.type.isAnnotationPresent(Entity::class.java)

fun <T> Field.value(entity: T): Any? {
	this.isAccessible = true
	return ReflectionUtils.getField(this, entity)
}

fun <T> Field.isFieldValueNull(entity: T): Boolean {
	return isNull(this.value(entity))
}