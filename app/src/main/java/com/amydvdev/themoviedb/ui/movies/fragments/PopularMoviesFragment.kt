package com.amydvdev.themoviedb.ui.movies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.amydvdev.domain.model.Movie
import com.amydvdev.presentation.states.Resource
import com.amydvdev.presentation.viewmodel.GetPopularMoviesViewModel
import com.amydvdev.themoviedb.databinding.FragmentPopularMoviesBinding
import com.amydvdev.themoviedb.ui.movies.adapter.PopularMoviesAdapter
import com.amydvdev.presentation.states.ResourceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val popularMoviesViewModel by viewModels<GetPopularMoviesViewModel>()

    private val binding by lazy { FragmentPopularMoviesBinding.inflate(layoutInflater) }

    private lateinit var adapter: PopularMoviesAdapter

    private val movieClickCallback: (Movie) -> Unit = { movie ->
        val dir = PopularMoviesFragmentDirections.actionNavPopularMoviesToNavMovieDetails(movie)
        findNavController().navigate(dir)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // To Prevent api call when user navigates back from Details Screen
        popularMoviesViewModel.getPopularMovies()
        setupRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        popularMoviesViewModel.popularMovies.observe(viewLifecycleOwner, popularMoviesObserver())
    }

    private fun popularMoviesObserver(): Observer<Resource<List<Movie>>?> {
        return Observer {
            val res = it ?: return@Observer
            when (res.status) {
                ResourceState.LOADING -> {
                    binding.progress.isVisible = true
                }
                ResourceState.SUCCESS -> {
                    res.data?.let { movies ->
                        adapter.items = movies
                    }
                    binding.progress.isVisible = false
                }
                ResourceState.ERROR -> {
                    binding.progress.isVisible = false
                }
            }
        }
    }

    private fun setupRecycler() {
        adapter = PopularMoviesAdapter(movieClickCallback)
        binding.rvMovies.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // To prevent livedata from emitting data
        // when user navigates back from details screen
        popularMoviesViewModel.reset()
    }
}