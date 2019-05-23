package com.hirauchi.training.ui

import android.support.v4.content.ContextCompat
import com.hirauchi.training.R
import com.hirauchi.training.activity.BaseActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.verticalLayout

class BaseActivityUI : AnkoComponent<BaseActivity> {

    override fun createView(ui: AnkoContext<BaseActivity>) = with(ui) {
        verticalLayout {
            backgroundColor = ContextCompat.getColor(ctx, R.color.trainingListBG)
            id = R.id.Container
        }
    }
}