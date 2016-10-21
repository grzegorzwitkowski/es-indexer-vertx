package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import org.slf4j.LoggerFactory

class HttpServerVerticle : AbstractVerticle() {

    companion object {
        val log = LoggerFactory.getLogger(HttpServerVerticle::class.java)
    }

    private val httpServer by lazy { vertx.createHttpServer() }

    override fun start() {
        httpServer.requestHandler({ request ->
            val p1 = request.getParam("p1")
            log.info("server received a request, p1=$p1")

            val response = request.response()
            response.statusCode = 200
            response.headers()
                    .add("Content-Type", "text/plain")
            response.end("Hi Bro! p1=$p1")

        }).listen(8080)
    }
}