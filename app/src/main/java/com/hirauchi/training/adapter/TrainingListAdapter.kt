package com.hirauchi.training.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.hirauchi.training.R
import com.hirauchi.training.model.TrainingCard
import com.hirauchi.training.ui.TrainingListAdapterUI
import org.jetbrains.anko.AnkoContext
import java.text.SimpleDateFormat
import java.util.*

class TrainingListAdapter(val mContext: Context, val mListener: TrainingListAdapterListener):
        RecyclerView.Adapter<TrainingListAdapter.ViewHolder>() {

    interface TrainingListAdapterListener {
        fun onClickCard(position: Int)
        fun onClickName(position: Int)
        fun onClickDelete(position: Int)
    }

    private val mUI = TrainingListAdapterUI()
    private var mItemList = listOf<TrainingCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(mUI.createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItemList.get(position)

        holder.card.setOnClickListener {
            mListener.onClickCard(position)
        }

        holder.name.setOnClickListener {
            mListener.onClickName(position)
        }

        holder.delete.setOnClickListener {
            mListener.onClickDelete(position)
        }

        holder.name.text = item.name
        holder.total.text = mContext.getString(R.string.training_list_unit, item.total)
        holder.max.text = mContext.getString(R.string.training_list_unit, item.max)

        if (item.lastDate == 0L) {
            holder.lastDate.setText(R.string.training_list_last_date_non)
        } else {
            holder.lastDate.text = SimpleDateFormat(mContext.getString(R.string.training_list_last_date_format), Locale.US).format(item.lastDate)
        }
    }

    fun setItemList(itemList: List<TrainingCard>) {
        mItemList = itemList
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: LinearLayout = mUI.mCard
        val name: TextView = mUI.mName
        val delete: RelativeLayout = mUI.mDelete
        val total: TextView = mUI.mTotal
        val max: TextView = mUI.mMax
        val lastDate: TextView = mUI.mLastDate
    }
}