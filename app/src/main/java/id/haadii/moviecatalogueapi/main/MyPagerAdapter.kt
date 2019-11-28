package id.haadii.moviecatalogueapi.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.haadii.moviecatalogueapi.R
import id.haadii.moviecatalogueapi.movie.MovieFragment
import id.haadii.moviecatalogueapi.tvShow.TvShowFragment

class MyPagerAdapter(fragmentManager: FragmentManager, private val context: Context) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MovieFragment()
            }
            else -> {
                return TvShowFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                context.getString(R.string.movie)
            }
            else -> {
                context.getString(R.string.tv_show)
            }
        }
    }

}