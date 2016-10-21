package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import org.slf4j.LoggerFactory

class MsgReceiverVerticle(private val receiverName: String) : AbstractVerticle() {

    companion object {

        private val log = LoggerFactory.getLogger(MsgReceiverVerticle::class.java)
    }

    override fun start() {

        vertx.eventBus().consumer<String>("an-address", { message ->
            log.info("{} received: [{}]", receiverName, message.body())
        })
    }
}
