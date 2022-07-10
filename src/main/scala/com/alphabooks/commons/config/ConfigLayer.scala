package com.alphabooks.commons.config

import zio.*
import zio.config.*
import zio.config.magnolia.{Descriptor, descriptor}
import zio.config.typesafe.TypesafeConfigSource

def layerFor[T](clazz: Class[T])(using d: Descriptor[T], t: Trace, x: Tag[T]): ZLayer[Any, ReadError[String], T] = ZLayer {
  read {
    descriptor[T].from(
      TypesafeConfigSource
        .fromResourcePath
        .at(PropertyTreePath.$(clazz.getSimpleName))
    )
  }
}
