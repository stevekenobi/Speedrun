package com.example.speedrun.ui.user.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.GameDto
import com.example.network.model.dto.SeriesDto
import com.example.speedrun.R
import kotlinx.android.synthetic.main.item_game_series_moderate.view.*

class ItemsModeratedAdapter(val viewModel: UserDetailsViewModel?, val itemList: List<Any>) : RecyclerView.Adapter<ItemsModeratedAdapter.ItemsModeratedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsModeratedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game_series_moderate, parent, false)
        return ItemsModeratedViewHolder(viewModel, view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemsModeratedViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    class ItemsModeratedViewHolder(val viewModel: UserDetailsViewModel?, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Any) {
            itemView.item_moderated.apply {
                when (item) {
                    is GameDto -> {
                        text = item.names?.international
                        setOnClickListener { viewModel?.gameClickedLiveData?.value = item.id }
                    }
                    is SeriesDto -> {
                        text = item.names?.international
                        setOnClickListener { viewModel?.seriesClickedLiveData?.value = item.id }
                    }
                }
            }
        }
    }

}