package id.haadii.moviecatalogueapi.movie


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import id.haadii.moviecatalogueapi.R
import id.haadii.moviecatalogueapi.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import java.util.*
import id.haadii.moviecatalogueapi.detail.DetailActivity

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(Objects.requireNonNull(activity!!)).get(MainViewModel::class.java)

        viewModel.setMovie()

        initObserver()
    }

    private fun initObserver() {
        viewModel.listMovies.observe(this, androidx.lifecycle.Observer {
            rv_movie.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = MovieAdapter(activity!!, it) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra("movie", it)
                    startActivity(intent)
                }
                pb_movie.visibility = View.GONE
            }
        })
    }

}
