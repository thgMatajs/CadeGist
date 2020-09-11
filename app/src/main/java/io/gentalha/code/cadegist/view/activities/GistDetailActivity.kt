package io.gentalha.code.cadegist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.presentation.viewmodel.GetGistDetailViewModel
import io.gentalha.code.common.extensions.handleExceptions
import io.gentalha.code.common.status.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class GistDetailActivity : AppCompatActivity() {

    private val getGistViewModel: GetGistDetailViewModel by viewModel()
    private val gistId: String by lazy {
        intent.getStringExtra("GIST_ID") ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist_detail)
        setupLiveData()
    }

    private fun setupLiveData() {
        getGistViewModel.loadGistDetail(gistId)
        getGistViewModel.gistDetailLiveData.observe(this, {
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

    private fun handleSuccessResult(gist: Gist?) {

    }

    private fun handleError(error: Throwable?) {
        error?.handleExceptions(
            httpException = {},
            whitOutNetWorkException = {},
            otherExceptions = {}
        )
    }
}