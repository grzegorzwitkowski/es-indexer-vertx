package com.github.grzegorzwitkowski

import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {

    val springCtx = AnnotationConfigApplicationContext("com.github.grzegorzwitkowski")
    springCtx.start()
}
