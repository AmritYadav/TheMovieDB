package com.amydvdev.themoviedb.ui.movies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.amydvdev.themoviedb.R
import com.amydvdev.themoviedb.databinding.FragmentMovieDetailsBinding
import com.amydvdev.themoviedb.ui.movies.MainActivity
import com.amydvdev.themoviedb.utils.Helpers
import com.amydvdev.themoviedb.utils.loadImage

class MovieDetailsFragment : Fragment() {

    private val binding by lazy { FragmentMovieDetailsBinding.inflate(layoutInflater) }

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (args.movie) {
            val backdropUrl = Helpers.buildBackdropPath(backdropPath)
            val posterUrl = Helpers.buildBackdropPath(posterPath)

            binding.ivBackdrop.loadImage(backdropUrl)
            binding.ivPoster.loadImage(posterUrl)

            binding.title.text = title

            val voteAvg = "${(voteAverage * 10).toInt()}%"
            val userScore = getString(R.string.score, voteAvg)
            binding.userScore.text = userScore

            val releaseDate = getString(R.string.release, releaseDate)
            binding.release.text = releaseDate

            binding.overview.text = overview
        }

    }
}