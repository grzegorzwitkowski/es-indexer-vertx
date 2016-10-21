package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import org.slf4j.LoggerFactory

class MyVerticle : AbstractVerticle() {

    companion object {

        private val log = LoggerFactory.getLogger(MyVerticle::class.java)
    }

    override fun start() {
        log.info("started")
    }

    override fun stop() {
        log.info("stopped")
    }
}
