package io.gentalha.code.cadegist.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.adapter.GistsAdapter
import io.gentalha.code.cadegist.presentation.viewmodel.AddGistInFavoriteViewModel
import io.gentalha.code.cadegist.presentation.viewmodel.GetGistsViewModel
import io.gentalha.code.cadegist.presentation.viewmodel.RemoveGistInFavoriteViewModel
import io.gentalha.code.cadegist.view.activities.GistDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class GistsFragment : Fragment() {

    private val viewModel: GetGistsViewModel by viewModel()
    private val addGistInFavoriteViewModel: AddGistInFavoriteViewModel by viewModel()
    private val removeGistOfFavoriteViewModel: RemoveGistInFavoriteViewModel by viewModel()
    private lateinit var gistsRv: RecyclerView
    private val adapter: GistsAdapter by lazy {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        setupRv()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews(view: View) {
        view.apply {
            gistsRv = findViewById(R.id.gistsRv)
        }
    }

    private fun setupRv() {
        gistsRv.adapter = adapter

        val subscribe = viewModel.gistsFlowable
            .subscribe(
                {
                    adapter.submitData(lifecycle, it)
                },
                {
                    println("THG_LOG -> ERROR ${it.message}")
                }
            )

        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {
                    println("THG_LOG --> Loading")
                }
                is LoadState.Error -> {
                    println("THG_LOG --> Error")
                }
                is LoadState.NotLoading -> {
                    println("THG_LOG --> NotLoading")
                }
            }
        }
    }
}