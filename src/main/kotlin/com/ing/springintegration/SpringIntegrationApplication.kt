package com.ing.springintegration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringIntegrationApplication

fun main(args: Array<String>) {
    runApplication<SpringIntegrationApplication>(*args)
}
