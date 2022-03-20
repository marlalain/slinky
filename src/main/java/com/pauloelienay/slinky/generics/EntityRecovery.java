package com.pauloelienay.slinky.generics;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRecovery {
	<T> List<T> find(Class<T> tClass, Specification<T> specification);

	<T> Page<T> findPage(Class<T> tClass, Specification<T> specification);

	<T, S> T findOne(S id, Class<T> tClass, String... projection);
}
