package com.hirauchi.training.ui

import com.hirauchi.training.fragment.TrainingListFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class TrainingListFragmentUI : AnkoComponent<TrainingListFragment> {
    override fun createView(ui: AnkoContext<TrainingListFragment>) = with(ui) {
        verticalLayout {
            textView("Hello World!!")
        }
    }
}