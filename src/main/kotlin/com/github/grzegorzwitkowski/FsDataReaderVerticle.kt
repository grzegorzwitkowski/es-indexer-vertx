package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.EventBus
import io.vertx.core.json.Json
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths

@Component
class FsDataReaderVerticle(private val eventBus: EventBus) : AbstractVerticle() {

    companion object {
        private val log = LoggerFactory.getLogger(FsDataReaderVerticle::class.java)
    }

    override fun start() {

        vertx.executeBlocking<Void>({ readFileAndSendLinesToIndexer(it) }, { logInCaseOfFailure() })
    }

    private fun readFileAndSendLinesToIndexer(futureResult: Future<Void>) {

        Files.lines(Paths.get("users.csv")).forEach { csvLine ->
            val userAsJson = Json.encode(User.fromCsv(csvLine))
            eventBus.send("docs-to-index", userAsJson)
        }
        futureResult.complete()
    }

    private fun logInCaseOfFailure() {
        log.info("finished reading users.csv file")
    }
}
