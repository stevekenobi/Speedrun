package com.example.speedrun.injection.components;

import com.example.speedrun.injection.PerViewHolder;

import dagger.Component;

@PerViewHolder
@Component(dependencies = {ApplicationComponent.class} )
public interface ViewHolderComponent {


}
