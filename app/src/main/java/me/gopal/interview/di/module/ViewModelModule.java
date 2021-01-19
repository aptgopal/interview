package me.gopal.interview.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.gopal.interview.ui.list.ListViewModel;
import me.gopal.interview.ui.detail.DetailsViewModel;
import me.gopal.interview.di.util.ViewModelKey;
import me.gopal.interview.ui.rooms.RoomViewModel;
import me.gopal.interview.util.ViewModelFactory;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RoomViewModel.class)
    abstract ViewModel bindRoomViewModel(RoomViewModel roomViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
