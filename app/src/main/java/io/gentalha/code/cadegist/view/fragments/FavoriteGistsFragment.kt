package io.gentalha.code.cadegist.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.adapter.FavoriteGistsAdapter
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.presentation.extensions.hide
import io.gentalha.code.cadegist.presentation.extensions.show
import io.gentalha.code.cadegist.presentation.viewmodel.AddGistInFavoriteViewModel
import io.gentalha.code.cadegist.presentation.viewmodel.GetFavoriteGistsViewModel
import io.gentalha.code.cadegist.presentation.viewmodel.RemoveGistInFavoriteViewModel
import io.gentalha.code.common.extensions.handleExceptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteGistsFragment : Fragment() {

    private lateinit var favoriteGistsRv: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val getFavoritesViewModel: GetFavoriteGistsViewModel by viewModel()
    private val addGistInFavoriteViewModel: AddGistInFavoriteViewModel by viewModel()
    private val removeGistOfFavoriteViewModel: RemoveGistInFavoriteViewModel by viewModel()
    private val favoriteAdapter: FavoriteGistsAdapter by lazy {
        FavoriteGistsAdapter(
            itemClick = {

            },
            favoriteClick = {
                if (it.isFavorite) {
                    removeGistOfFavoriteViewModel.removeOfFavorite(it)
                } else {
                    addGistInFavoriteViewModel.addInFavorite(it)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getFavoritesViewModel.loadFavoriteGists()
        return inflater.inflate(R.layout.fragment_favorite_gists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        setupRv()
        observeGetFavoriteGistsLiveData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews(view: View) {
        view.apply {
            favoriteGistsRv = findViewById(R.id.favoriteGistsRv)
            progressBar = findViewById(R.id.favoriteGistsProgressBar)
        }
    }

    private fun setupRv() {
        favoriteGistsRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = favoriteAdapter
        }
    }

    private fun observeGetFavoriteGistsLiveData() {
        getFavoritesViewModel.favoriteGistLiveData.observe(viewLifecycleOwner, {
            it.handle(
                onLoading = ::showLoading,
                onSuccess = ::handleSuccessResult,
                onError = ::handleError
            )
        })
    }

    private fun showLoading() {
        progressBar.show()
        favoriteGistsRv.hide()
    }

    private fun hideLoading() {
        progressBar.hide()
        favoriteGistsRv.show()
    }

    private fun handleSuccessResult(gists: List<Gist>?) {
        hideLoading()
        favoriteAdapter.submitList(gists)
    }

    private fun handleError(error: Throwable?) {
        hideLoading()
        error?.handleExceptions(
            httpException = {},
            whitOutNetWorkException = {},
            otherExceptions = {}
        )
    }


}