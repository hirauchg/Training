package com.hirauchi.training.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.hirauchi.training.R
import com.hirauchi.training.adapter.TrainingRecordAdapter
import com.hirauchi.training.ui.TrainingRecordActivityUI
import org.jetbrains.anko.setContentView

class TrainingRecordActivity : BaseActivity() {

    private val mUI = TrainingRecordActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUI.setContentView(this)

        val adapter = TrainingRecordAdapter(supportFragmentManager)
        val viewPager = findViewById<ViewPager>(R.id.ViewPager)
        viewPager.adapter = adapter
        mUI.mTabLayout.setupWithViewPager(viewPager)
    }
}