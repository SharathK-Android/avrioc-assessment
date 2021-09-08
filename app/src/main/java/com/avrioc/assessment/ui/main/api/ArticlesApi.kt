package com.avrioc.assessment.ui.main.api

import com.avrioc.assessment.ui.main.data.ArticlesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_END_POINT = "mostviewed/{section}/{period}.json"
const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
private const val SECTION = "section"
private const val PERIOD = "period"
private const val API_KEY = "api-key"

interface ArticlesApi {
    @GET(API_END_POINT)
    fun getResponse(
        @Path(SECTION) section: String,
        @Path(PERIOD) period: Int,
        @Query(API_KEY) apiKey: String
        ): Single<ArticlesResponse>
}