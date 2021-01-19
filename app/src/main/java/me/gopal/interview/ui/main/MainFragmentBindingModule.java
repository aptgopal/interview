package me.gopal.interview.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.gopal.interview.ui.detail.DetailsFragment;
import me.gopal.interview.ui.list.ListFragment;
import me.gopal.interview.ui.rooms.RoomsFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment();

    @ContributesAndroidInjector
    abstract RoomsFragment provideRoomFragment();
}
