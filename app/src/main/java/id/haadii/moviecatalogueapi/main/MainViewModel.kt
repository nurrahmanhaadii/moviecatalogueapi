package id.haadii.moviecatalogueapi.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.haadii.moviecatalogueapi.movie.Movie
import id.haadii.moviecatalogueapi.repository.Repository
import id.haadii.moviecatalogueapi.tvShow.TvShow

class MainViewModel : ViewModel() {

    private val repository = Repository()

    val listMovies = MutableLiveData<ArrayList<Movie>>()

    val listTvShows = MutableLiveData<ArrayList<TvShow>>()

    fun setMovie() {
        repository.setMovie {
            listMovies.postValue(it)
        }
    }

    fun setTvShow() {
        repository.setTvShow {
            listTvShows.postValue(it)
        }
    }
}