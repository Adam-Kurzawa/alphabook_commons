package com.alphabooks.commons.rest

import io.circe.Encoder
import zhttp.http.Response

extension (response: Response.type)
  def json[T](entity: T)(using encoder: Encoder[T]): Response = Response.json(encoder(entity).noSpaces)
