package com.example.speedrun.ui.run

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.splits.SplitsDto
import com.example.speedrun.R
import com.example.speedrun.utils.RunTimeConverter
import kotlinx.android.synthetic.main.item_split.view.*

class SplitsAdapter(val splits: List<SplitsDto>, val showMills: Boolean) : RecyclerView.Adapter<SplitsAdapter.SplitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_split, parent, false)
        return SplitViewHolder(view, showMills)
    }

    override fun getItemCount(): Int {
        return splits.size
    }

    override fun onBindViewHolder(holder: SplitViewHolder, position: Int) {
        holder.bind(splits[position])
    }

    class SplitViewHolder(itemView: View, val showMills: Boolean) : RecyclerView.ViewHolder(itemView) {

        fun bind(split: SplitsDto) {
            itemView.apply {
                split_name.text = split.name

                split_time.text = RunTimeConverter.from(split.duration, showMills)
            }
        }
    }
}