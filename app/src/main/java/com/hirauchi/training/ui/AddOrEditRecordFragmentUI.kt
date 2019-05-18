package com.hirauchi.training.ui

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.hirauchi.training.R
import com.hirauchi.training.fragment.AddOrEditRecordFragment
import com.hirauchi.training.manager.RecordManager
import com.hirauchi.training.model.Record
import org.jetbrains.anko.*
import java.io.File
import java.io.FileOutputStream

class AddOrEditRecordFragmentUI : AnkoComponent<AddOrEditRecordFragment> {

    private val JPG = ".jpg"
    private val MEDIA_TYPE = "image/jpeg"
    private val SAVE_DIRECTORY = "/training"

    lateinit var mCount: EditText
    lateinit var mImage: ImageView
    lateinit var mComment: EditText
    lateinit var mButton: Button

    var mTrainingId: Int = 0
    private var mRecord: Record? = null
    private var mBitmap: Bitmap? = null
    private var mFilePath: String? = null

    override fun createView(ui: AnkoContext<AddOrEditRecordFragment>) = with(ui) {
        scrollView {
            lparams(width = matchParent, height = matchParent)

            setOnTouchListener { _, _ ->
                val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(windowToken, 0)
            }

            verticalLayout {
                padding = dip(16)

                textView(R.string.add_or_edit_record_count) {
                    textSize = 18F
                }

                relativeLayout {
                    mCount = editText {
                        textSize = 18F
                        inputType = InputType.TYPE_CLASS_NUMBER
                        textAlignment = LinearLayout.TEXT_ALIGNMENT_TEXT_END
                        requestFocus()
                    }.lparams(width = matchParent) {
                        marginEnd = dip(25)
                    }

                    textView(R.string.add_or_edit_record_count_unit) {
                        textSize = 18F
                        translationY = 15F
                    }.lparams(width = 40) {
                        alignParentEnd()
                    }
                }.lparams {
                    horizontalMargin = dip(15)
                    bottomMargin = dip(30)
                }

                textView(R.string.add_or_edit_record_image) {
                    textSize = 18F
                }.lparams {
                    bottomMargin = dip(15)
                }

                mImage = imageView {
                    backgroundColor = ContextCompat.getColor(ctx, R.color.black)
                    scaleType = ImageView.ScaleType.FIT_CENTER

                    setOnClickListener {
                        ui.owner.clickedImage()
                    }
                }.lparams(width = 300, height = 400) {
                    gravity = Gravity.CENTER_HORIZONTAL
                    bottomMargin = dip(30)
                }

                textView(R.string.add_or_edit_record_comment) {
                    textSize = 18F
                }

                mComment = editText {
                    setSingleLine(false)
                    imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION
                }.lparams(width = matchParent) {
                    horizontalMargin = dip(15)
                    bottomMargin = dip(30)
                }

                mButton = button(R.string.add_or_edit_record_add_button) {
                    setOnClickListener {
                        val count = mCount.text.toString()
                        if(count.trim().isEmpty()) {
                            alert(R.string.add_or_edit_empty_count){
                                yesButton {}
                            }.show()
                            return@setOnClickListener
                        }

                        val comment = mComment.text.toString()

                        ui.owner.activity?.let {
                            if (mBitmap != null) {
                                mFilePath = saveImage(it)
                            }

                            mRecord?.let {
                                RecordManager(context).editRecord(it.id, count.toInt(), mFilePath, comment)
                            } ?: RecordManager(context).addRecord(mTrainingId, count.toInt(), mFilePath, comment)

                            it.setResult(Activity.RESULT_OK)
                            it.finish()
                        }
                    }
                }.lparams {
                    topMargin = dip(20)
                    bottomMargin = dip(10)
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }
        }
    }

    fun setImage(bitmap: Bitmap) {
        mBitmap = bitmap
        mImage.imageBitmap = mBitmap
    }

    fun setUpEditView(record: Record) {
        mRecord = record

        mFilePath = mRecord?.imagePath?.let {
            mImage.imageBitmap = BitmapFactory.decodeFile(it)
            it
        }

        mCount.setText(mRecord?.count.toString())
        mComment.setText(mRecord?.commnet)
        mButton.setText(R.string.add_or_edit_record_edit_button)
    }

    private fun saveImage(activity: Activity): String {
        val dirPath = Environment.getExternalStorageDirectory().toString() + SAVE_DIRECTORY
        val dir = File(dirPath)

        if (!dir.exists()) {
            dir.mkdirs()
        }

        val filePath = dirPath + "/" + System.currentTimeMillis() + JPG
        val file = File(filePath)
        val ops = FileOutputStream(file)

        mBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, ops)
        ops.close()

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.MIME_TYPE, MEDIA_TYPE)
            put("_data", file.absolutePath)
        }

        activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        return filePath
    }
}