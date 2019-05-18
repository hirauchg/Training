package com.hirauchi.training.fragment

import android.content.Context
import android.os.Bundle
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

class TrainingRecordSlideFragment : Fragment() {

    private val mUI = TrainingRecordSlideFragmentUI()
    private var mSlideRecordList = listOf<Record>()

    lateinit var mActivity: TrainingRecordActivity
    lateinit var mAdapter: TrainingRecordSlideAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context){
        mActivity = activity as TrainingRecordActivity
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val slideRecordList = RecordManager(mActivity).getSlideRecordList(mActivity.mTrainingId)

        mAdapter = TrainingRecordSlideAdapter(childFragmentManager)
        mAdapter.setSlideRecordList(slideRecordList)

        val viewPager = view.findViewById<ViewPager>(R.id.SlideViewPager)
        viewPager.adapter = mAdapter
        super.onViewCreated(view, savedInstanceState)
    }

    fun reload() {
        mSlideRecordList = RecordManager(mActivity).getSlideRecordList(mActivity.mTrainingId)
        mAdapter.setSlideRecordList(mSlideRecordList)
        mAdapter.notifyDataSetChanged()
    }
}