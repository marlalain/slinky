package com.pauloelienay.slinky.generics

import com.pauloelienay.slinky.extensions.isEmbedded
import com.pauloelienay.slinky.extensions.isFieldValueNull
import com.pauloelienay.slinky.extensions.isFromEntity
import com.pauloelienay.slinky.extensions.nonNullFieldsFromEntity
import com.pauloelienay.slinky.extensions.value
import java.lang.reflect.Field
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaUpdate
import javax.persistence.criteria.Root
import javax.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.util.ReflectionUtils

@Suppress("unused")
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
			if (it.isFromEntity && it.isFieldValueNull(entity))
				criteria.set(root.get<Any>(it.name).toString(), builder.nullLiteral(it.type))
			if (it.isEmbedded) handleEmbedded(it, criteria, root, entity)
			criteria.set(root.get(it.name), it.value(entity))
		}

		criteria.where(builder.equal(root.get<T>("id"), entity.id))

		entityManager.createQuery(criteria).executeUpdate()
	}

	private fun handleEmbedded(field: Field, criteria: CriteriaUpdate<T>, root: Root<T>, entity: T) {
		val value = field.value(entity)
		value?.let { safeValue ->
			val fields: MutableList<Any> = mutableListOf()
			safeValue::class.java.declaredFields.forEach {
				it?.let {
					ReflectionUtils.makeAccessible(it)
					fields.add(it)
				}
			}
			criteria.set(root.get(field.name), value)
		}
	}
}
