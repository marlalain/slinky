package com.pauloelienay.slinky.generics

import org.springframework.http.ResponseEntity

interface IGenericController<T : IGenericEntity<S>, S> {
	fun findAll(): ResponseEntity<List<T>>
	fun save(entity: T): ResponseEntity<T>
	fun update(entity: T, id: S): ResponseEntity<T>
	fun updateNonNullFields(entity: T, id: S): ResponseEntity<T>
	fun deleteById(id: S): ResponseEntity<Void>
	fun getById(id: S): ResponseEntity<T>
	fun getChildById(id: S, child: String): ResponseEntity<Any>
	fun headById(id: S): ResponseEntity<T>
}