package edu.pasudo123.study.demo.helper

import com.fasterxml.jackson.databind.ObjectMapper

object Helper

internal fun <T> T.toJson(): String = ObjectMapper().writeValueAsString(this)