package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle

class MsgSenderVerticle(private val senderName: String) : AbstractVerticle() {

    override fun start() {
        vertx.eventBus().publish("an-address", "$senderName: message #1")
        vertx.eventBus().send("an-address", "$senderName: message #2")
    }
}