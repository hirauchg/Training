package com.hirauchi.training.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.training.adapter.TrainingListAdapter
import com.hirauchi.training.model.TrainingCard
import com.hirauchi.training.ui.TrainingListFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.startActivity
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

class TrainingListFragment : Fragment(), TrainingListAdapter.TrainingListAdapterListener {

    private val mUI = TrainingListFragmentUI()

    lateinit var mContext: Context
    lateinit var mAdapter: TrainingListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context){
        mContext = context
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = TrainingListAdapter(mContext, this)

        val card1 = TrainingCard(0, "腕立て", 100, 30, System.currentTimeMillis())
        val card2 = TrainingCard(1, "腹筋", 2000, 3230, 0)
        val card3 = TrainingCard(2, "スクワット", 23040, 350, 0)
        val cardList = listOf(card1, card2, card3)
        mAdapter.setItemList(cardList)

        mUI.mRecyclerView.adapter = mAdapter
    }

    override fun onClickCard(position: Int) {
        activity?.startActivity<OssLicensesMenuActivity>("title" to "オープンソースライセンス")
    }

    override fun onClickName(position: Int) {
        toast("onClickName")
    }

    override fun onClickDelete(position: Int) {
        toast("onClickDelete")
    }
}
