package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import org.slf4j.LoggerFactory

class EsIndexerVerticle : AbstractVerticle() {

    companion object {
        private val log = LoggerFactory.getLogger(EsIndexerVerticle::class.java)
    }

    override fun start() {

        vertx.eventBus().consumer<String>("docs-to-index", { message ->
            val document = message.body()
            log.info("going to index: $document")
        })
    }
}