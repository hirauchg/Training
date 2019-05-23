package com.hirauchi.training.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.hirauchi.training.R
import com.hirauchi.training.activity.TrainingRecordActivity
import com.hirauchi.training.manager.RecordManager
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.support.v4.find
import java.text.SimpleDateFormat
import java.util.*

class TrainingRecordChartFragment : Fragment() {

    lateinit var mActivity: TrainingRecordActivity

    override fun onAttach(context: Context){
        mActivity = activity as TrainingRecordActivity
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.training_record_chart_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reload()
    }

    class IntegerValueFormatter : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return value.toInt().toString()
        }
    }

    fun reload() {
        val recordList = RecordManager(mActivity).getRecordList(mActivity.mTrainingId, SqlOrderDirection.ASC)

        if (recordList.isEmpty()) {
            find<LinearLayout>(R.id.container).visibility = View.GONE
            return
        } else {
            find<LinearLayout>(R.id.container).visibility = View.VISIBLE
        }

        var total = 0
        var max = 0
        val entries = ArrayList<Entry>()
        val date = ArrayList<String>()

        var index = 0F
        for (record in recordList) {
            total += record.count
            if (max < record.count) max = record.count
            date.add(SimpleDateFormat(getString(R.string.training_list_last_date_format), Locale.US).format(record.dateTime))
            entries.add(Entry(index, record.count.toFloat()))
            index++
        }

        find<TextView>(R.id.chart_training).setText(getString(R.string.record_chart_unit, index.toInt()))
        find<TextView>(R.id.chart_total).setText(getString(R.string.record_chart_unit, total))
        find<TextView>(R.id.chart_max).setText(getString(R.string.record_chart_unit, max))

        val dataSet = LineDataSet(entries, getString(R.string.record_chart_label_x)).apply {
            valueFormatter = IntegerValueFormatter()
            isHighlightEnabled = false
            setCircleColor(ContextCompat.getColor(mActivity, R.color.colorAccent))
            color = ContextCompat.getColor(mActivity, R.color.colorAccent)
            valueTextSize = 16F
        }

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(dataSet)

        find<LineChart>(R.id.line_chart).apply {
            setData(LineData(dataSets))
            description.isEnabled = false
            backgroundColor = ContextCompat.getColor(mActivity, R.color.chartBG)

            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(date)
                position = XAxis.XAxisPosition.BOTTOM
            }
        }
    }
}