package com.alphabooks.commons.domain.util

import com.alphabooks.commons.types.OpaqueType

final case class Page(
    number: Page.Number,
    size: Page.Size
)

object Page:
  val Default: Page = Page(Number(0), Size(30))

  opaque type Number = Int
  object Number extends OpaqueType[Int, Number]

  opaque type Size = Int
  object Size extends OpaqueType[Int, Size]
