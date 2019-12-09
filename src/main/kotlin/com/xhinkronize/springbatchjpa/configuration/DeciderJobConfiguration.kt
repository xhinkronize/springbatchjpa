package com.xhinkronize.springbatchjpa.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.job.flow.FlowExecutionStatus
import org.springframework.batch.core.job.flow.JobExecutionDecider
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class DeciderJobConfiguration (
        val jobBuilderFactory: JobBuilderFactory,
        val stepBuilderFactory: StepBuilderFactory
) {

    val LOGGER: Logger = LoggerFactory.getLogger(DeciderJobConfiguration::class.java)

    @Bean
    fun deciderJob(): Job {
        return jobBuilderFactory.get("deciderJob")
                .start(startStep())
                .next(decider()) // 홀수 | 짝수 구분
                .from(decider()) // decider의 상태가
                    .on("ODD") // ODD라면
                    .to(oddStep()) // oddStep로 간다.
                .from(decider()) // decider의 상태가
                    .on("EVEN") // ODD라면
                    .to(evenStep()) // evenStep로 간다.
                .end() // builder 종료
                .build()
    }

    @Bean
    fun startStep(): Step {
        return stepBuilderFactory.get("startStep")
                .tasklet({contribution, chunkContext ->
                    LOGGER.info(">>>>> Start!")
                    return@tasklet RepeatStatus.FINISHED
                })
                .build()
    }

    @Bean
    fun evenStep(): Step {
        return stepBuilderFactory.get("evenStep")
                .tasklet({contribution, chunkContext ->
                    LOGGER.info(">>>>> 짝수입니다.")
                    return@tasklet RepeatStatus.FINISHED
                })
                .build()
    }

    @Bean
    fun oddStep(): Step {
        return stepBuilderFactory.get("oddStep")
                .tasklet({contribution, chunkContext ->
                    LOGGER.info(">>>>> 홀수입니다.")
                    return@tasklet RepeatStatus.FINISHED
                })
                .build()
    }

    @Bean
    fun decider(): JobExecutionDecider {
        return OddDecider()
    }

    class OddDecider: JobExecutionDecider {
        override fun decide(jobExecution: JobExecution, stepExecution: StepExecution?): FlowExecutionStatus {
            val rand = Random()

            var randomNumber: Int = rand.nextInt(50) + 1

            if(randomNumber % 2 == 0) {
                return FlowExecutionStatus("EVEN")
            } else {
                return FlowExecutionStatus("ODD")
            }
        }
    }

}

