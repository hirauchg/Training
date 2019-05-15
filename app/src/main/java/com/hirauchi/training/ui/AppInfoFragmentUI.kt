package com.hirauchi.training.ui

import android.widget.ListView
import com.hirauchi.training.fragment.AppInfoFragment
import org.jetbrains.anko.*

class AppInfoFragmentUI : AnkoComponent<AppInfoFragment> {

    lateinit var mListView: ListView

    override fun createView(ui: AnkoContext<AppInfoFragment>) = with(ui) {
        verticalLayout {
            mListView = listView()
        }
    }
}