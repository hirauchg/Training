package com.hirauchi.training.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.hirauchi.training.common.Constants
import com.hirauchi.training.fragment.TrainingRecordSlidePageFragment
import com.hirauchi.training.model.Record

class TrainingRecordSlideAdapter(val fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var mSlideRecordList = listOf<Record>()

    override fun getItem(p0: Int): Fragment {
        val fragment = TrainingRecordSlidePageFragment()

        val bundle = Bundle()
        bundle.putSerializable(Constants.KEY_RECORD, mSlideRecordList.get(p0))
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return mSlideRecordList.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    fun setSlideRecordList(slideRecordList: List<Record>) {
        mSlideRecordList = slideRecordList
    }
}