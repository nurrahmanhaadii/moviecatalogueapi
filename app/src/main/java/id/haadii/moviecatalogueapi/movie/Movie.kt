package id.haadii.moviecatalogueapi.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val release: String,
    val overview: String,
    val score: String,
    val popularity: String,
    val photo: String,
    val backDrop: String
) : Parcelable