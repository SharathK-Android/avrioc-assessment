package com.avrioc.assessment.ui.main.repository

import com.avrioc.assessment.ui.main.api.ArticlesApi
import com.avrioc.assessment.ui.main.api.RetrofitBuilder
import com.avrioc.assessment.ui.main.data.ArticlesResponse
import io.reactivex.Single

private const val SECTION_PARAM = "all-sections"
private const val PERIOD_PARAM = 7
private const val API_KEY = "0UqBarODSe9hIJ21FrRvTkeMQz65GfbA"
//api key should be stored at the backend server

interface ArticlesRepository {
     fun getResponse(): Single<ArticlesResponse>
     fun getAPiService(): ArticlesApi

     companion object {
         fun getInstance() = RepositoryImpl()
     }
}

class RepositoryImpl: ArticlesRepository {

    override fun getResponse(): Single<ArticlesResponse> =
        getAPiService().getResponse( SECTION_PARAM, PERIOD_PARAM, API_KEY)

    override fun getAPiService(): ArticlesApi =
        RetrofitBuilder.apiService

}