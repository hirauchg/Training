package com.hirauchi.training.ui

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.hirauchi.training.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TrainingListAdapterUI : AnkoComponent<ViewGroup> {

    lateinit var mCard: LinearLayout
    lateinit var mName: TextView
    lateinit var mDelete: RelativeLayout
    lateinit var mTotal: TextView
    lateinit var mMax: TextView
    lateinit var mLastDate: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            cardElevation = dip(3).toFloat()
            radius = 8F
            setContentPadding(30,22,0,22)
            lparams(width = matchParent, height = wrapContent) {
                horizontalMargin = dip(14)
                verticalMargin = dip(6)
            }

            mCard = linearLayout {
                linearLayout {
                    verticalLayout {
                        mName = textView {
                            textSize = 20F
                        }
                        view {
                            backgroundColor = R.color.black
                        }.lparams(height = 2) {
                            bottomMargin = dip(10)
                        }

                        relativeLayout {
                            textView(R.string.training_list_total) {
                                textSize = 18F
                            }
                            mTotal = textView {
                                textSize = 18F
                            }.lparams{
                                alignParentEnd()
                            }
                        }.lparams{
                            bottomMargin = dip(4)
                        }

                        relativeLayout {
                            textView(R.string.training_list_max) {
                                textSize = 18F
                            }
                            mMax = textView {
                                textSize = 18F
                            }.lparams{
                                alignParentEnd()
                            }
                        }.lparams{
                            bottomMargin = dip(4)
                        }

                        relativeLayout {
                            textView(R.string.training_list_last_date) {
                                textSize = 18F
                            }
                            mLastDate = textView {
                                textSize = 18F
                            }.lparams{
                                alignParentEnd()
                            }
                        }
                    }.lparams(width = 0, weight = 1F)

                    mDelete = relativeLayout {
                        imageView(R.drawable.ic_delete) {
                            scaleType = ImageView.ScaleType.FIT_CENTER
                        }.lparams(width = 44, height = 44) {
                            alignParentTop()
                            centerHorizontally()
                        }
                    }.lparams(width = 100, height = 44)
                }
            }
        }
    }
}
