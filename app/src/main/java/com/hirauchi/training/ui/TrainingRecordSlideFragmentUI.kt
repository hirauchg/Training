package com.hirauchi.training.ui

import com.hirauchi.training.R
import com.hirauchi.training.fragment.TrainingRecordSlideFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.viewPager

class TrainingRecordSlideFragmentUI : AnkoComponent<TrainingRecordSlideFragment> {

    override fun createView(ui: AnkoContext<TrainingRecordSlideFragment>) = with(ui) {
        verticalLayout {
            viewPager {
                id = R.id.SlideViewPager
            }
        }
    }
}