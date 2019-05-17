package com.hirauchi.training.ui

import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.hirauchi.training.R
import org.jetbrains.anko.*

class TrainingRecordListAdapterUI : AnkoComponent<ViewGroup> {

    lateinit var mContainer: LinearLayout
    lateinit var mImage: ImageView
    lateinit var mDatetime: TextView
    lateinit var mCount: TextView
    lateinit var mComment: TextView
    lateinit var mDivider: View

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        verticalLayout {
            mContainer = linearLayout {
                mImage = imageView {
                    backgroundColor = ContextCompat.getColor(ctx, R.color.black)
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = 120, height = 160) {
                    marginEnd = dip(12)
                }

                verticalLayout {
                    linearLayout {
                        mDatetime = textView().lparams(width = 120)

                        mCount = textView {
                            textAlignment = LinearLayout.TEXT_ALIGNMENT_TEXT_END
                        }.lparams(width = 0, weight = 1F)
                    }

                    view {
                        backgroundColor = ContextCompat.getColor(ctx, R.color.silver)
                    }.lparams(width = matchParent, height = 1) {
                        verticalMargin = dip(2)
                    }

                    mComment = textView()
                }
            }.lparams {
                horizontalMargin = dip(12)
                bottomMargin = dip(7)
            }

            mDivider = view {
                backgroundColor = ContextCompat.getColor(ctx, R.color.gray)
            }.lparams(width = matchParent, height = 1) {
                bottomMargin = dip(7)
            }
        }
    }
}