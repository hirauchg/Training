package com.hirauchi.training.manager

import android.content.Context
import com.hirauchi.training.database.TrainingDBHelper
import com.hirauchi.training.model.Record
import org.jetbrains.anko.db.*

class RecordManager(ctx: Context) {

    private val mDB = TrainingDBHelper.getInstance(ctx)

    fun getRecordList(trainingId: Int, order: SqlOrderDirection = SqlOrderDirection.DESC): List<Record> {
        lateinit var recordList: List<Record>
        mDB.use {
            recordList = select(TrainingDBHelper.TABLE_RECORD)
                    .whereArgs("(trainingId = {trainingId})", "trainingId" to trainingId)
                    .orderBy(TrainingDBHelper.CULM_DATETIME, order)
                    .parseList(classParser())
        }
        return recordList
    }

    fun getSlideRecordList(trainingId: Int): List<Record> {
        lateinit var recordList: List<Record>
        mDB.use {
            recordList = select(TrainingDBHelper.TABLE_RECORD)
                    .whereArgs("(imagePath is not null) and (trainingId = {trainingId})", "trainingId" to trainingId)
                    .orderBy(TrainingDBHelper.CULM_DATETIME, SqlOrderDirection.DESC)
                    .parseList(classParser())
        }
        return recordList
    }

    fun addRecord(trainingId: Int, count: Int, imagePath: String?, commnet: String?) {
        mDB.use {
            insert(TrainingDBHelper.TABLE_RECORD,
                    TrainingDBHelper.CULM_TRAINING_ID to trainingId,
                    TrainingDBHelper.CULM_COUNT to count,
                    TrainingDBHelper.CULM_IMAGE_PATH to imagePath,
                    TrainingDBHelper.CULM_COMMENT to commnet,
                    TrainingDBHelper.CULM_DATETIME to System.currentTimeMillis())
        }
    }

    fun editRecord(id: Int, count: Int, imagePath: String?, commnet: String?) {
        mDB.use {
            update(TrainingDBHelper.TABLE_RECORD,
                    TrainingDBHelper.CULM_COUNT to count,
                    TrainingDBHelper.CULM_IMAGE_PATH to imagePath,
                    TrainingDBHelper.CULM_COMMENT to commnet)
                    .whereSimple(TrainingDBHelper.CULM_ID + " = ?", id.toString()).exec()
        }
    }

    fun deleteRecord(id: Int) {
        mDB.use {
            delete(TrainingDBHelper.TABLE_RECORD, TrainingDBHelper.CULM_ID + " = ?", arrayOf(id.toString()))
        }
    }
}