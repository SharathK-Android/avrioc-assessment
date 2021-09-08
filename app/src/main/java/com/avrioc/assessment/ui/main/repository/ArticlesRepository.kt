package com.avrioc.assessment.ui.main.repository

import com.avrioc.assessment.ui.main.api.ArticlesApi
import com.avrioc.assessment.ui.main.data.ArticlesResponse
import io.reactivex.Single
import javax.inject.Inject

private const val SECTION_PARAM = "all-sections"
private const val PERIOD_PARAM = 7
private const val API_KEY = "0UqBarODSe9hIJ21FrRvTkeMQz65GfbA"
//api key can be stored at the backend server

interface ArticlesRepository {
     fun getResponse(): Single<ArticlesResponse>
}

class RepositoryImpl @Inject constructor(private val api: ArticlesApi) : ArticlesRepository {

    override fun getResponse(): Single<ArticlesResponse> =
        api.getResponse( SECTION_PARAM, PERIOD_PARAM, API_KEY)

}