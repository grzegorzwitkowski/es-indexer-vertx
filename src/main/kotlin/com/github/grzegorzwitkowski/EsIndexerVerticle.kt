package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import io.vertx.core.eventbus.EventBus
import io.vertx.core.http.HttpClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EsIndexerVerticle(private val eventBus: EventBus, private val httpClient: HttpClient) : AbstractVerticle() {

    companion object {
        private val log = LoggerFactory.getLogger(EsIndexerVerticle::class.java)
    }

    override fun start() {

        eventBus.consumer<String>("docs-to-index", { message ->
            val userAsJson = message.body()
            log.info("indexing document: $userAsJson")

            httpClient
                    .post(9200, "localhost", "myshop/users", { response ->
                        if (response.statusCode() != 201) {
                            log.error("Failed to index document: $userAsJson, status code: ${response.statusCode()}")
                        }
                    })
                    .putHeader("Content-Type", "application/json")
                    .end(userAsJson)
        })
    }
}
