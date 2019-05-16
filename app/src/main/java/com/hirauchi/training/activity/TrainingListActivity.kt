package com.hirauchi.training.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.hirauchi.training.R
import com.hirauchi.training.fragment.TrainingListFragment
import org.jetbrains.anko.*

class TrainingListActivity : BaseActivity() {

    val mTrainingListFragment = TrainingListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportFragmentManager.beginTransaction().replace(R.id.Container, mTrainingListFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_training_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_add_training -> mTrainingListFragment.showTrainingAlert()
            R.id.menu_app_info -> startActivity<AppInfoActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}
