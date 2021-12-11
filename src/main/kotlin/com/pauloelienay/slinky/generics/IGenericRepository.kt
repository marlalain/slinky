package com.pauloelienay.slinky.generics

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository

@NoRepositoryBean
interface IGenericRepository<T : IGenericEntity<S>, S> : PagingAndSortingRepository<T, S> {
	fun updateNonNullFields(entity: T)
}