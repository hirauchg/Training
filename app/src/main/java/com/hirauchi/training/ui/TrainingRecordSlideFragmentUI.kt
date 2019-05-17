package com.hirauchi.training.ui

import com.hirauchi.training.fragment.TrainingRecordSlideFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class TrainingRecordSlideFragmentUI : AnkoComponent<TrainingRecordSlideFragment> {

    override fun createView(ui: AnkoContext<TrainingRecordSlideFragment>) = with(ui) {
        verticalLayout {
            textView("TrainingRecordSlideFragment") {
                textSize = 20F
            }
        }
    }
}