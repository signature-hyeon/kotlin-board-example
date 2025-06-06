package org.signature.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinBoardExampleApplication

fun main(args: Array<String>) {
    runApplication<KotlinBoardExampleApplication>(*args)
}
