package com.xhinkronize.springbatchjpa.configuration


import com.xhinkronize.springbatchjpa.SimpleJobTasklet
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SimpleJobConfiguration(
        val jobBuilderFactory: JobBuilderFactory,
        val stepBuilderFactory: StepBuilderFactory,
        var simpleJobTasklet: SimpleJobTasklet
) {

    val LOGGER:Logger = LoggerFactory.getLogger(SimpleJobConfiguration::class.java)

    /*
    @Bean
    fun simpleJob(): Job {
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep1(null))
                .next(simpleStep2(null))
                .build()
    }
     */

    @Bean
    fun simpleJob(): Job {
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep1())
                .next(simpleStep2(null))
                .build()
    }

    /*
    @Bean
    @JobScope
    fun simpleStep1(@Value("#{jobParameters[requestDate]}") requestDate: String?): Step {
        return stepBuilderFactory.get("simpleStep1")
                .tasklet({ contribution, chunkContext ->
                    LOGGER.info(">>>>>>> This is Step1")
                    LOGGER.info(">>>>>>> requestDate = {}", requestDate)
                    return@tasklet RepeatStatus.FINISHED
                })
                .build()
    }
     */

    /*
    @Bean
    @JobScope
    fun simpleStep1(@Value("#{jobParameters[requestDate]}") requestDate: String?): Step {
        return stepBuilderFactory.get("simpleStep1")
                .tasklet({ contribution, chunkContext ->
                    LOGGER.info(">>>>>>> This is Step1")
                    LOGGER.info(">>>>>>> requestDate = {}", requestDate)
                    return@tasklet RepeatStatus.FINISHED
                })
                .build()
    }
     */

    fun simpleStep1(): Step {
        LOGGER.info(">>>> definition simpleStep1")
        return stepBuilderFactory.get("simpleStep1")
                .tasklet(simpleJobTasklet)
                .build()
    }

    @Bean
    @JobScope
    fun simpleStep2(@Value("#{jobParameters[requestDate]}") requestDate : String?): Step {
        return stepBuilderFactory.get("simpleStep2")
                .tasklet({ contribution, chunkContext ->
                    LOGGER.info(">>>>>>> This is Step2")
                    LOGGER.info(">>>>>>> requestDate = {}", requestDate)
                    return@tasklet RepeatStatus.FINISHED
                })
                .build()
    }

}