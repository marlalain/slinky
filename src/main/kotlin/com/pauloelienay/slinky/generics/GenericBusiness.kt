package com.pauloelienay.slinky.generics

import com.pauloelienay.slinky.exceptions.EntityNotFoundException
import com.pauloelienay.slinky.extensions.value
import java.util.Optional

open class GenericBusiness<T : IGenericEntity<S>, S>
	(private val repository: IGenericRepository<T, S>) : IGenericBusiness<T, S> {

	override fun findAll(): List<T> {
		return repository.findAll().toList()
	}

	override fun findById(id: S): T {
		return repository.findById(id)
			.orElseThrow { throw EntityNotFoundException() }
	}

	override fun findChildById(id: S, child: String): Any? {
		val entity = findById(id)
		val field = entity.javaClass.declaredFields.find { it.name.equals(child) }

		field?.let {
			it.isAccessible = true
			return it.value(entity)
		}

		return null
	}

	override fun save(entity: T): T {
		return repository.save(entity)
	}

	override fun justSave(entity: T) {
		repository.save(entity)
	}

	override fun update(entity: T, id: S): Optional<T> {
		return repository.findById(id)
			.map { target -> entity.copyAllButId(target) }
			.map(::save)
	}

	override fun justUpdate(entity: T, id: S) {
		existsById(id)
		entity.id = id
		repository.save(entity)
	}

	override fun updateNonNullFields(entity: T, id: S) {
		entity.id = id
		repository.updateNonNullFields(entity)
	}

	override fun deleteById(id: S) {
		existsById(id)
		repository.deleteById(id)
	}

	override fun existsById(id: S): Boolean {
		if (repository.existsById(id)) return true
		throw EntityNotFoundException()
	}
}