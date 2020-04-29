package com.example.speedrun.ui.user

import android.view.View
import com.example.network.model.dto.UserRunDto
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunsTextUtils
import kotlinx.android.synthetic.main.item_user_run.view.*

class UserRunViewHolder(itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(run: UserRunDto) {
        itemView.user_run_info.text = RunsTextUtils.setUserGameRun(run)
    }
}