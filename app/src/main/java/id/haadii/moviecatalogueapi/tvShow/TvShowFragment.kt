package id.haadii.moviecatalogueapi.tvShow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import id.haadii.moviecatalogueapi.R
import id.haadii.moviecatalogueapi.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_tv_show.*
import java.util.*
import id.haadii.moviecatalogueapi.detail.DetailActivity

class TvShowFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(Objects.requireNonNull(activity!!)).get(MainViewModel::class.java)

        viewModel.setTvShow()

        initObserver()
    }

    private fun initObserver() {
        viewModel.listTvShows.observe(this, Observer {
            rv_tv_show.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = TvShowAdapter(activity!!, it) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra("tv_show", it)
                    startActivity(intent)
                }

                pb_tv_show.visibility = View.GONE
            }
        })
    }

}
