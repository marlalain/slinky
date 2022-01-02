package com.pauloelienay.slinky.generics

import java.util.*

interface IGenericBusiness<T : IGenericEntity<S>, S> {
	fun findAll(): List<T>
	fun save(entity: T): T
	fun justSave(entity: T)
	fun update(entity: T, id: S): Optional<T>
	fun justUpdate(entity: T, id: S)
	fun updateNonNullFields(entity: T, id: S)
	fun deleteById(id: S)
	fun getById(id: S): T
	fun getChildById(id: S, child: String): Any?
	fun headById(id: S): Boolean
}