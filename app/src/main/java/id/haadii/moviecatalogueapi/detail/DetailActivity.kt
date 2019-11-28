package id.haadii.moviecatalogueapi.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.haadii.moviecatalogueapi.R
import id.haadii.moviecatalogueapi.movie.Movie
import id.haadii.moviecatalogueapi.tvShow.TvShow
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra("movie") as Movie?
        val tv = intent.getParcelableExtra("tv_show") as TvShow?

        if (movie != null) {
            setViewMovie(movie)
        } else {
            setViewTv(tv!!)
        }
    }

    private fun setViewMovie(movie: Movie) {

        title = movie.title

        Glide.with(this)
            .load(movie.backDrop)
            .into(img_background)

        Glide.with(this)
            .load(movie.photo)
            .into(img_photo_detail)

        tv_tittle_detail.text = movie.title
        tv_date_detail.text = movie.release
        tv_runtime.text = getString(R.string.popularity, movie.popularity)
        tv_overview_content.text = movie.overview
        tv_genre_content.text = movie.score
    }

    private fun setViewTv(tv: TvShow) {
        title = tv.title

        Glide.with(this)
            .load(tv.backDrop)
            .into(img_background)

        Glide.with(this)
            .load(tv.photo)
            .into(img_photo_detail)

        tv_tittle_detail.text = tv.title
        tv_date_detail.text = tv.release
        tv_runtime.text = getString(R.string.popularity, tv.popularity)
        tv_overview_content.text = tv.overview
        tv_genre_content.text = tv.score
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
