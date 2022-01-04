package com.pauloelienay.slinky.functions

import com.pauloelienay.slinky.generics.IGenericEntity
import java.util.TreeSet

class EntityComparator<T : IGenericEntity<S>, S : Comparable<S>> : Comparator<T> {
	override fun compare(e1: T, e2: T): Int {
		return if (e1.id == null) -1
		else if (e2.id == null) +1
		else e1.id!!.compareTo(e2.id!!)
	}
}

@Suppress("unused")
fun <T : IGenericEntity<S>, S : Comparable<S>> entitySetOf(): TreeSet<T> {
	return TreeSet<T>(EntityComparator<T, S>())
}