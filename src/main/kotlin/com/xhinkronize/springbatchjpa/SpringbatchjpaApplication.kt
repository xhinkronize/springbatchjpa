package com.xhinkronize.springbatchjpa

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableBatchProcessing
@SpringBootApplication
class SpringbatchjpaApplication

fun main(args: Array<String>) {
	runApplication<SpringbatchjpaApplication>(*args)
}

