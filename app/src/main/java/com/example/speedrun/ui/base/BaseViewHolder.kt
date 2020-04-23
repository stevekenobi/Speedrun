package com.example.speedrun.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import butterknife.ButterKnife
import com.example.speedrun.application.SpeedrunApplication
import com.example.speedrun.injection.components.ApplicationComponent
import com.example.speedrun.injection.components.DaggerViewHolderComponent
import com.example.speedrun.injection.components.ViewHolderComponent

open class BaseViewHolder(itemView: View) : ViewHolder(itemView) {
    private var viewHolderComponent: ViewHolderComponent? = null
    protected fun viewHolderComponent(): ViewHolderComponent? {
        return viewHolderComponent
    }

    init {
        ButterKnife.bind(this, itemView)
        if (viewHolderComponent == null) {
            val applicationComponent: ApplicationComponent =
                (itemView.context.applicationContext as SpeedrunApplication).getComponent()
            viewHolderComponent = DaggerViewHolderComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
        }
    }
}
