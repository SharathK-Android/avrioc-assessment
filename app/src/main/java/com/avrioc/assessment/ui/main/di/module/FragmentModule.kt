package com.avrioc.assessment.ui.main.di.module

import com.avrioc.assessment.ui.main.view.ArticlesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ArticlesListFragment
}
