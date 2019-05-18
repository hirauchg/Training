package com.hirauchi.training.ui

import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import com.hirauchi.training.R
import com.hirauchi.training.fragment.TrainingRecordSlideFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.viewPager

class TrainingRecordSlideFragmentUI : AnkoComponent<TrainingRecordSlideFragment> {

    lateinit var mImageChange: ImageView

    override fun createView(ui: AnkoContext<TrainingRecordSlideFragment>) = with(ui) {
        relativeLayout {
            verticalLayout {
                viewPager {
                    id = R.id.SlideViewPager
                }

            }.lparams(width = matchParent, height = matchParent)

            mImageChange = imageView {
                backgroundColor = ContextCompat.getColor(ctx, R.color.black)
                scaleType = ImageView.ScaleType.FIT_CENTER
                visibility = View.GONE
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}