package com.xhinkronize.springbatchjpa.jobparameter

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManagerFactory

@Configuration
class JobParameterExtendsConfiguration (
        val jobBuilderFactory: JobBuilderFactory,
        val stepBuilderFactory: StepBuilderFactory,
        val entityManagerFactory: EntityManagerFactory,
        val jobParameter: CreateDateJobParameter
){

    @Bean(JOB_NAME.toString() + "jobParameter")
    @JobScope
    fun jobParameter(): CreateDateJobParameter? {
        return CreateDateJobParameter()
    }

}