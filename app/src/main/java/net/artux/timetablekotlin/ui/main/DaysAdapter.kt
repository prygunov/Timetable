package net.artux.timetablekotlin.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.text.HtmlCompat.fromHtml
import androidx.recyclerview.widget.RecyclerView
import net.artux.timetablekotlin.R
import net.artux.timetablekotlin.data.main.Day
import net.artux.timetablekotlin.data.model.Task
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DaysAdapter(
    var list:List<Day>,
    var dateFormat: SimpleDateFormat) : RecyclerView.Adapter<DaysAdapter.DayHolder>() {

    var onItemClick: ((Task?) -> Unit)? = null

    fun update(list: List<Day>){
        this.list = list;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder =
        DayHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false), viewType)


    override fun getItemCount() :Int{
        var size = 0
        for (day in list){
            size += day.list.size
        }
        return size
    }

    override fun getItemViewType(position: Int): Int {
        var i = 0
        for (day in list){
            if (i == position)
                return 1
            else
                i+=day.list.size
        }
        return 0
    }

    private fun getTask(position: Int): Task? {
        var i = 0

        for (day in list){
            if (position>=i && position<i+day.list.size) {
                i = position - i
                return day.list[i]
            }
            else
                i+=day.list.size
        }
        return null
    }

    private fun getDay(position: Int): Day? {
        var i = 0

        for (day in list){
            if (position>=i && position<i+day.list.size)
                return day
            else
                i+=day.list.size
        }
        return null
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        val task = getTask(position)
        if (task!=null){
            holder.mTitleView.text = task.title
            holder.mDescView.text = task.description?.let { fromHtml(it, FROM_HTML_MODE_LEGACY) }
            if (holder.type == 1){
                holder.titleView.visibility = View.VISIBLE
                holder.titleView.text = "${getDay(position)?.number}"

                println("Bind position $position, ${getDay(position)?.number}")
            }
        }
    }
    inner class DayHolder(itemView: View, var type:Int) : RecyclerView.ViewHolder(itemView) {

        var titleView: TextView = itemView.findViewById(R.id.dayTitleView)

        var mTitleView: TextView  = itemView.findViewById(R.id.titleView)
        var mDescView: TextView = itemView.findViewById(R.id.descView)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getTask(adapterPosition))
            }
        }


    }


}



