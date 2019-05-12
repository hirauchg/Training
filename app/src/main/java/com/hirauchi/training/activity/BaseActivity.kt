package com.hirauchi.training.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hirauchi.training.ui.BaseActivityUI
import org.jetbrains.anko.setContentView

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseActivityUI().setContentView(this)
    }
}
