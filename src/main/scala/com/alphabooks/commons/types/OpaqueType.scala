package com.alphabooks.commons.types

abstract class OpaqueType[F, T](using tFromF: F =:= T, fFromT: T =:= F):
  def apply(f: F): T = tFromF(f)
  def unapply(t: T): F = fFromT(t)

  given Conversion[T, F] = fFromT(_)

  type SuperType = F
