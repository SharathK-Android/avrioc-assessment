package com.avrioc.assessment.ui.main.di.module

import com.avrioc.assessment.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class, ViewModelModule::class])
    abstract fun contributeActivityAndroidInjector(): MainActivity

}