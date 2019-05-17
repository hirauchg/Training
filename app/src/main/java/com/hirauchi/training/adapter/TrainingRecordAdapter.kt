package com.hirauchi.training.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hirauchi.training.fragment.TrainingRecordChartFragment
import com.hirauchi.training.fragment.TrainingRecordListFragment
import com.hirauchi.training.fragment.TrainingRecordSlideFragment

class TrainingRecordAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> TrainingRecordListFragment()
            1 -> TrainingRecordSlideFragment()
            else -> TrainingRecordChartFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val titles = listOf("List", "Slide", "Chart")
        return titles.get(position)
    }
}