package com.hirauchi.training.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.training.R
import com.hirauchi.training.common.Constants
import com.hirauchi.training.model.Record
import com.hirauchi.training.ui.TrainingRecordSlidePageFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.image
import org.jetbrains.anko.imageBitmap
import java.text.SimpleDateFormat
import java.util.*

class TrainingRecordSlidePageFragment : Fragment() {

    private val mUI = TrainingRecordSlidePageFragmentUI()
    private var mBitmap: Bitmap? = null

    lateinit var mContext: Context
    lateinit var mRecord: Record

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context){
        mContext = context
        super.onAttach(context)
    }

    override fun onDestroyView() {
        mBitmap?.recycle()
        mBitmap = null
        mUI.mImage.image = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mRecord = arguments?.getSerializable(Constants.KEY_RECORD) as Record
        val ops = BitmapFactory.Options()
        ops.inSampleSize = 2
        mBitmap = BitmapFactory.decodeFile(mRecord.imagePath, ops)
        mUI.mImage.imageBitmap = mBitmap
        mUI.mDate.text = SimpleDateFormat(mContext.getString(R.string.record_slide_date_format), Locale.US).format(mRecord.dateTime)
        super.onViewCreated(view, savedInstanceState)
    }
}