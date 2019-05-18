package com.hirauchi.training.ui

import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat.getColor
import android.view.View
import com.hirauchi.training.R
import com.hirauchi.training.activity.TrainingRecordActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class TrainingRecordActivityUI : AnkoComponent<TrainingRecordActivity> {

    lateinit var mTabLayout: TabLayout

    override fun createView(ui: AnkoContext<TrainingRecordActivity>): View = with(ui) {
        verticalLayout {
            mTabLayout = tabLayout {
                tabGravity = TabLayout.GRAVITY_FILL
                backgroundColor = getColor(ctx, R.color.tabBG)
                setTabTextColors(getColor(ctx, R.color.white), getColor(ctx, R.color.tabTextSelect))
                setSelectedTabIndicatorColor(getColor(ctx, R.color.tabTextSelect))
            }

            viewPager {
                id = R.id.RecordViewPager
            }
        }
    }
}