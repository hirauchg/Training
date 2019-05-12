package com.hirauchi.training.model

data class Record (
        val id: Int,
        val trainingId: Int,
        val count: Int,
        val imagePath: String?,
        val commnet: String?,
        val dateTime: Long
)