package com.alphabooks.commons.rest

import io.circe.*
import io.circe.generic.semiauto.*
import io.circe.parser.*
import io.circe.syntax.*

final case class ErrorResponse(
    code: String,
    message: String
)

object ErrorResponse:
  given encoder: Encoder[ErrorResponse] = deriveEncoder[ErrorResponse]

  def apply(throwable: Throwable): ErrorResponse = ErrorResponse(
    code = "E-001",
    message = throwable.getMessage
  )

  def apply(any: Any): ErrorResponse = ErrorResponse(
    code = "E-002",
    message = any.toString
  )
