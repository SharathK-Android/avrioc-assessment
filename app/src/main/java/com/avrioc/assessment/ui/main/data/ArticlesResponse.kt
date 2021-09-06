package com.avrioc.assessment.ui.main.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticlesResponse(
	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,
) : Parcelable

@Parcelize
data class MediaItem(
	@field:SerializedName("media-metadata")
	val mediaMetadata: List<MediaMetadataItem?>? = null,
) : Parcelable

@Parcelize
data class ResultsItem(
	@field:SerializedName("media")
	val media: List<MediaItem?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("published_date")
	val publishedDate: String? = null,

	@field:SerializedName("byline")
	val byline: String? = null
) : Parcelable

@Parcelize
data class MediaMetadataItem(

	@field:SerializedName("url")
	val url: String? = null,

) : Parcelable
