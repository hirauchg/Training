package com.hirauchi.training.manager

import android.content.Context
import com.hirauchi.training.database.TrainingDBHelper
import com.hirauchi.training.model.Training
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

class TrainingManager(ctx: Context) {

    private val mDB = TrainingDBHelper.getInstance(ctx)

    fun getTrainingList(): List<Training> {
        lateinit var trainingList: List<Training>
        mDB.use {
            trainingList = select(TrainingDBHelper.TABLE_TRAINING).parseList(classParser())
        }
        return trainingList
    }

    fun addTraining(name: String) {
        mDB.use {
            insert(TrainingDBHelper.TABLE_TRAINING, TrainingDBHelper.CULM_NAME to name)
        }
    }

    fun editTraining(id: Int, name: String) {
        mDB.use {
            update(TrainingDBHelper.TABLE_TRAINING,
                    TrainingDBHelper.CULM_NAME to name)
                    .whereSimple(TrainingDBHelper.CULM_ID + " = ?", id.toString()).exec()
        }
    }

    fun deleteTraining(id: Int) {
        mDB.use {
            delete(TrainingDBHelper.TABLE_TRAINING, TrainingDBHelper.CULM_ID + " = ?", arrayOf(id.toString()))
            delete(TrainingDBHelper.TABLE_RECORD, TrainingDBHelper.CULM_TRAINING_ID + " = ?", arrayOf(id.toString()))
        }
    }
}