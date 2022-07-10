package com.alphabooks.commons.rest

import io.circe.*
import io.circe.generic.semiauto.*
import io.circe.parser.*
import io.circe.syntax.*

final case class PagedResponse[+T](
    content: List[T],
    previousPageNumber: Option[Int],
    pageNumber: Int,
    nextPageNumber: Option[Int]
) {
  val contentSize: Int = content.size
}

object PagedResponse:
  private def basePagedResponseEncoder[T](using dataEncoder: Encoder[T]): Encoder[PagedResponse[T]] = deriveEncoder[PagedResponse[T]]

  given encoder[T](using dataEncoder: Encoder[T]): Encoder[PagedResponse[T]] = (a: PagedResponse[T]) => {
    basePagedResponseEncoder(a)
      .mapObject { _.add("contentSize", Json.fromInt(a.contentSize)) }
  }
