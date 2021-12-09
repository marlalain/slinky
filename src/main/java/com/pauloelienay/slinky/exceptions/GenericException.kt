package com.pauloelienay.slinky.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class GenericException(message: String = "Internal Server Error") : RuntimeException(message)