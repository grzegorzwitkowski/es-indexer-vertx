package com.github.grzegorzwitkowski

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.eventbus.EventBus
import io.vertx.core.http.HttpClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class ApplicationConfig {

    @Autowired
    lateinit var verticles: List<AbstractVerticle>

    @Autowired
    lateinit var vertx: Vertx

    @PostConstruct
    open fun setSystemProperties() {
        System.setProperty(
                "vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory"
        )
    }

    @PostConstruct
    open fun deployVerticles() {
        verticles.forEach { vertx.deployVerticle(it) }
    }

    @Bean
    open fun vertx(): Vertx {
        return Vertx.vertx()
    }

    @Bean
    open fun httpClient(vertx: Vertx): HttpClient {
        return vertx.createHttpClient()
    }

    @Bean
    open fun eventBus(vertx: Vertx): EventBus {
        return vertx.eventBus()
    }
}
