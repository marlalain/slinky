package com.pauloelienay.slinky.generics

import org.springframework.beans.BeanUtils

interface IGenericEntity<S> {
    var id: S?

    val isUpdate: Boolean
        get() = id == null

    fun <T> copyAllButId(target: T): T {
        BeanUtils.copyProperties(this, target as Any, "id")
        return target
    }
}