package com.hirauchi.training.fragment

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.training.common.Constants
import com.hirauchi.training.model.Record
import com.hirauchi.training.ui.AddOrEditRecordFragmentUI
import org.jetbrains.anko.AnkoContext

class AddOrEditRecordFragment : Fragment() {

    interface OnAddOrEditRecordListener {
        fun clickedImage()
    }

    lateinit var mContext: Context
    private var mListener: OnAddOrEditRecordListener? = null
    private val mUI = AddOrEditRecordFragmentUI()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mListener = context as? OnAddOrEditRecordListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            mUI.mTrainingId = it.getInt(Constants.KEY_TRAINING_ID)

            (it.getSerializable(Constants.KEY_RECORD) as? Record)?.let {
                mUI.setUpEditView(it)
            }
        }
    }

    fun clickedImage() {
        mListener?.clickedImage()
    }

    fun setImage(bitmap: Bitmap) {
        mUI.setImage(bitmap)
    }
}