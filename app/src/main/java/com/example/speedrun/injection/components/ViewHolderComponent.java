package com.example.speedrun.injection.components;

import com.example.speedrun.injection.PerViewHolder;
import com.example.speedrun.ui.main.LatestGameViewHolder;
import com.example.speedrun.ui.main.LatestPlayersViewHolder;
import com.example.speedrun.ui.main.LatestRunViewHolder;

import dagger.Component;

@PerViewHolder
@Component(dependencies = {ApplicationComponent.class} )
public interface ViewHolderComponent {

    void inject(LatestGameViewHolder holder);
    void inject(LatestRunViewHolder holder);
    void inject(LatestPlayersViewHolder holder);

}
