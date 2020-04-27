package com.example.speedrun.injection.components;

import com.example.speedrun.injection.PerViewHolder;
import com.example.speedrun.ui.main.LatestRunViewHolder;
import com.example.speedrun.ui.main.RunPlayerViewHolder;

import dagger.Component;

@PerViewHolder
@Component(dependencies = {ApplicationComponent.class} )
public interface ViewHolderComponent {

    void inject(LatestRunViewHolder holder);
    void inject(RunPlayerViewHolder holder);

}
