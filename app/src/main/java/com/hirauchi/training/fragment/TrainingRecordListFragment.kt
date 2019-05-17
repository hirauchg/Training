package com.hirauchi.training.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.training.adapter.TrainingRecordListAdapter
import com.hirauchi.training.model.Record
import com.hirauchi.training.ui.TrainingRecordListFragmentUI
import org.jetbrains.anko.AnkoContext

class TrainingRecordListFragment : Fragment(), TrainingRecordListAdapter.TrainingRecordListAdapterListener {

    private val mUI = TrainingRecordListFragmentUI()
    private var mRecordList = arrayListOf<Record>()

    lateinit var mContext: Context
    lateinit var mAdapter: TrainingRecordListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context){
        mContext = context
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = TrainingRecordListAdapter(mContext, this)

        loadList()

        mUI.mRecyclerView.adapter = mAdapter
    }

    fun loadList() {
        mRecordList.clear()

        val record1 = Record(0, 0, 100, null, "コメント1", System.currentTimeMillis())
        val record2 = Record(1, 0, 101, "", "コメント2", System.currentTimeMillis())
        val record3 = Record(2, 0, 102, null, "コメント3", System.currentTimeMillis())
        val record4 = Record(3, 0, 103, "", "コメント4", System.currentTimeMillis())
        mRecordList = arrayListOf(record1, record2, record3, record4)

        mAdapter.setRecordList(mRecordList)
        mAdapter.notifyDataSetChanged()
    }
    override fun OnClickContainer(id: Int) {

    }
}