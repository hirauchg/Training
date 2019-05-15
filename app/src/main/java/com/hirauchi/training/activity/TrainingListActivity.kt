package com.hirauchi.training.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.hirauchi.training.R
import com.hirauchi.training.fragment.TrainingListFragment
import org.jetbrains.anko.toast

class TrainingListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.Container, TrainingListFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_training_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_add_training -> toast(R.string.training_list_menu_add_training)
            R.id.menu_privacy -> toast(R.string.training_list_menu_privacy)
            R.id.menu_oss -> toast(R.string.training_list_menu_oss)
        }
        return super.onOptionsItemSelected(item)
    }

}
