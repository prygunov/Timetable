package net.artux.timetablekotlin.ui.chat

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.artux.timetablekotlin.R
import net.artux.timetablekotlin.data.model.Message

import net.artux.timetablekotlin.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MessageRecyclerViewAdapter(
    var values: List<Message>
) : RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = "${item.Date}, ${item.Fio}"
        holder.contentView.text = item.Text
    }

    fun updateMessages(list:List<Message>){
        values = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.chat_item_title)
        val contentView: TextView = view.findViewById(R.id.chat_item_content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}