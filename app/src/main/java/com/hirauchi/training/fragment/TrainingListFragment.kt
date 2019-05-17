package com.hirauchi.training.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.hirauchi.training.R
import com.hirauchi.training.activity.TrainingRecordActivity
import com.hirauchi.training.adapter.TrainingListAdapter
import com.hirauchi.training.model.TrainingCard
import com.hirauchi.training.ui.TrainingListFragmentUI
import com.hirauchi.training.manager.RecordManager
import com.hirauchi.training.manager.TrainingManager
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert

class TrainingListFragment : Fragment(), TrainingListAdapter.TrainingListAdapterListener {

    private val mUI = TrainingListFragmentUI()
    private var mCardList = arrayListOf<TrainingCard>()

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

        loadList()

        mUI.mRecyclerView.adapter = mAdapter
    }

    fun loadList() {
        mCardList.clear()

        val trainingList = TrainingManager(mContext).getTrainingList()

        for (training in trainingList) {
            val recordList = RecordManager(mContext).getRecordList(training.id)

            var total = 0
            var max = 0
            for (record in recordList) {
                total += record.count
                if (max < record.count) max = record.count
            }

            var lastDate = 0L
            if (recordList.isNotEmpty()) {
                lastDate = recordList.first().dateTime
            }

            val trainingCard = TrainingCard(training.id, training.name, total, max, lastDate)
            mCardList.add(trainingCard)
        }

        mAdapter.setItemList(mCardList)
        mAdapter.notifyDataSetChanged()
    }

    fun showTrainingAlert(trainingCard: TrainingCard? = null) {
        lateinit var trainingName: EditText
        lateinit var errorMessage: TextView

        val alertDialog = alert {
            title = if (trainingCard == null) {
                getString(R.string.training_list_dialog_add_title)
            } else {
                getString(R.string.training_list_dialog_edit_title)
            }

            customView {
                verticalLayout {
                    padding = dip(16)
                    trainingName = editText {
                        hint = getString(R.string.training_list_dialog_hint)
                        setText(trainingCard?.name ?: "")
                    }.lparams(width = matchParent)

                    errorMessage = textView(R.string.training_list_dialog_add_error) {
                        textColor = ContextCompat.getColor(mContext, R.color.red)
                        visibility = View.GONE
                    }.lparams(width = matchParent) {
                        topMargin = dip(10)
                    }
                }
            }

            yesButton {}
            noButton {}
        }.show()

        (alertDialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val str = trainingName.text.toString()
            if (str.trim().isEmpty()) {
                errorMessage.visibility = View.VISIBLE
            } else {
                if (trainingCard == null) {
                    TrainingManager(mContext).addTraining(str)
                } else {
                    TrainingManager(mContext).editTraining(trainingCard.id, str)
                }

                alertDialog.dismiss()
                loadList()
            }
        }
    }

    override fun onClickCard(position: Int) {
        activity?.startActivity<TrainingRecordActivity>()
    }

    override fun onClickName(position: Int) {
        showTrainingAlert(mCardList.get(position))
    }

    override fun onClickDelete(position: Int) {
        alert {
            val trainingCard = mCardList.get(position)
            title = getString(R.string.training_list_dialog_delete, trainingCard.name)

            yesButton {
                TrainingManager(this@TrainingListFragment.mContext).deleteTraining(trainingCard.id)
                loadList()
            }
            noButton {}
        }.show()
    }
}
