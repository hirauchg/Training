package com.hirauchi.training.fragment

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.training.R
import com.hirauchi.training.activity.TrainingRecordActivity
import com.hirauchi.training.adapter.TrainingRecordSlideAdapter
import com.hirauchi.training.manager.RecordManager
import com.hirauchi.training.model.Record
import com.hirauchi.training.ui.TrainingRecordSlideFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.imageBitmap

class TrainingRecordSlideFragment : Fragment() {

    private val mUI = TrainingRecordSlideFragmentUI()
    private var mHandler: Handler? = null
    private var mRunnable: Runnable? = null
    var mSlideRecordList = listOf<Record>()

    lateinit var mActivity: TrainingRecordActivity
    lateinit var mAdapter: TrainingRecordSlideAdapter
    lateinit var mViewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context){
        mActivity = activity as TrainingRecordActivity
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAdapter = TrainingRecordSlideAdapter(childFragmentManager)
        reload()

        mViewPager = view.findViewById(R.id.SlideViewPager)
        mViewPager.adapter = mAdapter

        super.onViewCreated(view, savedInstanceState)
    }

    fun startAutoSlide() {
        if (mSlideRecordList.isEmpty()) {
            mActivity.stopAutoSlide()
            return
        }

        mUI.mImageChange.visibility = View.VISIBLE
        setImage(0)

        mHandler = Handler()
        mRunnable = object : Runnable {
            var count = 0
            override fun run() {

                if (count >= mSlideRecordList.size) {
                    Thread.sleep(500)
                    mActivity.stopAutoSlide()
                    return
                }

                setImage(count)

                count++
                mHandler?.post(mRunnable)
            }
        }
        mHandler?.post(mRunnable)
    }

    private fun setImage(count: Int) {
        val ops = BitmapFactory.Options()
        ops.inSampleSize = 4
        val bitmap = BitmapFactory.decodeFile(mSlideRecordList.get(count).imagePath, ops)
        bitmap?.let {
            mUI.mImageChange.imageBitmap = bitmap
        } ?: mUI.mImageChange.setImageResource(R.drawable.file_not_exist)
    }

    fun stopAutoSlide() {
        mHandler?.removeCallbacks(mRunnable)
        mUI.mImageChange.visibility = View.GONE
    }

    fun reload() {
        mSlideRecordList = RecordManager(mActivity).getSlideRecordList(mActivity.mTrainingId)
        mAdapter.setSlideRecordList(mSlideRecordList)
        mAdapter.notifyDataSetChanged()
    }
}