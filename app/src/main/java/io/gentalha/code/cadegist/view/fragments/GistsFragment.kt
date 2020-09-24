package io.gentalha.code.cadegist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.adapter.GistsAdapter
import io.gentalha.code.cadegist.custom_views.ViewStateNotifier
import io.gentalha.code.cadegist.presentation.extensions.hide
import io.gentalha.code.cadegist.presentation.extensions.show
import io.gentalha.code.cadegist.presentation.viewmodel.AddGistInFavoriteViewModel
import io.gentalha.code.cadegist.presentation.viewmodel.GetGistsViewModel
import io.gentalha.code.cadegist.presentation.viewmodel.RemoveGistInFavoriteViewModel
import io.gentalha.code.cadegist.view.activities.GistDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class GistsFragment : Fragment(R.layout.fragment_gists) {

    private val viewModel: GetGistsViewModel by viewModel()
    private val addGistInFavoriteViewModel: AddGistInFavoriteViewModel by viewModel()
    private val removeGistOfFavoriteViewModel: RemoveGistInFavoriteViewModel by viewModel()
    private lateinit var gistsRv: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewStateNotifier: ViewStateNotifier

    private val gistAdapter: GistsAdapter by lazy {
        GistsAdapter(
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
        initViews(view)
        setupRv()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews(view: View) {
        view.apply {
            gistsRv = findViewById(R.id.gistsRv)
            progressBar = findViewById(R.id.gistsProgressBar)
            viewStateNotifier = findViewById(R.id.gistsStateNotifier)
        }
    }

    private fun setupRv() {
        gistsRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = gistAdapter
        }
        loadGists()
        gistAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {
                    showLoading()
                }
                is LoadState.Error -> {
                    hideLoading()
                    viewStateNotifier.showGenericError()
                }
                is LoadState.NotLoading -> {
                    hideLoading()
                }
            }
        }
    }

    private fun loadGists() {
        val subscribe = viewModel.gistsFlowable
            .subscribe {
                gistAdapter.submitData(lifecycle, it)
            }
    }

    private fun showLoading() {
        progressBar.show()
        gistsRv.hide()
    }

    private fun hideLoading() {
        progressBar.hide()
        gistsRv.show()
    }

}