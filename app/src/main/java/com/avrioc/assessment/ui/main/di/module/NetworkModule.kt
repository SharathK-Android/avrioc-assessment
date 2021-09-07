package com.avrioc.assessment.ui.main.di.module

import com.avrioc.assessment.ui.main.api.ArticlesApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getApiInterface(retroFit: Retrofit): ArticlesApi
    {
        return retroFit.create(ArticlesApi::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit
    {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient
    {
        return  OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor
    {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}
