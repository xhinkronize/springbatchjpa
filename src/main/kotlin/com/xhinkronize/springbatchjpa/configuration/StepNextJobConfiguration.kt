package com.xhinkronize.springbatchjpa.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StepNextJobConfiguration (
        val jobBuilderFactory: JobBuilderFactory,
        val stepBuilderFactory: StepBuilderFactory
) {

    val LOGGER: Logger = LoggerFactory.getLogger(StepNextJobConfiguration::class.java)

    @Bean
    fun stepNextJob() : Job {
        return jobBuilderFactory.get("StepNextJob")
                .start(step1())
                .next(step2())
                .next(step3())
                .build()
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory.get("step1")
                .tasklet({ contribution, chunkContext ->
                    LOGGER.info(" >>> This is Step1")
                    return@tasklet RepeatStatus.FINISHED
                })
                .build()
    }

    @Bean
    fun step2(): Step {
        return stepBuilderFactory.get("step2")
                .tasklet({ contribution, chunkContext ->
                    LOGGER.info(" >>> This is Step2")
                    return@tasklet RepeatStatus.FINISHED
                })
                .build()
    }

    @Bean
    fun step3(): Step {
        return stepBuilderFactory.get("step3")
                .tasklet({ contribution, chunkContext ->
                    LOGGER.info(" >>> This is Step3")
                    return@tasklet RepeatStatus.FINISHED
                })
                .build()
    }
}