package io.gentalha.code.cadegist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.adapter.FavoriteGistsAdapter
import io.gentalha.code.cadegist.custom_views.ViewStateNotifier
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.presentation.extensions.hide
import io.gentalha.code.cadegist.presentation.extensions.show
import io.gentalha.code.cadegist.presentation.viewmodel.AddGistInFavoriteViewModel
import io.gentalha.code.cadegist.presentation.viewmodel.GetFavoriteGistsViewModel
import io.gentalha.code.cadegist.presentation.viewmodel.RemoveGistInFavoriteViewModel
import io.gentalha.code.cadegist.view.activities.GistDetailActivity
import io.gentalha.code.common.extensions.handleExceptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteGistsFragment : Fragment(R.layout.fragment_favorite_gists) {

    private lateinit var favoriteGistsRv: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewStateNotifier: ViewStateNotifier
    private val getFavoritesViewModel: GetFavoriteGistsViewModel by viewModel()
    private val addGistInFavoriteViewModel: AddGistInFavoriteViewModel by viewModel()
    private val removeGistOfFavoriteViewModel: RemoveGistInFavoriteViewModel by viewModel()
    private val favoriteAdapter: FavoriteGistsAdapter by lazy {
        FavoriteGistsAdapter(
            itemClick = {
                val intent = Intent(requireContext(), GistDetailActivity::class.java)
                intent.putExtra("GIST_ID", it.id)
                startActivity(intent)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getFavoritesViewModel.loadFavoriteGists()
        initViews(view)
        setupRv()
        observeGetFavoriteGistsLiveData()
        observeRemoveGistOfFavorite()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews(view: View) {
        view.apply {
            favoriteGistsRv = findViewById(R.id.favoriteGistsRv)
            progressBar = findViewById(R.id.favoriteGistsProgressBar)
            viewStateNotifier = findViewById(R.id.favoriteGistsStateNotifier)
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

    private fun observeRemoveGistOfFavorite() {
        removeGistOfFavoriteViewModel.removeFavoriteGistLiveData.observe(viewLifecycleOwner, {
            it.handle(
                onSuccess = { getFavoritesViewModel.loadFavoriteGists() },
                onError = ::handleError
            )
        })
    }

    private fun showLoading() {
        progressBar.show()
        favoriteGistsRv.hide()
        viewStateNotifier.hide()
    }

    private fun hideLoading() {
        progressBar.hide()
        favoriteGistsRv.show()
        viewStateNotifier.hide()
    }

    private fun handleSuccessResult(gists: List<Gist>?) {
        hideLoading()
        favoriteAdapter.submitList(gists)
        if (gists.isNullOrEmpty())
            viewStateNotifier.showEmptyState(
                titleResId = R.string.favorite_empty_title,
                descriptionResId = R.string.favorite_empty_description
            )
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