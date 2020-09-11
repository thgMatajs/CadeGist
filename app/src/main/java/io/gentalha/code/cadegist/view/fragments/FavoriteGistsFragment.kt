package io.gentalha.code.cadegist.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.adapter.FavoriteGistsAdapter
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.presentation.viewmodel.GetFavoriteGistsViewModel
import io.gentalha.code.common.extensions.handleExceptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteGistsFragment : Fragment() {

    private lateinit var favoriteGistsRv: RecyclerView
    private val getFavoritesViewModel: GetFavoriteGistsViewModel by viewModel()
    private val adapter: FavoriteGistsAdapter by lazy {
        FavoriteGistsAdapter(
            itemClick = {

            },
            favoriteClick = {

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
        }
    }

    private fun setupRv() {
        favoriteGistsRv.adapter = adapter
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
        println("THG_LOG --> Loading")
    }

    private fun hideLoading() {

    }

    private fun handleSuccessResult(gists: List<Gist>?) {
        adapter.submitList(gists)
    }

    private fun handleError(error: Throwable?) {
        error?.handleExceptions(
            httpException = {},
            whitOutNetWorkException = {},
            otherExceptions = {}
        )
    }


}