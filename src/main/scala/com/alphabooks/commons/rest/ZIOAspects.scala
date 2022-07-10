package com.alphabooks.commons.rest

import io.circe.Encoder
import zhttp.http.*
import zio.*

def errorHandler[E >: Nothing <: Any](using encoder: Encoder[E]): ZIOAspect[Nothing, Any, Nothing, Any, Response, Response] = new ZIOAspect[Nothing, Any, Nothing, Any, Response, Response]:
  override def apply[R >: Nothing <: Any, E, A >: Response <: Response](zio: ZIO[R, E, A])(using trace: Trace): ZIO[R, E, A] = zio
    .catchAll {
      case error: Throwable => ZIO.logError { error.toString }.as { Response.json(ErrorResponse(error)) }
      case other            => ZIO.logError { other.toString }.as { Response.json(ErrorResponse(other)) }
    }

def traceId(request: Request): ZIOAspect[Nothing, Any, Nothing, Any, Nothing, Any] = new ZIOAspect[Nothing, Any, Nothing, Any, Nothing, Any]:
  override def apply[R >: Nothing <: Any, E >: Nothing <: Any, A >: Nothing <: Any](zio: ZIO[R, E, A])(using trace: Trace): ZIO[R, E, A] = ZIO
    .succeed { request.traceId }
    .flatMap { id => ZIO.logAnnotate("trace-id", id)(zio) }
