package com.alphabooks.commons.rest

import io.circe.*
import io.circe.generic.semiauto.*
import io.circe.parser.*
import io.circe.syntax.*

final case class HateoasResponse[T](
    entity: T,
    links: List[HateoasResponse.Link]
)

object HateoasResponse:

  final case class Link(
      rel: String,
      href: String,
      method: String = "GET"
  )
  object Link:
    given encoder: Encoder[Link] = deriveEncoder[Link]

  given encoder[T](using entityEncoder: Encoder[T]): Encoder[HateoasResponse[T]] = (a: HateoasResponse[T]) => {
    entityEncoder(a.entity)
      .mapObject { _.add("_links", a.links.asJson) }
  }

  def apply[T](entity: T, link: Link): HateoasResponse[T] = HateoasResponse(
    entity = entity,
    links = List(link)
  )
