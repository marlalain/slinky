package com.pauloelienay.slinky.generics

import java.util.Optional

interface IGenericBusiness<T : IGenericEntity<S>, S> {
	fun findAll(): List<T>
	fun findById(id: S): T
	fun findChildById(id: S, child: String): Any?
	fun save(entity: T): T
	fun justSave(entity: T)
	fun update(entity: T, id: S): Optional<T>
	fun justUpdate(entity: T, id: S)
	fun updateNonNullFields(entity: T, id: S)
	fun deleteById(id: S)
	fun existsById(id: S): Boolean
}