package com.pauloelienay.slinky.generics

import java.util.Optional

interface IGenericBusiness<T : IGenericEntity<S>, S> {
	fun save(entity: T): T
	fun justSave(entity: T)
	fun update(entity: T, id: S): Optional<T>
	fun justUpdate(entity: T, id: S)
	fun updateNonNullFields(entity: T, id: S)
	fun deleteById(id: S)
	fun getById(id: S): T
	fun headById(id: S): Boolean
}