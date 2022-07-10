package com.alphabooks.commons.rest

import zhttp.http.Request

import java.util.UUID

extension (request: Request)
  def getTraceId: String = request.header("X-Trace-ID").map { _._2.toString }.getOrElse { UUID.randomUUID().toString }
