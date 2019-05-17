package com.hirauchi.training.ui

import com.hirauchi.training.fragment.TrainingRecordChartFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class TrainingRecordChartFragmentUI : AnkoComponent<TrainingRecordChartFragment> {

    override fun createView(ui: AnkoContext<TrainingRecordChartFragment>) = with(ui) {
        verticalLayout {
            textView("TrainingRecordChartFragment") {
                textSize = 20F
            }
        }
    }
}