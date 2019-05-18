package com.hirauchi.training.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hirauchi.training.fragment.TrainingRecordChartFragment
import com.hirauchi.training.fragment.TrainingRecordListFragment
import com.hirauchi.training.fragment.TrainingRecordSlideFragment

class TrainingRecordAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    private var mFragmentList = listOf<Fragment>()

    override fun getItem(p0: Int): Fragment {
        return mFragmentList.get(p0)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val titles = listOf("List", "Slide", "Chart")
        return titles.get(position)
    }

    fun setFragmentList(fragmentList: List<Fragment>) {
        mFragmentList = fragmentList
    }
}