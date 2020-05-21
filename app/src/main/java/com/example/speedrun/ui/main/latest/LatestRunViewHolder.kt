package com.example.speedrun.ui.main.latest

import android.content.res.Configuration
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunTimeConverter
import kotlinx.android.synthetic.main.item_latest_run.view.*

class LatestRunViewHolder(val viewModel: LatestRunsViewModel?, itemView: View, val showMills: Boolean) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(run: LatestRunDto) {
        itemView.apply {
            val info = run.category.data.name + "   " + RunTimeConverter.from(run.times.primary_t, showMills)
            if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                latest_game_run_info.text = info
            } else {
                land_latest_run_cat.text = run.category.data.name
                land_latest_run_time.text = RunTimeConverter.from(run.times.primary_t, showMills)
                land_latest_players.adapter =
                    LatestPlayersAdapter(
                        viewModel,
                        run.players.data
                    )
                land_latest_players.layoutManager = LinearLayoutManager(context)
            }

            setOnClickListener {
                viewModel?.latestRunPressedLiveData?.value = run.id
            }
        }
    }
}