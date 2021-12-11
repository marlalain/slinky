package com.pauloelienay.slinky.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class EmptyPatchRequestException(message: String = "Empty PATCH request") : RuntimeException(message)