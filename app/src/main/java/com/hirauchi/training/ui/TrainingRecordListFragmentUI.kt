package com.hirauchi.training.ui

import com.hirauchi.training.fragment.TrainingRecordListFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class TrainingRecordListFragmentUI : AnkoComponent<TrainingRecordListFragment> {

    override fun createView(ui: AnkoContext<TrainingRecordListFragment>) = with(ui) {
        verticalLayout {
            textView("TrainingRecordListFragment") {
                textSize = 20F
            }
        }
    }
}