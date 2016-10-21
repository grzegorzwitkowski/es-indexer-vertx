package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import org.slf4j.LoggerFactory

class HttpClientVerticle : AbstractVerticle() {

    companion object {
        val log = LoggerFactory.getLogger(HttpClientVerticle::class.java)
    }

    override fun start() {

        val httpClient = vertx.createHttpClient()
        httpClient.getNow(8080, "localhost", "/?p1=value1", { response ->
            response.bodyHandler { buffer ->
                val responseText = buffer.getString(0, buffer.length())
                log.info("client received a response: $responseText")
            }
        })
    }
}
