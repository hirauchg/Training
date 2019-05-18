package com.hirauchi.training.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.training.R
import com.hirauchi.training.activity.AddOrEditRecordActivity
import com.hirauchi.training.activity.TrainingRecordActivity
import com.hirauchi.training.adapter.TrainingRecordListAdapter
import com.hirauchi.training.common.Constants
import com.hirauchi.training.manager.RecordManager
import com.hirauchi.training.model.Record
import com.hirauchi.training.ui.TrainingRecordListFragmentUI
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert

class TrainingRecordListFragment : Fragment(), TrainingRecordListAdapter.TrainingRecordListAdapterListener {

    private val mUI = TrainingRecordListFragmentUI()
    private var mRecordList = listOf<Record>()

    lateinit var mActivity: TrainingRecordActivity
    lateinit var mAdapter: TrainingRecordListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mActivity = activity as TrainingRecordActivity
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = TrainingRecordListAdapter(mActivity, this)

        mRecordList = RecordManager(mActivity).getRecordList(mActivity.mTrainingId)
        mAdapter.setRecordList(mRecordList)

        mUI.mRecyclerView.adapter = mAdapter
    }

    fun reload() {
        mRecordList = RecordManager(mActivity).getRecordList(mActivity.mTrainingId)
        mAdapter.setRecordList(mRecordList)
        mAdapter.notifyDataSetChanged()
    }

    override fun onClickContainer(record: Record) {
        mActivity.startActivityForResult<AddOrEditRecordActivity>(Constants.REQUEST_CODE_EDIT_RECORD,Constants.KEY_TRAINING_ID to mActivity.mTrainingId, Constants.KEY_RECORD to record)
    }

    override fun onClickDelete(position: Int) {
        alert {
            title = getString(R.string.record_list_dialog_delete)

            yesButton {
                mActivity.setResult(Activity.RESULT_OK)
                RecordManager(mActivity).deleteRecord(mRecordList.get(position).id)
                reload()
            }
            noButton {}
        }.show()
    }
}