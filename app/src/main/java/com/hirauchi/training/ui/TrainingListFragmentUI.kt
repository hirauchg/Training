package com.hirauchi.training.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hirauchi.training.R
import com.hirauchi.training.fragment.TrainingListFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TrainingListFragmentUI : AnkoComponent<TrainingListFragment> {

    lateinit var mRecyclerView: RecyclerView

    override fun createView(ui: AnkoContext<TrainingListFragment>) = with(ui) {
        verticalLayout {
            mRecyclerView = recyclerView {
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
            }.lparams(width = matchParent, height = matchParent) {
                topMargin = dip(8)
            }
        }
    }
}