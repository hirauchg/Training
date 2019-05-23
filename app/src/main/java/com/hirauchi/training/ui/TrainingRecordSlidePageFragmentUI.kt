package com.hirauchi.training.ui

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.hirauchi.training.R
import com.hirauchi.training.fragment.TrainingRecordSlidePageFragment
import org.jetbrains.anko.*

class TrainingRecordSlidePageFragmentUI : AnkoComponent<TrainingRecordSlidePageFragment> {

    lateinit var mImage: ImageView
    lateinit var mDate: TextView
    lateinit var mController: LinearLayout
    lateinit var mStart: ImageView
    lateinit var mPrev: ImageView
    lateinit var mNext: ImageView
    lateinit var mLast: ImageView

    override fun createView(ui: AnkoContext<TrainingRecordSlidePageFragment>): View = with(ui) {
        relativeLayout {
            mImage = imageView {
                backgroundColor = ContextCompat.getColor(ctx, R.color.black)
                scaleType = ImageView.ScaleType.CENTER_INSIDE
            }.lparams(width = matchParent, height = matchParent)

            mDate = textView {
                backgroundColor = ContextCompat.getColor(ctx, R.color.imageTextBG)
                textColor = ContextCompat.getColor(ctx, R.color.white)
                gravity = Gravity.CENTER_HORIZONTAL
                setPadding(5, 0, 5, 0)
                visibility = View.GONE
            }.lparams(width = wrapContent) {
                centerHorizontally()
                topMargin = dip(10)
            }

            mController = linearLayout {
                visibility = View.GONE
                backgroundColor = ContextCompat.getColor(ctx, R.color.imageTextBG)
                setPadding(20, 30, 20, 30)

                mStart = imageView(R.drawable.ic_start) {
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = 0, weight = 1F)
                mPrev = imageView(R.drawable.ic_prev) {
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = 0, weight = 1F)
                mNext = imageView(R.drawable.ic_next) {
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = 0, weight = 1F)
                mLast = imageView(R.drawable.ic_last) {
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = 0, weight = 1F)
            }.lparams(width = matchParent, height = 160) {
                alignParentBottom()
            }
        }
    }
}