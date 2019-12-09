package com.xhinkronize.springbatchjpa

import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * JobParameters를 사용하기 위해선 꼭 @StepScope, @JobScope로 Bean을 생성해야한다
 */
@Component
@StepScope
class SimpleJobTasklet(
        @Value("#{jobParameters[requestDate]}")
        var requestDate : String
) : Tasklet {

    val LOGGER = LoggerFactory.getLogger(SimpleJobTasklet::class.java)

    init {
        LOGGER.info(">>> TaskLet 생성")
    }

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        LOGGER.info(">>>> this is step1")
        LOGGER.info(">>>> request date = {}", requestDate)
        return RepeatStatus.FINISHED
    }

}
