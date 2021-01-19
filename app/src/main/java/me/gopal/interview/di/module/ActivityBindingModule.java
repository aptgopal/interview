package me.gopal.interview.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.gopal.interview.ui.main.MainActivity;
import me.gopal.interview.ui.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
