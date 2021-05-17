package net.artux.timetablekotlin

import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.artux.timetablekotlin.data.model.Decision
import net.artux.timetablekotlin.databinding.ItemResultBinding

import net.artux.timetablekotlin.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ResultsRecyclerViewAdapter(
    private val values: List<Decision>
) : RecyclerView.Adapter<ResultsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val decision = values[position]
        holder.binding.resultText.text = Html.fromHtml(decision.getText())
        holder.binding.resultTime.text = decision.getVersionDate()
        if (decision.getResultText()!=null && decision.getResultText()!=""){
            holder.binding.resultComment.visibility = View.VISIBLE
            holder.binding.resultComment.text = decision.getResultText()
        }

        if (decision.getResult()!=null) {
            if (decision.getResult()!!.toInt() in 1..5) {
                holder.binding.resultStatus.text = decision.getResult()
            }else
                if (decision.getResult()!!.toInt() == 0)
                    holder.binding.resultStatus.text = "X"
                else
                    holder.binding.resultStatus.text = "✓"
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(var binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}