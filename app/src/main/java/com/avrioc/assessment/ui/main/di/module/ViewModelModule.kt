package com.avrioc.assessment.ui.main.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avrioc.assessment.ui.main.di.ViewModelFactory
import com.avrioc.assessment.ui.main.di.ViewModelKey
import com.avrioc.assessment.ui.main.viewmodel.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    internal abstract fun bindViewModel(myViewModel: ArticlesViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}