package com.example.speedrun.ui.main.latest

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.R
import com.example.speedrun.utils.RunTimeConverter
import kotlinx.android.synthetic.main.item_latest_run.view.*

class LatestRunAdapter(val viewModel: LatestRunsViewModel?, val runs: List<LatestRunDto>, val showMills: Boolean) : RecyclerView.Adapter<LatestRunAdapter.LatestRunViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestRunViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_latest_run, parent, false)
        return LatestRunViewHolder(viewModel, view, showMills)
    }

    override fun getItemCount() = runs.size

    override fun onBindViewHolder(holder: LatestRunViewHolder, position: Int) {
        holder.bind(runs[position])
    }

    class LatestRunViewHolder(val viewModel: LatestRunsViewModel?, itemView: View, val showMills: Boolean) : RecyclerView.ViewHolder(itemView) {

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
}