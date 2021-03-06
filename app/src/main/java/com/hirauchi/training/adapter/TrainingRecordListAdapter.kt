package com.hirauchi.training.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.hirauchi.training.R
import com.hirauchi.training.model.Record
import com.hirauchi.training.task.BitmapCache
import com.hirauchi.training.task.ImageTask
import com.hirauchi.training.ui.TrainingRecordListAdapterUI
import org.jetbrains.anko.AnkoContext
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

class TrainingRecordListAdapter(val mContext: Context, val mListener: TrainingRecordListAdapterListener):
        RecyclerView.Adapter<TrainingRecordListAdapter.ViewHolder>() {

    interface TrainingRecordListAdapterListener {
        fun onClickContainer(record: Record)
        fun onClickDelete(position: Int)
    }

    private val mUI = TrainingRecordListAdapterUI()
    private var mRecordList = listOf<Record>()
    private val mBitmapCache = BitmapCache()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(mUI.createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return mRecordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mRecordList.get(position)

        holder.container.setOnClickListener {
            mListener.onClickContainer(item)
        }

        if (item.imagePath?.isNotEmpty() ?: false) {
            val imageTask = ImageTask(WeakReference(holder.image), mBitmapCache)
            imageTask.execute(item.imagePath)
        } else {
            holder.image.setImageResource(R.drawable.no_image)
        }

        holder.datetime.text = SimpleDateFormat(mContext.getString(R.string.training_list_last_date_format), Locale.US).format(item.dateTime)
        holder.count.text = mContext.getString(R.string.training_list_unit, item.count)
        holder.comment.text = item.commnet

        holder.delete.setOnClickListener {
            mListener.onClickDelete(position)
        }
    }

    fun setRecordList(recordList: List<Record>) {
        mRecordList = recordList
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: LinearLayout = mUI.mContainer
        val image: ImageView = mUI.mImage
        val datetime: TextView = mUI.mDatetime
        val count: TextView = mUI.mCount
        val comment: TextView = mUI.mComment
        val delete: RelativeLayout = mUI.mDelete
    }
}