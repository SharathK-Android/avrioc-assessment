package com.avrioc.assessment.ui.main.di.module

import com.avrioc.assessment.ui.main.repository.ArticlesRepository
import com.avrioc.assessment.ui.main.repository.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    internal abstract fun bindLoanQuoteRepository(repository: RepositoryImpl): ArticlesRepository
}