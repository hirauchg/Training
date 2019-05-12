package com.hirauchi.training.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class TrainingDBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "db_1"
        const val DB_VERSION = 1

        const val TABLE_TRAINING = "table_training"
        const val TABLE_RECORD = "table_record"

        const val CULM_ID = "id"
        const val CULM_NAME = "name"

        const val CULM_TRAINING_ID = "trainingId"
        const val CULM_COUNT = "count"
        const val CULM_IMAGE_PATH = "imagePath"
        const val CULM_COMMENT = "comment"
        const val CULM_DATETIME = "datetime"

        private var instance: TrainingDBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): TrainingDBHelper {
            if (instance == null) {
                instance = TrainingDBHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(TABLE_TRAINING, true,
                CULM_ID to INTEGER + PRIMARY_KEY + UNIQUE,
                CULM_NAME to TEXT)

        db.createTable(TABLE_RECORD, true,
                CULM_ID to INTEGER + PRIMARY_KEY + UNIQUE,
                CULM_TRAINING_ID to INTEGER,
                CULM_COUNT to INTEGER,
                CULM_IMAGE_PATH to BLOB,
                CULM_COMMENT to TEXT,
                CULM_DATETIME to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}
