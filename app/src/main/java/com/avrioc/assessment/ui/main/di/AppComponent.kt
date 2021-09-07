package com.avrioc.assessment.ui.main.di

import android.app.Application
import com.avrioc.assessment.ui.main.MyApplication
import com.avrioc.assessment.ui.main.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    FragmentModule::class, ActivityModule::class,
    NetworkModule::class, ViewModelModule::class, RepositoryModule::class])
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}