package com.vizurd.tutorial

import mu.KotlinLogging
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TutorialApplication

private val logger = KotlinLogging.logger {  }

fun main(args: Array<String>) {
    logger.info { "Use KotlinLogging" }
    SpringApplication.run(TutorialApplication::class.java, *args)
}