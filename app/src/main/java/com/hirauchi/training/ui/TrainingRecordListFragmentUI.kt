package com.hirauchi.training.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hirauchi.training.fragment.TrainingRecordListFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TrainingRecordListFragmentUI : AnkoComponent<TrainingRecordListFragment> {

    lateinit var mRecyclerView: RecyclerView

    override fun createView(ui: AnkoContext<TrainingRecordListFragment>) = with(ui) {
        verticalLayout {
            mRecyclerView = recyclerView {
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
            }.lparams(width = matchParent) {
                topMargin = dip(7)
            }
        }
    }
}