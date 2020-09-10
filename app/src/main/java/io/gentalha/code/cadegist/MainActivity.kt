package io.gentalha.code.cadegist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.gentalha.code.cadegist.presentation.viewmodel.GetGistsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val _viewModel: GetGistsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subscribe = _viewModel.gistsFlowable
            .subscribe(
            {
                println("THG_LOG --> SUCCESS")
            },
            {
                println("THG_LOG -> ERROR ${it.message}")
            }
        )

    }
}