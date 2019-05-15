package com.hirauchi.training.activity

import android.os.Bundle
import com.hirauchi.training.R
import com.hirauchi.training.fragment.AppInfoFragment

class AppInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.Container, AppInfoFragment()).commit()
    }

}