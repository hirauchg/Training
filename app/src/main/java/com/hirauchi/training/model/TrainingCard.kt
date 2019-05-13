package com.hirauchi.training.model

data class TrainingCard (
        val id: Int,
        val name: String,
        val total: Int,
        val max: Int,
        val lastDate: Long?
)