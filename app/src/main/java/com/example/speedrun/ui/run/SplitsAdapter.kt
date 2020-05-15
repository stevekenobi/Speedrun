package com.example.speedrun.ui.run

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.splits.SplitsDto
import com.example.speedrun.R

class SplitsAdapter(val splits: List<SplitsDto>) : RecyclerView.Adapter<SplitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_split, parent, false)
        return SplitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return splits.size
    }

    override fun onBindViewHolder(holder: SplitViewHolder, position: Int) {
        holder.bind(splits[position])
    }

}