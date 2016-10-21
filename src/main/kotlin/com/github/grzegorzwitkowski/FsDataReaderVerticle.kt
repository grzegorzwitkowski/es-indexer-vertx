package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import io.vertx.core.file.OpenOptions
import io.vertx.core.json.Json
import io.vertx.core.parsetools.RecordParser
import org.slf4j.LoggerFactory

class FsDataReaderVerticle : AbstractVerticle() {

    companion object {
        private val log = LoggerFactory.getLogger(FsDataReaderVerticle::class.java)
    }

    override fun start() {

        vertx.fileSystem().readFile("users.csv", { result ->
            if (result.succeeded()) {
                val buffer = result.result()
                val lineParser = RecordParser.newDelimited("\n", { line ->
                    log.info("parsed line: $line")
                    val user = User.fromCsv(line.getString(0, line.length()))
                    vertx.eventBus().send("docs-to-index", Json.encode(user))
                })
                lineParser.handle(buffer)
            }
        })

        vertx.fileSystem().open("users.csv", OpenOptions(), { result ->
            if (result.succeeded()) {
                val file = result.result()


        }
        })
    }
}
