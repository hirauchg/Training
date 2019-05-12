package com.hirauchi.training.ui

import com.hirauchi.training.R
import com.hirauchi.training.activity.BaseActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.verticalLayout

class BaseActivityUI : AnkoComponent<BaseActivity> {

    override fun createView(ui: AnkoContext<BaseActivity>) = with(ui) {
        verticalLayout {
            id = R.id.Container
        }
    }
}