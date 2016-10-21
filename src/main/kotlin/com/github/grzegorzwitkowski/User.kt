package com.github.grzegorzwitkowski

import io.vertx.core.json.JsonObject

data class User(val firstName: String, val lastName: String, val age: Int) {

    companion object {

        fun fromCsv(csvLine: String): User {
            val columns = csvLine.split(",")
            return User(
                    firstName = columns[0],
                    lastName = columns[1],
                    age = columns[2].trim().toInt()
            )
        }
    }
}