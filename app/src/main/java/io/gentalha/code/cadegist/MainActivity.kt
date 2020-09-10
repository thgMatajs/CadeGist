package io.gentalha.code.cadegist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import io.gentalha.code.cadegist.adapter.GistsAdapter
import io.gentalha.code.cadegist.presentation.viewmodel.GetGistsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val _viewModel: GetGistsViewModel by viewModel()

    private val gistsRv: RecyclerView by lazy { findViewById(R.id.gistsRv) }

    private val adapter: GistsAdapter by lazy {
        GistsAdapter(
            itemClick = {},
            favoriteClick = {}
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gistsRv.adapter = adapter

        val subscribe = _viewModel.gistsFlowable
            .subscribe(
            {
                println("THG_LOG --> SUCCESS")
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