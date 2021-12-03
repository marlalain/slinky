package com.pauloelienay.slinky.generics

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

open class GenericController<T : IGenericEntity<S>, S>
    (private val business: IGenericBusiness<T, S>) : IGenericController<T, S> {

    @PostMapping
    override fun save(@RequestBody entity: T): ResponseEntity<T> {
        return ResponseEntity.ok(business.save(entity))
    }

    @PutMapping("{id}")
    override fun update(@RequestBody entity: T, @PathVariable id: S): ResponseEntity<T> {
        return business.update(entity, id)
            .let { ResponseEntity.noContent().build() }
    }

    @DeleteMapping("{id}")
    override fun deleteById(@PathVariable id: S): ResponseEntity<Void> {
        return business.deleteById(id)
            .let { ResponseEntity.noContent().build() }
    }

    @GetMapping("{id}")
    override fun getById(@PathVariable id: S): ResponseEntity<T> {
        return ResponseEntity.ok(business.getById(id))
    }
}