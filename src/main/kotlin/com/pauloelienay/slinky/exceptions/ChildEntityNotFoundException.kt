package com.pauloelienay.slinky.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ChildEntityNotFoundException(message: String = "Child entity not found.") : RuntimeException(message)