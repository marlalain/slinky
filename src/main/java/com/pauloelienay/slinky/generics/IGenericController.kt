package com.pauloelienay.slinky.generics

import org.springframework.http.ResponseEntity

interface IGenericController<T : IGenericEntity<S>, S> {
	fun save(entity: T): ResponseEntity<T>
	fun update(entity: T, id: S): ResponseEntity<T>
	fun updateNonNullFields(entity: T, id: S): ResponseEntity<T>
	fun deleteById(id: S): ResponseEntity<Void>
	fun getById(id: S): ResponseEntity<T>
	fun headById(id: S): ResponseEntity<T>
}