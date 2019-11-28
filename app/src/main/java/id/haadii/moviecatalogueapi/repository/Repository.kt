package id.haadii.moviecatalogueapi.repository

import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import id.haadii.moviecatalogueapi.movie.Movie
import id.haadii.moviecatalogueapi.repository.EndPoint.Companion.MOVIE_URL
import id.haadii.moviecatalogueapi.repository.EndPoint.Companion.TV_SHOW_URL
import id.haadii.moviecatalogueapi.repository.EndPoint.Companion.URL
import id.haadii.moviecatalogueapi.tvShow.TvShow
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class Repository {

    private val client = AsyncHttpClient()

    fun setMovie(listener : (ArrayList<Movie>) -> Unit) {
        val listItem = ArrayList<Movie>()
        val url = MOVIE_URL

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results") as JSONArray
                    for (item in 0..list.length()) {
                        val objects = list.getJSONObject(item)
                        val title = objects.getString("title")
                        val score = objects.getString("vote_average")
                        val release = objects.getString("release_date")
                        val overview = objects.getString("overview")
                        val photo = objects.getString("poster_path")
                        val photoUrl = URL + photo
                        val backDrop = objects.getString("backdrop_path")
                        val backDropUrl = URL + backDrop
                        val popularity = objects.getString("popularity")
                        val movie = Movie(title = title,
                            release = release,
                            overview = overview,
                            score = score,
                            photo = photoUrl,
                            backDrop = backDropUrl,
                            popularity = popularity)
                        listItem.add(movie)
                    }
                } catch (e : Exception) {
                    Log.e("catch", "true:$e")
                    e.printStackTrace()
                }

                listener(listItem)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.e("onFailure", error?.message.toString())
            }
        })
    }

    fun setTvShow(listener: (ArrayList<TvShow>) -> Unit) {
        val listItem = ArrayList<TvShow>()
        val url = TV_SHOW_URL

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results") as JSONArray

                    for (item in 0..list.length()) {
                        val objects = list.getJSONObject(item)
                        val title = objects.getString("original_name")
                        val score = objects.getString("vote_average")
                        val release = objects.getString("first_air_date")
                        val overview = objects.getString("overview")
                        val photo = objects.getString("poster_path")
                        val photoUrl = URL + photo
                        val backDrop = objects.getString("backdrop_path")
                        val backDropUrl = URL + backDrop
                        val popularity = objects.getString("popularity")

                        val tvShow = TvShow(title = title,
                            score = popularity,
                            release = release,
                            overview = overview,
                            photo = photoUrl,
                            backDrop = backDropUrl,
                            popularity = score
                            )

                        listItem.add(tvShow)
                    }
                } catch (e: Exception) {
                    Log.e("catch", "e:$e")
                    e.printStackTrace()
                }

                listener(listItem)

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.e("onFailure", error?.message.toString())
            }
        })
    }
}