package com.xhinkronize.springbatchjpa.jobparameter

import org.springframework.beans.factory.annotation.Value
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CreateDateJobParameter (
        var createDate: LocalDate
){

    @Value("#{jobParameters[createDate]}")
    fun setCreateDate(createDate: String) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        this.createDate = LocalDate.parse(createDate, formatter)
    }
}