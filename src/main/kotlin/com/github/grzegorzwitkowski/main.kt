package com.github.grzegorzwitkowski

import io.vertx.core.Vertx

fun main(args: Array<String>) {

    System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory")

    val vertx = Vertx.vertx()

    vertx.deployVerticle(MyVerticle())

    vertx.deployVerticle(MsgReceiverVerticle("R1"))
    vertx.deployVerticle(MsgReceiverVerticle("R2"))
    vertx.deployVerticle(MsgSenderVerticle("S1"))
}

