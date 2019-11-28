package id.haadii.moviecatalogueapi.tvShow

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val title: String,
    val release: String,
    val overview: String,
    val score: String,
    val popularity: String,
    val photo: String,
    val backDrop: String
) : Parcelable