package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.Json
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths

class FsDataReaderVerticle : AbstractVerticle() {

    companion object {
        private val log = LoggerFactory.getLogger(FsDataReaderVerticle::class.java)
    }

    override fun start() {

        vertx.executeBlocking<Void>({ futureResult ->
            Files.lines(Paths.get("users.csv")).forEach { csvLine ->
                val user = User.fromCsv(csvLine)
                val json = Json.encode(user)
                vertx.eventBus().send("docs-to-index", json)
            }
            futureResult.complete()
        }, { futureResult ->
            log.info("finished reading users.csv file")
        })
    }
}
