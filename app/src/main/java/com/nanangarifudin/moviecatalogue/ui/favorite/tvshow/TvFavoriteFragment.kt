package com.nanangarifudin.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanangarifudin.moviecatalogue.R
import com.nanangarifudin.moviecatalogue.ui.favorite.movie.FavoriteMovieViewModel
import com.nanangarifudin.moviecatalogue.ui.favorite.movie.FavoriteMoviesAdapter
import com.nanangarifudin.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*

class TvFavoriteFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel : FavoriteTvShowViewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

        val moviesAdapter = FavoriteTvShowAdapter()

        progress_bar.visibility = View.VISIBLE
        viewModel.getAllFavorite().observe(viewLifecycleOwner, { movies ->
            progress_bar.visibility = View.INVISIBLE
            moviesAdapter.setData(movies)

            with(rv_movies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        })
    }
}