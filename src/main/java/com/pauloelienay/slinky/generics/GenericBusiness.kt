package com.pauloelienay.slinky.generics

import com.pauloelienay.slinky.exceptions.EntityNotFoundException
import java.util.Optional

open class GenericBusiness<T : IGenericEntity<S>, S>
	(private val repository: IGenericRepository<T, S>) : IGenericBusiness<T, S> {
	
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
		headById(id)
		entity.id = id
		repository.save(entity)
	}
	
	override fun updateNonNullFields(entity: T, id: S) {
		entity.id = id
		repository.updateNonNullFields(entity)
	}
	
	override fun deleteById(id: S) {
		headById(id)
		repository.deleteById(id)
	}
	
	override fun getById(id: S): T {
		return repository.findById(id)
			.orElseThrow { throw EntityNotFoundException() }
	}
	
	override fun headById(id: S): Boolean {
		if (repository.existsById(id)) return true
		else throw EntityNotFoundException()
	}
}