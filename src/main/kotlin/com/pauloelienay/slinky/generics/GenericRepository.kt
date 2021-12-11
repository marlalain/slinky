package com.pauloelienay.slinky.generics

import com.pauloelienay.slinky.extensions.nonNullFieldsFromEntity
import com.pauloelienay.slinky.extensions.value
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager
import javax.transaction.Transactional

open class GenericRepository<T : IGenericEntity<S>, S>(
	private val clazz: Class<T>,
	private val entityManager: EntityManager
) : IGenericRepository<T, S>, JpaRepository<T, S>,
	SimpleJpaRepository<T, S>(clazz, entityManager) {

	@Transactional
	override fun updateNonNullFields(entity: T) {
		val builder = entityManager.criteriaBuilder
		val criteria = builder.createCriteriaUpdate(clazz)
		val root = criteria.from(clazz)

		clazz.nonNullFieldsFromEntity(entity).forEach {
			criteria.set(root.get(it.name), it.value(entity))
		}

		criteria.where(builder.equal(root.get<T>("id"), entity.id))

		entityManager.createQuery(criteria).executeUpdate()
	}
}
