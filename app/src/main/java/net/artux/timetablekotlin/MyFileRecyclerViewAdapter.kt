package net.artux.timetablekotlin

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import net.artux.timetablekotlin.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyFileRecyclerViewAdapter(
    private val values: HashMap<String, String>
) : RecyclerView.Adapter<MyFileRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_file, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idView.text = values.keys.elementAt(position)
        holder.contentView.text = values.values.elementAt(position)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.filename)
        val contentView: TextView = view.findViewById(R.id.id)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}