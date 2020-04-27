package com.example.speedrun.ui.main

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunTimeConverter
import kotlinx.android.synthetic.main.item_latest_run.view.*

class LatestRunViewHolder(itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(run: LatestRunDto) {
        itemView.apply {

            run_players_names.adapter =
                RunPlayerRecyclerViewAdapter(itemView.context, run.players.data)
            run_players_names.layoutManager = LinearLayoutManager(itemView.context)
            run_category_name.text = run.category.data.name
            run_time.text = RunTimeConverter.from(run.times.primary_t)
            run_game_name.text = if (run.game.data.names == null)
                "null name"
            else run.game.data.names?.international

            run_date.text = run.date
        }
    }
}