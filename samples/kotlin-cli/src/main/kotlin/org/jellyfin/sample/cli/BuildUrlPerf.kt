package org.jellyfin.sample.cli

import kotlin.time.Duration
import java.util.UUID
import kotlin.time.DurationUnit
import org.jellyfin.sdk.api.client.util.UrlBuilder
import kotlin.time.measureTime

fun main(args: Array<String>) {
	buildUrlPerf()
	buildPathPerf()
}

private const val ITERATIONS = 1000000

private fun buildUrlPerf() {
	val baseUrl = "https://demo.jellyfin.org/stable/"
	val queryParameters = mutableMapOf<String, String>()
	for (i in 1..100) {
		queryParameters["queryParameters$i"] = i.toString()
	}

	val duration = measureTime {
		for (i in 1..ITERATIONS) {
			UrlBuilder.buildUrl(baseUrl = baseUrl, queryParameters = queryParameters)
		}
	}

	println("buildUrlPerf: $duration")
}

private fun buildPathPerf() {
	var path = "/test"
	val parameters = mutableMapOf<String, String>()
	for (i in 0..19) {
		val name = "index$i"
		path += "/$name"
		parameters[name] = UUID.randomUUID().toString()
	}

	val duration = measureTime {
		for (i in 1..ITERATIONS) {
			UrlBuilder.buildPath(path, parameters.toMap())
		}
	}

	println("buildPathPerf: $duration")
}
