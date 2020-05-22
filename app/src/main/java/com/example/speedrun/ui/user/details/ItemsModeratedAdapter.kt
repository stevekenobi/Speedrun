package com.example.speedrun.ui.user.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.speedrun.R

class ItemsModeratedAdapter(val viewModel: UserDetailsViewModel?, val itemList: List<Any>) :
    RecyclerView.Adapter<ItemsModeratedViewHolder>() {
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

}