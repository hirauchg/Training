package com.hirauchi.training.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.hirauchi.training.R
import com.hirauchi.training.adapter.TrainingRecordAdapter
import com.hirauchi.training.common.Constants
import com.hirauchi.training.fragment.TrainingRecordChartFragment
import com.hirauchi.training.fragment.TrainingRecordListFragment
import com.hirauchi.training.fragment.TrainingRecordSlideFragment
import com.hirauchi.training.ui.TrainingRecordActivityUI
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivityForResult

class TrainingRecordActivity : BaseActivity() {

    private val mUI = TrainingRecordActivityUI()
    private val mListFragment = TrainingRecordListFragment()
    private val mSlideFragment = TrainingRecordSlideFragment()
    private val mChartFragment = TrainingRecordChartFragment()

    lateinit var mAdapter: TrainingRecordAdapter
    var mTrainingId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUI.setContentView(this)

        mTrainingId = intent.getIntExtra(Constants.KEY_TRAINING_ID, 0)

        mAdapter = TrainingRecordAdapter(supportFragmentManager)
        mAdapter.setFragmentList(listOf(mListFragment, mSlideFragment, mChartFragment))
        val viewPager = findViewById<ViewPager>(R.id.RecordViewPager)
        viewPager.adapter = mAdapter
        mUI.mTabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_record, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_add_record -> startActivityForResult<AddOrEditRecordActivity>(Constants.REQUEST_CODE_ADD_RECORD, Constants.KEY_TRAINING_ID to mTrainingId)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK)
            reloadFragments()
        }
    }

    fun reloadFragments() {
        mListFragment.reload()
        mSlideFragment.reload()
//            mChartFragment.reload()
    }
}